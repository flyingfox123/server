/**
 * 角色管理
 * Created by Magic on 2015/2/4.
 */
var app = angular.module('myApp');
var myTree;
/**
 * 角色列表
 */
app.controller('RoleCtrl', function ($scope, $resource, $modal, $http) {
    $scope.sysRole = $resource("admin/sysRole/select").query();
    // 添加角色页面
    $scope.addRole = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysRole/createRole.html',
            controller: 'CreateRoleCtrl',
            backdrop: 'static',
            resolve: {
                currentSysRole: function () {
                    return $scope.sysRole;
                }
            }
        });
    };

    // 编辑角色页面
    $scope.editRole = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysRole/updateRole.html', // 模态窗口的地址
            controller: 'UpdateRoleCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentRole: function () {
                    return $scope.sysRole[index];
                }
            }
        });
    };

    // 删除一列
    $scope.deleteColumn = function (id) {
        $scope.jsonResult = $resource('admin/sysRole/delete').delete({id: id});
        if ($scope.jsonResult.result === 'success') {
            alert("删除id为" + id + "的数据");
            $scope.sysUser.splice(id, 1);
        } else {

        }
    };

    // 编辑权限页面
    $scope.addAuthorization = function (index) {
        $http({
            method: 'GET',
            url: 'admin/sysRoleRes/findResByRoleId?roleId=' + $scope.sysRole[index].id
        }).success(function (data) {
            // 当准备就绪时调用
            var modalInstance = $modal.open({
                templateUrl: 'html/sysRole/authorizationModal.html', // 模态窗口的地址
                controller: 'AuthorizeCtrl',
                backdrop: true, // 点击modal之外窗口关闭还是不关闭的效果，'static'：不关闭, 'true':关闭，'false':不关闭
                keyboard: false, // 按下Esc时模态对话框不关闭
                resolve: {
                    relTree: function () {
                        return data;
                    }
                }
            })
        }).error(function () {
            $scope.jsonResult.message = "服务器出错，请重试！";
        });
    };
});

/**
 * 添加角色
 *
 */
app.controller('CreateRoleCtrl', function ($scope, $resource, $modalInstance, currentSysRole) {
    $scope.Role = {};
    $scope.ok = function () {
        $scope.jsonResult = $resource("admin/sysRole/insert").save($scope.Role, function (data) {
            if ($scope.jsonResult.result === 'success') {
                currentSysRole.push($scope.Role); // 添加一行
                $modalInstance.close();
            } else {
                console.log("failure");
            }
        });
    };
    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

/**
 * 修改角色
 * param ：currentUser当前用户
 * param ：$modelInstance 模态框对象
 *
 */
app.controller('UpdateRoleCtrl', function ($scope, currentRole, $modalInstance) {
    $scope.role = angular.copy(currentRole);
    $scope.ok = function () {
        //TODO submit to server or some other action
        angular.copy($scope.role, currentRole);
        $Scope.jsonResult = $resource("admin/sysRole/update").save($scope.role, function (data) {
            if ($scope.jsonResult.result === 'success') {
                $modalInstance.close();
            } else {
                console.log("failure");
            }
        });
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

/**
 * tree树授权
 */
app.directive('mytree', function () {
    return {
        require: '?ngModel',
        restrict: 'A',
        link: function ($scope, element, attrs, ngModel) {

            var setting = {
                view: {
                    selectedMulti: false // 不允许同时选中多个节点
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                check: {
                    enable: true
                }
            };
            var zNodes = $scope.relTree;
            myTree = $.fn.zTree.init(element, setting, zNodes);
        }
    };
});

/**
 * 为角色授权
 */
app.controller('AuthorizeCtrl', function ($scope, $resource, relTree, $modalInstance) {
    $scope.relTree = {};
    $scope.relTree = relTree;

    $scope.ok = function () {
        // 获取选中的节点
        var treeChecked = myTree.getCheckedNodes(true);
        var treeList = "";

        // 循环获取选中节点
        for (var i = 0; i < treeChecked.length; i++) {
            var checkStatus = treeChecked[i].getCheckStatus();
            // 判断半选状态
            //if (checkStatus.half) {
            //    continue;
            //}
            treeList += treeChecked[i].id;
            treeList += "_";
            treeList += treeChecked[i].checked;
            treeList += ",";
        }

        // 循环获取未选中节点
        var treeUnChecked = myTree.getCheckedNodes(false);
        for(var i=0;i<treeUnChecked.length;i++){
            treeList+=treeUnChecked[i].id;
            treeList+="_";
            treeList+=treeUnChecked[i].checked;
            if(i<treeUnChecked.length-1){
                treeList+=",";
            }
        }
        $scope.grantAuth = {};
        $scope.grantAuth.checkedNodes = treeList;
        $scope.grantAuth.roleId = $scope.relTree[0].roleId;

        $resource("admin/sysRoleRes/grantAuth").save($scope.grantAuth, function (data) {
            $modalInstance.close();
        });
    };
    $scope.cancle = function () {
        $modalInstance.close();
    };
});

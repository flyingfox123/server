/**
 * 资源管理
 * Created by Magic on 2015/2/4.
 */
var app = angular.module('myApp');

app.controller('ResourceCtrl', function ($scope, $resource, $modal) {
    // 资源列表
    $scope.sysResource = $resource("admin/sysResource/select").query();
    // 添加资源页面
    $scope.addResource = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysResource/createReso.html',
            controller: 'CreateResourceCtrl',
            backdrop: 'static',
            resolve: {
                currentSysResource: function () {
                    return $scope.sysResource;
                }
            }
        });
    };

    // 编辑资源页面
    $scope.editResource = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysResource/updateReso.html', // 模态窗口的地址
            controller: 'UpdateResourceCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentResource: function () {
                    return $scope.sysResource[index];
                }
            }
        });
    };

    // 删除一列
    $scope.deleteColumn = function (index) {
        var mId = $scope.sysResource[index].id;
        console.log("当前要删除的数据的id为:" + mId);
        //$scope.jsonResult = $resource('sysResource/delete').delete({id: mId});
        //if ($scope.jsonResult.result === 'success') {
            alert("删除行数为：第" + index + "行的数据");
            $scope.sysUser.splice(index, 1);
        //} else {
        //
        //}
    };
});

/**
 * 添加资源
 *
 */
app.controller('CreateResourceCtrl', function ($scope, $resource, $modalInstance, currentSysResource) {
    $scope.resource = {};
    $scope.ok = function () {
        $scope.jsonResult = $resource("admin/sysResource/insert").save($scope.resource, function (data) {
            if ($scope.jsonResult.result === 'success') {
                currentSysResource.push($scope.Resource); // 添加一行
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
 * 修改资源
 * param ：currentUser当前用户
 * param ：$modelInstance 模态框对象
 *
 */
app.controller('UpdateResourceCtrl', function ($scope, currentResource, $modalInstance) {
    $scope.resource = angular.copy(currentResource);
    $scope.ok = function () {
        //TODO submit to server or some other action
        angular.copy($scope.resource, currentResource);
        $Scope.jsonResult = $resource("admin/sysResource/update").save($scope.resource);
        $modalInstance.close();
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

// 为模块加载controller
app.controller("UserCtrl", function ($scope, $resource, $modal) {
    $scope.sysUser = $resource("usercenter/sysUser/select").query();

    // 添加用户页面
    $scope.addUser = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysUser/createUser.html',
            controller: 'CreateUserCtrl',
            backdrop: 'static',
            resolve: {
                currentSysUser: function () {
                    return $scope.sysUser;
                }
            }
        });
    };

    // 修改用户页面
    $scope.editUser = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/sysUser/updateUser.html', // 模态窗口的地址
            controller: 'UpdateUserCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentUser: function () {
                    return $scope.sysUser[index];
                }
            }
        });
    };

    // 删除一列
    $scope.deleteColumn = function (index) {
        var userId = $scope.sysUser[index].id;
        $scope.jsonResult = $resource('usercenter/sysUser/delete').delete({id: userId});
        if ($scope.jsonResult.result === 'success') {
            alert("删除id为" + id + "的数据");
            $scope.sysUser.splice(id, 1);
        } else {

        }
    };
});

/**
 * 添加用户
 *
 */
app.controller('CreateUserCtrl', function ($scope, $resource, $modalInstance, currentSysUser) {
    $scope.user = {};
    $scope.ok = function () {
        $scope.jsonResult = {message: '验证未通过', result: "failure"};

        $scope.jsonResult = $resource("usercenter/sysUser/insert").save($scope.user, function (data) {
            if ($scope.jsonResult.result === 'success') {
                currentSysUser.push($scope.user);
                $modalInstance.close();
            } else {

            }
        });
    };
    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

/**
 * 修改用户
 * param ：currentUser当前用户
 * param ：$modelInstance 模态框对象
 *
 */
app.controller('UpdateUserCtrl', function ($scope, currentUser, $modalInstance, $resource) {
    $scope.user = angular.copy(currentUser);

    $scope.ok = function () {
        //TODO submit to server or some other action
        angular.copy($scope.user, currentUser);
        $scope.jsonResult = $resource("usercenter/sysUser/update").save($scope.user, function (data) {

            console.log($scope.jsonResult);

            if ($scope.jsonResult.result === 'success') {
                $modalInstance.close();
            } else {
                $scope.jsonResult.message = $scope.jsonResult.errorMsg;
            }
        });
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
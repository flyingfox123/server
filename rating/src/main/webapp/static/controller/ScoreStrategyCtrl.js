/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myScore');

// 为模块加载controller
app.controller("ScoreStrategyCtrl", function ($scope, $resource, $modal) {
    $scope.sysScoreStrategy = $resource("score/scoreStrategy/selectStrategy").query();

    // 添加策略页面
    $scope.addScoreStrategy = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysScore/addScoreStrategy.html',
            controller: 'CreateUserCtrl',
            backdrop: 'static',
            resolve: {
                currentSysUser: function () {
                    return $scope.sysUser;
                }
            }
        });
    };


    // 修改评价策略页面
    $scope.editScoreStrategy = function (index) {

        // $scope.itemList = $resource("score/scoreStrategy/getGradeStrategyItem?gradeStrategyID="+$scope.sysScoreStrategy[index].gradeStrategyID).query();

        var modalInstance = $modal.open({
            templateUrl: 'html/sysScore/updateScoreStrategy.html', // 模态窗口的地址
            controller: 'UpdateScoreStrategyCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentScoreStrategy: function () {
                    return $scope.sysScoreStrategy[index];
                }
            }
        });
    };


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

    // 删除一行
    $scope.deleteColumn = function (index) {
        var scoreStrategyid = $scope.sysScoreStrategy[index].gradeStrategyID;
        $scope.jsonResult = $resource('score/scoreStrategy/deleteScoreStratety').delete({gradeStrategyID: scoreStrategyid});
        if ($scope.jsonResult.codeMsg == '200') {
            alert("删除id为" + scoreStrategyid + "的数据");
            $scope.scoreStrategyid.splice(scoreStrategyid, 1);
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

        $scope.jsonResult = $resource("admin/sysUser/insert").save($scope.user, function (data) {
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
        $scope.jsonResult = $resource("admin/sysUser/update").save($scope.user, function (data) {

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

/**
 * 修改评价策略
 * param ：currentScoreStrategy 当前策略
 * param ：$modelInstance 模态框对象
 *
 */
app.controller('UpdateScoreStrategyCtrl', function ($scope, currentScoreStrategy, $modalInstance, $resource) {
    $scope.currentScoreStrategy = angular.copy(currentScoreStrategy);
    $scope.itemList = $resource("score/scoreStrategy/getGradeStrategyItem?gradeStrategyID="+$scope.currentScoreStrategy.gradeStrategyID).query();
    $scope.currentScoreStrategy.strategyItem=$scope.itemList;

    $scope.ok = function () {
        angular.copy($scope.currentScoreStrategy, currentScoreStrategy);
        $scope.jsonResult = $resource("score/scoreStrategy/updateScoreStrategy").save($scope.currentScoreStrategy, function (data) {

            console.log($scope.jsonResult);
            alert($scope.jsonResult.codeMsg);
            if ($scope.jsonResult.codeMsg == '200') {
                $modalInstance.close();
            } else {
                $scope.jsonResult.errorMsg = $scope.jsonResult.errorMsg;
            }
        });
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
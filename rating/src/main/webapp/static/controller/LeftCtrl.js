/**
 * 左侧菜单
 * @type {module|*}
 */
var app = angular.module('myScore');
app.controller("LeftCtrl", function ($scope, $state) {
    // 进入用户列表页面
    $scope.serviceMsg = function() {
        $state.go('main.serviceMsg');
    };
    $scope.msgManage = function() {
        $state.go('main.msgManage');
        // 进入菜单列表页面
     $scope.scoreManage = function() {
            $state.go('main.scoreStrategyList');
        };
        $scope.scoreStrategyList = function(){
            $state.go('main.scoreStrategyList');
        }

    }
});
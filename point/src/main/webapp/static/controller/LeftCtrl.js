/**
 * 左侧菜单
 * @type {module|*}
 */
var app = angular.module('myPoint');
app.controller("LeftCtrl", function ($scope, $state) {
    // 进入用户列表页面
    $scope.serviceMsg = function() {
        $state.go('main.pointService');
    };
});
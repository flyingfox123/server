/**
 * 左侧菜单
 * @type {module|*}
 */
var app = angular.module('myApp');
app.controller("LeftCtrl", function ($scope, $state) {
    // 进入用户列表页面
    $scope.userManage = function() {
        $state.go('main.userList');
    };
    // 进入角色列表页面
    $scope.roleManage = function() {
        $state.go('main.roleList');
    };
    // 进入菜单列表页面
    $scope.resourceManage = function() {
        $state.go('main.resourceList');
    };
});
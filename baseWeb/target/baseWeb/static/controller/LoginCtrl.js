/**
 * 登录
 * @type {module|*}
 */
var app = angular.module('myApp');

app.controller("LoginCtrl", function ($scope, $http, $state) {
    $scope.User = {};
    $scope.gotoLogin = function () {
        $http({
                method: 'POST',
                url: 'usercenter/login',
                data: $scope.User
            }
        ).success(function (data) {
                if (data.head.state == 'success') {
                    //$state.go('main.select');
                    $state.go('main.userList');
                } else {
                    $scope.result = data;
                }
            });
    }
});
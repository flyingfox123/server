/**
 * 登录
 * @type {module|*}
 */
var app = angular.module('myApp');

app.factory('AuthenticationService', function() {
    var auth = {
        isLogged: false
    }
    return auth;
});

app.factory('TokenInterceptor', function ($q, $window, AuthenticationService) {
    return {
        request: function (config) {
            config.headers = config.headers || {};
            if ($window.sessionStorage.token) {
                config.headers.token =  $window.sessionStorage.token;
                config.headers.loginName =  $window.sessionStorage.loginName;
            }
            return config;
        },

        response: function (response) {
            return response || $q.when(response);
        }
    };
});



app.controller("LoginCtrl", function ($scope, $http, $state,$window,AuthenticationService) {
    $scope.User = {};



    $scope.logout = function logout() {
        if (AuthenticationService.isLogged) {
            AuthenticationService.isLogged = false;
            delete $window.sessionStorage.token;
            $state.go('toLogin');
        }
    }


    $scope.gotoLogin = function () {
        $http({
                method: 'POST',
                url: 'usercenter/login',
                data: $scope.User
            }
        ).success(function (data) {
                if (data.state == 'success') {
                    AuthenticationService.isLogged = true;
                    $window.sessionStorage.token = data.token;
                    $window.sessionStorage.loginName =  $scope.User.loginName;
                    $state.go('main.userList');
                } else {
                    $scope.result = data;
                }
            });
    }
});
angular.module('myApp', [
    'ngResource',   // 引入rest风格请求
    'ui.router',    // 引入路由选择
    'ui.bootstrap'  // 引入bootstrap风格组件
])//依赖注入
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('toLogin', {
                url: '/toLogin',
                templateUrl: 'html/login.html',
                controller: 'LoginCtrl'
            })
            .state('main', {
                url: '/main',
                views: {
                    'header': {
                        templateUrl: 'html/layout/header.html'
                    },
                    'left': {
                        templateUrl: 'html/layout/left.html',
                        controller: 'LeftCtrl'
                    },
                    'right': {
                        templateUrl: 'html/layout/right.html'
                    },
                    'footer': {
                        templateUrl: 'html/layout/footer.html'
                    }
                }
            })
            .state('main.userList', {
                url: '/userList',
                templateUrl: 'html/sysUser/selectUser.html',
                controller: 'UserCtrl'
            })
            .state('main.roleList', {
                url: '/roleList',
                templateUrl: 'html/sysRole/selectRole.html',
                controller: 'RoleCtrl'
            })
            .state('main.resourceList', {
                url: '/resourceList',
                templateUrl: 'html/sysResource/selectReso.html',
                controller: 'ResourceCtrl'
            });
        // 设置默认页
        $urlRouterProvider.otherwise('/toLogin');
    })
    .run(function ($rootScope, $resource) {
    });


angular.module('myPoint', [
    'ngResource',   // 引入rest风格请求
    'ui.router',    // 引入路由选择
    'ui.bootstrap'  // 引入bootstrap风格组件
])//依赖注入
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
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
            .state('main.pointService', {
                url: '/pointService',
                templateUrl: 'html/point/point.html',
                controller: 'PointCtrl'
            });
        // 设置默认页
        $urlRouterProvider.otherwise('/main/pointService');
    })
    .run(function ($rootScope, $resource) {
    });


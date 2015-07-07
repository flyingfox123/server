angular.module('myApp', [
    'ngResource',   // 引入rest风格请求
    'ui.router',    // 引入路由选择
    'ui.bootstrap'  // 引入bootstrap风格组件
])//依赖注入

    .config(function ($httpProvider) {
      $httpProvider.interceptors.push('TokenInterceptor');
    })
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
            .state('main.indiviList', {
                url: '/indiviList',
                templateUrl: 'html/Individual/selectIndivi.html',
                controller: 'IndiviCtrl'
            })
            .state('main.corpList', {
                url: '/corpList',
                templateUrl: 'html/corporation/selectCorp.html',
                controller: 'CorpCtrl'
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
            })
            .state('main.orderList', {
                url: '/orderList',
                templateUrl: 'html/order/selectOrder.html',
                controller: 'OrderCtrl'
            })
            .state('main.orderExpList', {
                url: '/orderExpList',
                templateUrl: 'html/order/selectOrderExp.html',
                controller: 'OrderExpCtrl'
            })
            .state('main.billList', {
                url: '/billList',
                templateUrl: 'html/bill/selectBill.html',
                controller: 'BillCtrl'
            })
            .state('main.checkList', {
                url: '/checkList',
                templateUrl: 'html/check/selectCheckSum.html',
                controller: 'CheckCtrl'
            })
            .state('main.checkDetailList', {
                url: '/checkDetailList',
                templateUrl: 'html/check/selectCheckDetail.html',
                controller: 'CheckDetailCtrl'
            })
            .state('main.pointList', {
                url: '/pointList',
                templateUrl: 'html/point/selectPoint.html',
                controller: 'PointCtrl'
            })
            .state('main.baseData', {
                url: '/AppsBaseData',
                templateUrl: 'html/umengStatistics/appsBaseData.html',
                controller: 'UserBaseDataCtl'
            })
            .state('main.appVersions', {
                url: '/appVersions',
                templateUrl: 'html/umengStatistics/AppVersions.html',
                controller: 'AppVersionsCtl'
            })
            .state('main.appChannels', {
                url: '/appChannels',
                templateUrl: 'html/umengStatistics/AppChannels.html',
                controller: 'AppChannelCtl'
            })
            .state('main.appTodayData', {
                url: '/appTodayData',
                templateUrl: 'html/umengStatistics/AppTodayData.html',
                controller: 'TodayDataCtl'
            })
            .state('main.appYesterdayData', {
                url: '/YesterdayData',
                templateUrl: 'html/umengStatistics/AppYesterdayData.html',
                controller: 'YesterdayDataCtl'
            })
            .state('main.appAnydateData', {
                url: '/appAnydateData',
                templateUrl: 'html/umengStatistics/AppAnydateData.html',
                controller: 'AnyDateDataCtl'
            })
            .state('main.apkUpload', {
                url: '/apkUpload',
                templateUrl: 'html/apkManage/apkUpload.html',
                controller: 'apkUploadCtl'
            })
            .state('main.accountManage', {
                url: '/accountManage',
                templateUrl: 'html/account/selectAccount.html',
                controller: 'AccountCtrl'
            });
        // 设置默认页
        $urlRouterProvider.otherwise('/toLogin');
    })
    .run(function ($rootScope, $resource) {
    });


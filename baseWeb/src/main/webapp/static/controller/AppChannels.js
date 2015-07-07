/**
 * 友盟统计管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

// 为模块加载controller
app.controller("UserBaseDataCtl", function ($scope, $resource, $modal) {

    //apps基本数据
    $scope.UserBaseData = $resource('uMeng/baseData/select', {}, {query: {isArray: false}}).query();

});

// 为模块加载controller
app.controller("TodayDataCtl", ['$scope', '$resource', '$http',function ($scope, $resource, $http) {
    $scope.todayapps = $resource('uMeng/appsData/select', {}, {query: {isArray: true}}).query();
    $scope.loadTodayData = function (todayapp) {
        if(todayapp==""||todayapp==null){
            alert("app为空！")
            return false;
        }
        var appkey = todayapp.appkey;
         $http({
            params: {
                appKey: appkey
            },
            url: 'uMeng/todayData/select'
        }).success(function (data) {
            $scope.AppTodayData=data;
        })
    };
}]);
// 为模块加载controller
app.controller("YesterdayDataCtl", function ($scope, $resource, $http) {

    $scope.yesapps = $resource('uMeng/appsData/select', {}, {query: {isArray: true}}).query();
    $scope.loadYesterdayData = function (yesapp) {
        var appkey = yesapp.appkey;
        if(yesapp==""||yesapp==null){
            alert("app为空！")
            return false;
        }
        $http({
            params: {
                appKey: appkey
            },
            url: 'uMeng/yesterdayData/select'
        }).success(function (data) {
            $scope.AppYesterdayData=data;
        })
    };
});
// 为模块加载controller
// 为模块加载controller
app.controller("AnyDateDataCtl", function ($scope, $resource, $http) {
    $scope.anydateapps = $resource('uMeng/appsData/select', {}, {query: {isArray: true}}).query();
    //apps基本数据
    $scope.loadAnydateData = function (anydateapp) {
        $scope.anyDate = $("#dateText").val();
        var appkey = anydateapp.appkey;
        if(anydateapp==""||anydateapp==null){
            alert("app为空！")
            return false;
        }
        $scope.AnydateData = $http({
            params: {
                appKey: appkey,
                date: $scope.anyDate
            },
            url: 'uMeng/AnyDateData/select'
        }).success(function (data) {
            $scope.AnydateData=data;
        })
    };
});

//流水业务类
app.factory('ChannelService', ['$http', function ($http) {

    var list = function (page, size, appkey, date) {
        return $http({
            params: {
                page: page,
                size: size,
                appKey: appkey,
                date: date
            },
            url: 'uMeng/appChannels/select'
        });
    };

    //var total = function () {
    //    return $http.get('/baseWeb/order/sys/total');
    //};

    return {
        list: function (page, size, appkey, date) {
            return list(page, size, appkey, date);
        }
    };
}]);

app.controller('AppChannelCtl', ['$scope', '$rootScope', '$resource', 'ChannelService', '$modal', function ($scope, $rootScope, $resource, ChannelService, $modal) {
    $scope.channelsapps = $resource('uMeng/appsData/select', {}, {query: {isArray: true}}).query();
    $rootScope.title = '渠道列表';
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 10;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.AppChannels = '';
    $scope.channelsapp;
    $scope.channeldate = '';

    $scope.load = function () {
        var appkey = $scope.channelsapp.appkey;
        $scope.channeldate = $("#dateText").val();
        ChannelService.list($scope.currentPage,
            $scope.pageSize, appkey, $scope.channeldate).success(function (data) {
                $scope.AppChannels = data;
                //获取总页数
                //alert($scope.totalPage);
                $scope.totalPage = 1;
                //alert($scope.totalPage);
                $scope.endPage = $scope.totalPage;
                //alert("total:" +$scope.totalPage);
                //alert("currentPage:" +$scope.currentPage);
                //alert("pages:" +$scope.pages);

                //生成数字链接
                if ($scope.currentPage > 1 && $scope.currentPage < $scope.totalPage) {

                    $scope.pages = [
                        $scope.currentPage - 1,
                        $scope.currentPage,
                        $scope.currentPage + 1
                    ];
                } else if ($scope.currentPage == 1 && $scope.totalPage > 1) {
                    $scope.pages = [
                        $scope.currentPage,
                        $scope.currentPage + 1
                    ];
                } else if ($scope.currentPage == $scope.totalPage && $scope.totalPage > 1) {
                    $scope.pages = [
                        $scope.currentPage - 1,
                        $scope.currentPage
                    ];
                }
                else {
                    $scope.pages = [];

                }
            });
    };
    $scope.loadCondition = function () {
        $scope.currentPage = 1;
        $scope.load();

    };
    $scope.next = function () {
        if ($scope.currentPage < $scope.totalPage) {
            $scope.currentPage++;
            $scope.load();
        }
    };

    $scope.prev = function () {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
            $scope.load();
        }
    };

    $scope.loadPage = function (page) {
        $scope.currentPage = page;
        $scope.load();
    };

}]);

// 为模块加载controller
app.controller("AppVersionsCtl", ['$scope', '$resource', '$http',function ($scope, $resource, $http) {
    $scope.versionpps = $resource('uMeng/appsData/select', {}, {query: {isArray: true}}).query();
    $scope.loadVersions = function (versionpp) {
        $scope.versiondate = $("#dateText").val();
        var appkey = versionpp.appkey;
        $http({
            params: {
                appKey: appkey,
                date: $scope.versiondate
            },
            url: 'uMeng/appVersions/select'
        }).success(function (data) {
            $scope.AppVersionsData=data;
        })
    };
}]);
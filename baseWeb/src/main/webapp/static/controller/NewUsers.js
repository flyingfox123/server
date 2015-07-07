/**
 * 友盟统计管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

// 为模块加载controller
app.controller("NewUsersCtl", ['$scope', '$resource', '$http', function ($scope, $resource, $http) {
    $scope.version1='';
    $scope.channel1 = '';
    $scope.startDate = '';
    $scope.endDate = '';
    $scope.periodType = '';
    $scope.NewUsersData = '';
    $scope.laodVersionAndChannel = function (newapp) {
        $scope.versions = $resource('uMeng/appVersions/select', {
            appKey: $scope.newapp,
            date: 'today'
        }, {query: {isArray: true}}).query();
        $scope.channels = $resource('uMeng/appChannels/select', {
            appKey: $scope.newapp,
            date: 'today',
            size: 100,
            page: 1
        }, {query: {isArray: true}}).query();
    };

    $scope.loadData = function () {
        $scope.startDate = $("#startDateText").val();
        $scope.endDate = $("#endDateText").val();

        var channel;
        if($scope.channel1 != null) {
            channel = $scope.channel1.id;
        }
        var version;
        if($scope.version1 != null) {
            version = $scope.version1.version;
        }

        if (app == "" || app == null) {
            alert("app为空！")
            return false;
        }
        $http({
            params: {
                appKey: $scope.newapp,
                start_date: $scope.startDate,
                end_date:$scope.endDate,
                period_type: $scope.periodType,
                channels:channel,
                versions: version
            },
            url: 'uMeng/newUsers/select'
        }).success(function (data) {
            $scope.NewUsersData = data;
        })
    };
}]);
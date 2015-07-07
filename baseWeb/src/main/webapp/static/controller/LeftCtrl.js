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
    // 进入个人用户列表页面
    $scope.indiviManage = function() {
        $state.go('main.indiviList');
    };
    // 进入企业用户列表页面
    $scope.corpManage = function() {
        $state.go('main.corpList');
    };
    // 进入角色列表页面
    $scope.roleManage = function() {
        $state.go('main.roleList');
    };
    // 进入菜单列表页面
    $scope.resourceManage = function() {
        $state.go('main.resourceList');
    };
    // 进入订单页面
    $scope.orderManage = function() {
        $state.go('main.orderList');
    };

    // 进入订单页面
    $scope.orderExpManage = function() {
        $state.go('main.orderExpList');
    };


    // 进入账单页面
    $scope.billManage = function() {
        $state.go('main.billList');
    };

    // 进入对账异常页面
    $scope.checkManage = function() {
        $state.go('main.checkList');
    };

    // 进入对账异常详情页面
    $scope.checkDetailManage = function() {
        $state.go('main.checkDetailList');
    };

    // 进入积分管理
    $scope.pointManage = function() {
        $state.go('main.pointList');
    };
    //  apps的基本数据
    $scope.baseDataManage = function() {
        $state.go('main.baseData');
    };
    //  app版本列表
    $scope.appVersionsManage = function() {
        $state.go('main.appVersions');
    };
    //  app渠道列表
    $scope.appChannelnsManage = function() {
        $state.go('main.appChannels');
    };
    //  app今日数据
    $scope.appTodayManage = function() {
        $state.go('main.appTodayData');
    };
    //  app昨日数据
    $scope.appYesterdayManage = function() {
        $state.go('main.appYesterdayData');
    };
    //  app任意日期数据
    $scope.appAnydateManage = function() {
        $state.go('main.appAnydateData');
    };
    //  apk上传
    $scope.apkUpload = function() {
        $state.go('main.apkUpload');
    };
    // 账户管理
    $scope.accountManage = function() {
        $state.go('main.accountManage');
    }
});
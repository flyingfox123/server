///**
//* 用户管理
//* @type {module|*}
//*/
//// 定义模块
//var app = angular.module('myApp');
//
//
////流水业务类
//app.factory('PointService', ['$http', function ($http) {
//
//    var list = function (page, size,userId) {
//        return $http({
//            params: {
//                page: page,
//                size: size ,
//                userId:userId
//            },
//            url: '/baseWeb/point/select/points'
//        });
//    };
//
//
//    return {
//        list: function (page, size,userId) {
//            return list(page, size,userId);
//        }
//    };
//}]);
//
//
//app.controller('PointCtrl', ['$scope', '$rootScope', 'PointService','$modal' ,function ($scope, $rootScope, PointService,$modal) {
//    $rootScope.title = '流水列表';
//    $scope.currentPage = 1;
//    $scope.totalPage = 1;
//    $scope.pageSize = 5;
//    $scope.pages = [];
//    $scope.endPage = 1;
//    $scope.pointList;
//    $scope.userId ='' ;
//    $scope.starttime ='';
//    $scope.endTime ='';
//
//    ////获取总流水
//    //BusinessService.total($scope.condition).success(function (data) {
//    //    $scope.totalPage = Math.ceil(data / $scope.pageSize);
//    //    $scope.endPage = $scope.totalPage;
//    //});
//     //获取初始化查询的页面
//    PointService.list($scope.currentPage,
//        $scope.pageSize,$scope.userId).success(function (data) {
//        $scope.pointList = data.list;
//        $scope.totalPage = data.pageCount;
//        $scope.endPage = $scope.totalPage;
//    });
//
//    $scope.load = function () {
//        //alert($scope.state);
//        //alert($scope.type);
//        PointService.list($scope.currentPage,
//            $scope.pageSize,$scope.userId).success(function (data) {
//            $scope.pointList = data.list;
//            //获取总页数
//            //alert($scope.totalPage);
//            $scope.totalPage = data.pageCount;
//            //alert($scope.totalPage);
//            $scope.endPage = $scope.totalPage;
//            //alert("total:" +$scope.totalPage);
//            //alert("currentPage:" +$scope.currentPage);
//            //alert("pages:" +$scope.pages);
//
//            //生成数字链接
//            if ($scope.currentPage > 1 && $scope.currentPage < $scope.totalPage) {
//
//                $scope.pages = [
//                    $scope.currentPage - 1,
//                    $scope.currentPage,
//                    $scope.currentPage + 1
//                ];
//            } else if ($scope.currentPage == 1 && $scope.totalPage > 1) {
//                $scope.pages = [
//                    $scope.currentPage,
//                    $scope.currentPage + 1
//                ];
//            } else if ($scope.currentPage == $scope.totalPage && $scope.totalPage > 1) {
//                $scope.pages = [
//                    $scope.currentPage - 1,
//                    $scope.currentPage
//                ];
//            }
//            else {
//                $scope.pages = [ ];
//
//            }
//        });
//    };
//    $scope.loadCondition = function () {
//            $scope.currentPage = 1;
//            $scope.load();
//
//        };
//    $scope.next = function () {
//        if ($scope.currentPage < $scope.totalPage) {
//            $scope.currentPage++;
//            $scope.load();
//        }
//    };
//
//    $scope.prev = function () {
//        if ($scope.currentPage > 1) {
//            $scope.currentPage--;
//            $scope.load();
//        }
//    };
//
//    $scope.loadPage = function (page) {
//        $scope.currentPage = page;
//        $scope.load();
//    };
//
//    $scope.reset = function () {
//        $scope.userId ='' ;
//        $scope.starttime ='';
//        $scope.endTime ='';
//    };
//
//
//
//    $scope.getPointLog = function (index) {
//
//        var modalInstance = $modal.open({
//            templateUrl: 'html/point/pointlog.html',
//            controller: 'PointLogCtrl',
//            backdrop: 'static',
//            resolve: {
//                currentMsg: function () {
//                    return $scope.pointList[index];
//                }
//            }
//        });
//    }
//
//}]);
//
//app.controller('PointLogCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
//    // 关闭modal弹框
//    $scope.message = angular.copy(currentMsg);
//    $scope.pointLogList = $resource("point/select/pointlogs?userId="+$scope.message.userId).query();
//    $scope.cancel = function () {
//        $modalInstance.close();
//    };
//});
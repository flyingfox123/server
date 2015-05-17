/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
//var app = angular.module('myScore');
'use strict';
var app = angular.module('myPoint')
    .factory('DataService', function ($resource) {
        return $resource('point/:action', {},
            {
                query: {method: 'GET', params: {action: 'query'}, isArray: true},
                save: {method: 'POST', params: {action: 'save'}, isArray: false}
            });
    })

// 为模块加载controller
app.controller("PointCtrl", function ($scope, $resource, $modal) {
    $scope.PointList = $resource("point/select/points").query();
    //$scope.MsgList = [
    //    { tradeOrder: 10001, commodityId:'商品Id', score: 90, graderId: "张三", evaluateContent: "正品，非常喜欢，实体店试穿了，都和店里的一样，买了还以为是假的，拿到手开始查，试穿，都非常好。查过了正品！支持卖家，推荐大家来买", evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 11000, commodityId:'商品Id', score: 81, graderId: "李四", evaluateContent: "我很普通，随便点我吧",evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 12000, commodityId:'商品Id', score: 66, graderId: "轻舞", evaluateContent: "正品，非常喜欢，实体店试穿了，都和店里的一样，买了还以为是假的",evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 13000, commodityId:'商品Id', score: 70, graderId: "离殇", evaluateContent: "我很普通，随便点我吧",evaluateTime:'2014-03-18 15:00:00'},
    //];

    $scope.addPoint = function (index) {


        var modalInstance = $modal.open({
            templateUrl: 'html/point/createPoint.html',
            controller: 'CreatePointCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.PointList[index];
                }

            }
        });
    };


    $scope.usePoint = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/point/usePoint.html',
            controller: 'UsePointCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.PointList[index];
                }

            }
        });
    };

    $scope.getPointLog = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/point/pointlog.html',
            controller: 'PointLogCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.PointList[index];
                }
            }
        });
    }
});

app.controller('CreatePointCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    $scope.message = angular.copy(currentMsg);
    $scope.ok = function () {
        angular.copy($scope.message, currentMsg);

        $scope.jsonResult = $resource("point/addPoint").save($scope.message, function (data) {

            if ($scope.jsonResult.codeMsg =='200') {
                $modalInstance.close();
            } else {
                $scope.jsonResult.message = $scope.jsonResult.errorMsg;
            }
        });
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

app.controller('UsePointCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    $scope.message = angular.copy(currentMsg);


    $scope.ok = function () {
        angular.copy($scope.message, currentMsg);

        $scope.jsonResult = $resource("point/usePoint").save($scope.message, function (data) {

            if ($scope.jsonResult.codeMsg =='200') {
                $modalInstance.close();
            } else {
                $scope.jsonResult.message = $scope.jsonResult.errorMsg;
            }
        });
    };

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

app.controller('PointLogCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    // 关闭modal弹框
    $scope.message = angular.copy(currentMsg);
    $scope.PointLogList = $resource("point/select/pointlogs?userId="+$scope.message.userId).query();
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
//var app = angular.module('myScore');
'use strict';
var app = angular.module('myScore')
    .factory('DataService', function ($resource) {
        return $resource('score/:action', {},
            {
                query: {method: 'GET', params: {action: 'query'}, isArray: true},
                save: {method: 'POST', params: {action: 'save'}, isArray: false},
                delete: {method: 'GET', url: 'score/delete/:id', isArray: false}
            });
    })

// 为模块加载controller
app.controller("ServiceMsgCtrl", function ($scope, $resource, $modal) {
    $scope.MsgList = $resource("score/select/graded").query({
        channelID: 'test',
        commodityID: 'test',
        gradedID: 'wangpengfei',
        pagenum: 1,
        pagesize: 10
    });
    //$scope.MsgList = [
    //    { tradeOrder: 10001, commodityId:'商品Id', score: 90, graderId: "张三", evaluateContent: "正品，非常喜欢，实体店试穿了，都和店里的一样，买了还以为是假的，拿到手开始查，试穿，都非常好。查过了正品！支持卖家，推荐大家来买", evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 11000, commodityId:'商品Id', score: 81, graderId: "李四", evaluateContent: "我很普通，随便点我吧",evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 12000, commodityId:'商品Id', score: 66, graderId: "轻舞", evaluateContent: "正品，非常喜欢，实体店试穿了，都和店里的一样，买了还以为是假的",evaluateTime:'2014-03-18 15:00:00'},
    //    { tradeOrder: 13000, commodityId:'商品Id', score: 70, graderId: "离殇", evaluateContent: "我很普通，随便点我吧",evaluateTime:'2014-03-18 15:00:00'},
    //];

    $scope.addMsg = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/serviceMsg/createMsg.html',
            controller: 'CreateMsgCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.MsgList;
                }

            }
        });
    };

    $scope.getDetailInfo = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/serviceMsg/detailMsg.html',
            controller: 'DetailMsgCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.MsgList[index];
                }
            }
        });
    }
});

app.controller('DetailMsgCtrl', function ($scope, $modalInstance, currentMsg) {
    $scope.message = currentMsg;
    // 追评
    $scope.sendMessage = function () {

    }

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

    app.controller('CreateMsgCtrl', function ($scope, $resource, $modalInstance,DataService,currentMsg) {
        //gradeStrategyID:"11",channelID:"11",gradeStrategyName:"11",gradedID:"magic",tradeOrder:"order11",commodityID:"bmw"
       // $scope.message = angular.copy(Params.message);
       // $scope.isAdd = Params.isAdd;
        $scope.message = {
            gradeStrategyID: "11",
            channelID: "test",
            gradeStrategyName: "11",
            gradedID: "wangpengfei",
            tradeOrder: "order11",
            commodityID: "test",
            graderID: "lujing",
            scoreRule: "bmw"
        };
        $scope.ok = function () {
           $scope.jsonResult =  DataService.save($scope.message, function () {
                if ($scope.jsonResult.codeMsg =='200') {
                    alert("添加成功");
                    currentMsg.push($scope.message); // 添加一行
                    $modalInstance.close();
                } else {
                    console.log("failure");
                }
            });
        };
    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
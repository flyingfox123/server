
var app = angular.module('myApp');
// 为模块加载controller
app.controller("OrderExpCtrl", function ($scope, $resource, $modal) {
    $scope.expList =  [
        { seqNo: "201506171010942688", type:'etc', userId: "马宾宝", createTime: "2015-06-25 11:24:08", state: "预充值失败",driverName:"马宾宝",loginName:"18611111111"},
        { seqNo: "201506171010942698", type:'etc', userId: "露露", createTime: "2015-06-24 11:24:08", state: "预充值失败",driverName:"露露",loginName:"18622222222"},
        { seqNo: "201506171010942689", type:'etc', userId: "峰娃子", createTime: "2015-06-23 11:24:08", state: "预充值失败",driverName:"峰娃子",loginName:"18633333333"},
        { seqNo: "201506171010949688", type:'etc', userId: "赵小新", createTime: "2015-06-22 11:24:08", state: "预充值失败",driverName:"赵小新",loginName:"18644444444"},
        { seqNo: "201506171010942999", type:'etc', userId: "鹏飞", createTime: "2015-06-17 11:24:08", state: "预充值失败",driverName:"鹏飞",loginName:"18655555555"}
    ];

    $scope.getExtItems = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/order/orderExpItem.html',
            controller: 'OrderItemExpCtrl',
            backdrop: 'static',
            size:"lg",
            resolve: {
                currentMsg: function () {
                    return $scope.expList[index];
                }
            }
        });
    }
});

app.controller('OrderItemExpCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    // 关闭modal弹框
    $scope.message = angular.copy(currentMsg);
    $scope.expItemList = [
        { plate1: "鲁A 122", plate2: "鲁A 122", card1: "12385",card2: "12345",amount1: "1200", amount2: "1200",state1: "失败", state2: "成功",time1: "2015-06-25 10:10:20", time2: "2015-06-25 10:10:20"},
        { plate1: "鲁A 322", plate2: "鲁A 344", card1: "23456",card2: "23456",amount1: "1200", amount2: "1200",state1: "成功", state2: "成功",time1: "2015-06-25 10:10:20", time2: "2015-06-25 10:10:20"},
        { plate1: "鲁A 123", plate2: "鲁A 123", card1: "24566",card2: "24566",amount1: "1200", amount2: "1200",state1: "失败", state2: "成功",time1: "2015-06-25 10:10:20", time2: "2015-06-25 10:10:20"},
        { plate1: "鲁A 345", plate2: "鲁A 345", card1: "54143",card2: "54343",amount1: "1200", amount2: "1200",state1: "成功", state2: "成功",time1: "2015-06-25 10:10:20", time2: "2015-06-25 10:10:20"}
    ];
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

$("input[name!='newsletter']").attr("checked", true);
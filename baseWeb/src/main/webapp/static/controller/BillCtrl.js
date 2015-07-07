/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');


app.controller('BillCtrl', ['$scope', '$rootScope','$resource','$modal' ,function ($scope, $rootScope,$resource,$modal) {
    $rootScope.title = '流水列表';
    $scope.billList;
    $scope.billBean ='';

    $scope.billPage = $resource("/baseWeb/pay/bill/manage/getBill", {}, {query: {isArray: false}}).query($scope.billBean,function(){
    $scope.billList = $scope.billPage.list;
    });

    $scope.query = function () {
        $scope.billPage = $resource("/baseWeb/pay/bill/manage/getBill", {}, {query: {isArray: false}}).query($scope.billBean,function(){
            $scope.billList = $scope.billPage.list;
            console.log($scope.billList);
        });
    };

    $scope.reset = function () {
        //$scope.billBean.accountId='';
        //$scope.billBean.billNo;
        //$scope.billBean.tradeState ='';
        //$scope.billBean.checkState ='';
        //$scope.billBean.minAmount ='';
        //$scope.billBean.maxAmount ='';
        //$scope.billBean.tradeType ='';
        //$scope.billBean.tradeTimeStart;
        //$scope.billBean.tradeTimeEnd;
        $scope.billBean='';
    };

    $scope.getOrderItem = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/bill/billItem.html',
            controller: 'BillItemCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.billList[index];
                }
            }
        });
    }
}]);

app.controller('BillItemCtrl', function ($scope,$rootScope, currentMsg, $modalInstance, $resource) {
    $rootScope.title = '流水列表';
    // 关闭modal弹框
    $scope.itemDetail='';
    $scope.message = angular.copy(currentMsg);
    //$scope.itemList = $resource("order/selectItems?orderId="+$scope.message.billNo).query();
    $resource("/baseWeb/pay/bill/manage/getAccount", {}, {query: {isArray: false}}).query($scope.message,function(data){
        $scope.realName=data.realName;
    });
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

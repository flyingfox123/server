/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

app.controller('CheckCtrl', ['$scope', '$rootScope','$resource','$modal' ,'$state',function ($scope, $rootScope,$resource,$modal,$state) {
    $rootScope.title = '流水列表';
    $scope.checkSumList=[];
    $scope.checkDetailBean ='';
    $scope.createDateStart;
    $scope.createDateEnd;
    $resource("/baseWeb/pay/check/manage/getCheckSum", {}, {query: {isArray: true}}).query($scope.checkDetailBean,function(data){
        $scope.checkSumList = data;
    });
    $scope.query = function () {
        $resource("/baseWeb/pay/check/manage/getCheckSum", {}, {query: {isArray: true}}).query($scope.checkDetailBean,function(data){
            //alert($scope.checkDetailBean.createDateStart)
            $scope.checkSumList = data;
            console.log($scope.checkDetailBean)
        });
    };

    $scope.openDetailItem = function() {
        $state.go('main.checkDetailList');
    };

    $scope.reset = function () {
        $scope.checkDetailBean='';
    };


}]);


app.controller('CheckDetailCtrl', ['$scope', '$rootScope','$resource','$modal' ,function ($scope, $rootScope,$resource,$modal) {
    $rootScope.title = '流水列表';
    $scope.checkDetailBean ='';
    $scope.checkDetailList='';
    $resource("/baseWeb/pay/check/manage/getCheckDetail", {}, {query: {isArray: true}}).query($scope.checkDetailBean,function(data){
        $scope.checkDetailList = data;
    });
    $scope.query = function () {
        $resource("/baseWeb/pay/check/manage/getCheckDetail", {}, {query: {isArray: true}}).query($scope.checkDetailBean,function(data){
            //alert($scope.checkDetailBean.createDateStart)
            $scope.checkDetailList = data;
        });
    };

    $scope.reset = function () {
        $scope.checkDetailBean='';
    };



    $scope.getExceptionDetail = function (index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/check/checkDetailItem.html',
            controller: 'CheckDetailItemCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.checkDetailList[index];
                }
            }
        });
    }
}]);


app.controller('CheckDetailItemCtrl', function ($scope,$rootScope, currentMsg, $modalInstance, $resource) {
    $rootScope.title = '流水列表';
    // 关闭modal弹框
    $scope.itemDetail='';
    $scope.messageMy='';
    $scope.messageZd='';
    $scope.message = angular.copy(currentMsg);
    if($scope.message.state==1){
        $scope.messageMy=$scope.message;
    }else if($scope.message.state==2){
        $scope.messageZd=$scope.message;
    }else if($scope.message.state==3){
        $scope.messageMy=$scope.message;
        $scope.messageZd=$scope.message;
    }
    //$scope.itemList = $resource("order/selectItems?orderId="+$scope.message.billNo).query();
    //$resource("/baseWeb/pay/bill/manage/getAccount", {}, {query: {isArray: false}}).query($scope.message,function(data){
    //    $scope.realName=data.realName;
    //});
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

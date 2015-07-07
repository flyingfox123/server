/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');
//流水业务类
app.factory('PointService', ['$http', function ($http) {

    var list = function (page, size,userId) {
        return $http({
            params: {
                page: page,
                size: size ,
                userId:userId
            },
            url: '/baseWeb/point/sys/select/points'
        });
    };


    return {
        list: function (page, size,userId) {
            return list(page, size,userId);
        }
    };
}]);


//流水业务类
app.factory('BusinessService', ['$http', function ($http) {

    var list = function (page, size,seq,userId,state,type) {
        return $http({
            params: {
                page: page,
                size: size ,
                seq:seq,
                userId:userId,
                state:state,
                type:type
            },
            url: '/baseWeb/order/sys/select'
        });
    };

    var total = function () {
        return $http.get('/baseWeb/order/sys/total');
    };

    var post = function (business) {
        return $http.post('/merchants/business/post', business);
    };

    return {
        list: function (page, size,seq,userId,state,type) {
            return list(page, size,seq,userId,state,type);
        },
        total: function () {
            return total();
        },
        post: function (business) {
            return post(business);
        }
    };
}]);


app.controller('OrderCtrl', ['$scope', '$rootScope', 'BusinessService','$modal' ,function ($scope, $rootScope, BusinessService,$modal) {
    $rootScope.title = '流水列表';
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 5;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.orderList;
    $scope.seqNo='';
    $scope.userId ='' ;
    $scope.starttime ='';
    $scope.endTime ='';
    $scope.state ='';
    $scope.type ='';
    $scope.filter='';

    ////获取总流水
    //BusinessService.total($scope.condition).success(function (data) {
    //    $scope.totalPage = Math.ceil(data / $scope.pageSize);
    //    $scope.endPage = $scope.totalPage;
    //});
     //获取初始化查询的页面
    BusinessService.list($scope.currentPage,
        $scope.pageSize, $scope.seqNo,0,$scope.state, $scope.type).success(function (data) {
        $scope.orderList = data.list;
        $scope.totalPage = data.pageCount;
        $scope.endPage = $scope.totalPage;
    });


    $scope.changeFilter = function (filter) {
        $scope.filter= filter;
        $scope.load();

    };


    $scope.load = function () {
        //alert($scope.state);
        //alert($scope.type);
        BusinessService.list($scope.currentPage,
            $scope.pageSize,$scope.seqNo,$scope.userId,$scope.state, $scope.type).success(function (data) {
            $scope.orderList = data.list;
            //获取总页数
            //alert($scope.totalPage);
            $scope.totalPage = data.pageCount;
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
                $scope.pages = [ ];

            }
        });
    };
    $scope.loadCondition = function () {
            $scope.currentPage = 1;
            $scope.filter='';
            $scope.load();

        };
    $scope.next = function () {
        if ($scope.currentPage < $scope.totalPage) {
            $scope.filter='';
            $scope.currentPage++;
            $scope.load();
        }
    };

    $scope.prev = function () {
        if ($scope.currentPage > 1) {
            $scope.filter='';
            $scope.currentPage--;
            $scope.load();
        }
    };

    $scope.loadPage = function (page) {
        $scope.filter='';
        $scope.currentPage = page;
        $scope.load();
    };

    $scope.reset = function () {
        $scope.seqNo='';
        $scope.userId ='' ;
        $scope.starttime ='';
        $scope.endTime ='';
        $scope.state ='';
        $scope.type ='';
    };



    $scope.getOrderItem = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/order/orderItem.html',
            controller: 'OrderItemCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.orderList[index];
                }
            }
        });
    }

}]);

app.controller('OrderItemCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    // 关闭modal弹框
    $scope.message = angular.copy(currentMsg);
    //查询用户信息

    $scope.itemList = $resource("order/selectETCItems?orderId="+$scope.message.id).query();
    $scope.user = $resource("order/queryOrderUser?userId="+$scope.message.userId, {}, {query: {isArray: false}}).query();
    $scope.cancel = function () {
        $modalInstance.close();
    };
});




app.controller('PointCtrl', ['$scope', '$rootScope', 'PointService','$modal' ,function ($scope, $rootScope, PointService,$modal) {
    $rootScope.title = '流水列表';
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 5;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.pointList;
    $scope.userId ='' ;
    $scope.starttime ='';
    $scope.endTime ='';

    ////获取总流水
    //BusinessService.total($scope.condition).success(function (data) {
    //    $scope.totalPage = Math.ceil(data / $scope.pageSize);
    //    $scope.endPage = $scope.totalPage;
    //});
    //获取初始化查询的页面
    PointService.list($scope.currentPage,
        $scope.pageSize,$scope.userId).success(function (data) {
            $scope.pointList = data.list;
            $scope.totalPage = data.pageCount;
            $scope.endPage = $scope.totalPage;
        });

    $scope.load = function () {
        //alert($scope.state);
        //alert($scope.type);
        PointService.list($scope.currentPage,
            $scope.pageSize,$scope.userId).success(function (data) {
                $scope.pointList = data.list;
                //获取总页数
                //alert($scope.totalPage);
                $scope.totalPage = data.pageCount;
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
                    $scope.pages = [ ];

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

    $scope.reset = function () {
        $scope.userId ='' ;
        $scope.starttime ='';
        $scope.endTime ='';
    };



    $scope.getPointLog = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/point/pointlog.html',
            controller: 'PointLogCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.pointList[index];
                }
            }
        });
    }

}]);

app.controller('PointLogCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
    // 关闭modal弹框
    $scope.message = angular.copy(currentMsg);
    $scope.pointLogList= $resource("point/sys/select/pointLogs?userId="+$scope.message.userId).query();
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

app.factory('CorpService', ['$http', function ($http) {

    var list = function (page, size,userId,loginName) {
        return $http({
            params: {
                page: page,
                size: size,
                userId:userId,
                loginName:loginName
            },
            url: 'usercenter/Corporation/select'
        });
    };

    //var total = function () {
    //    return $http.get('/baseWeb/order/sys/total');
    //};

    return {
        list: function (page, size,userId,loginName) {
            return list(page, size,userId,loginName);
        }
    };
}]);

app.controller('CorpCtrl', ['$scope', '$rootScope', '$resource', 'CorpService', '$modal', function ($scope, $rootScope, $resource, CorpService, $modal) {
    $rootScope.title = '个人用户列表';
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 10;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.Corporation = '';
    $scope.userId ='';
    $scope.loginName ='';
    CorpService.list($scope.currentPage,
        $scope.pageSize,0,$scope.loginName).success(function (data) {
            $scope.Corporation = data.list;
            //获取总页数
            // alert($scope.sysUser);
            // alert(data.totalCount);
            $scope.totalPage = Math.ceil(data.totalCount/$scope.pageSize);
            //alert($scope.totalPage);
            $scope.endPage = $scope.totalPage;
        });
    $scope.load = function () {
        //alert($scope.userId);
        if($scope.userId==""||$scope.userId==null){
            $scope.userId=0;
        }
        //alert($scope.userId);
        //alert( $scope.loginName);
        CorpService.list($scope.currentPage,
            $scope.pageSize,$scope.userId ,$scope.loginName).success(function (data) {
                //  alert(data);
                $scope.Corporation = data.list;
                //获取总页数
                // alert($scope.sysUser);
               // alert(data.totalCount);
                $scope.totalPage = Math.ceil(data.totalCount/$scope.pageSize);
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
    // 密码重置
    $scope.resetPassword = function (loginName) {
        $scope.jsonResult = $resource("usercenter/user/resetPassword").save(loginName, function (data) {
            if ($scope.jsonResult.result === 'success') {
                alert("重置成功");
            } else {
                alert("重置失败");
            }
        });
    };

    // 用户停用
    $scope.disableUser = function (index) {
        var userId = $scope.Corporation[index].userId;
        $scope.jsonResult = $resource("usercenter/user/disableUser").save(userId, function (data) {
            if ($scope.jsonResult.result === 'success') {
                alert("停用成功");
            } else {
                alert("停用失败");
            }
        });
    };
    $scope.reset = function () {
        $scope.loginName='';
    };
}]);
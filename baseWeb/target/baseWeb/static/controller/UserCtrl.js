/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myApp');

app.factory('sysUserService', ['$http', function ($http) {

    var list = function (page, size,userId,loginName) {
        return $http({
            params: {
                page: page,
                size: size,
                userId:userId,
                loginName:loginName
            },
            url: 'usercenter/sysUser/select'
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

app.controller('UserCtrl', ['$scope', '$rootScope', '$resource', 'sysUserService', '$modal', function ($scope, $rootScope, $resource, sysUserService, $modal) {
    $rootScope.title = '平台用户列表';
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 10;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.sysUser = '';
    $scope.userId ='';
    $scope.loginName ='';
    //获取初始化查询的页面
    sysUserService.list($scope.currentPage,
        $scope.pageSize,0,$scope.loginName).success(function (data) {
            $scope.sysUser = data.list;
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
        sysUserService.list($scope.currentPage,
            $scope.pageSize,$scope.userId ,$scope.loginName).success(function (data) {
              //  alert(data);
                $scope.sysUser = data.list;
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
    // 添加用户页面
    $scope.addUser = function () {
        var modalInstance = $modal.open({
            templateUrl: 'html/sysUser/createUser.html',
            controller: 'CreateUserCtrl',
            backdrop: 'static',
            resolve: {
                currentSysUser: function () {
                    return $scope.sysUser;
                }
            }
        });
    };

    // 修改用户页面
    $scope.editUser = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/sysUser/updateUser.html', // 模态窗口的地址
            controller: 'UpdateUserCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentUser: function () {
                    return $scope.sysUser[index];
                }
            }
        });
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
        var userId = $scope.sysUser[index].userId;
        var state = $scope.sysUser[index].state;
        if(state=='D'){
            alert("用户已停用");
            return null;
        }
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
/**
 * 添加用户
 *
 */
app.controller('CreateUserCtrl', function ($scope, $resource, $modalInstance, currentSysUser) {
    Date.prototype.format =function(format)
    {
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4- RegExp.$1.length));
        for(var k in o)if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length==1? o[k] :
                    ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }
    $scope.user = {};
    $scope.ok = function () {
        $scope.jsonResult = {message: '验证未通过', result: "failure"};

        $scope.jsonResult = $resource("usercenter/sysUser/insert").save($scope.user, function (data) {
            if ($scope.jsonResult.state === 'success') {
                $scope.user.state='E';
                var myDate=new Date().format('yyyy-MM-dd');
                $scope.user.createTime=myDate;
                currentSysUser.push($scope.user);
                $modalInstance.close();
            } else {
                alert("创建失败");
            }
        });
    };
    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

/**
 * 修改用户
 * param ：currentUser当前用户
 * param ：$modelInstance 模态框对象
 *
 */
app.controller('UpdateUserCtrl', function ($scope, currentUser, $modalInstance, $resource) {
    $scope.user = angular.copy(currentUser);

    $scope.ok = function () {
        //TODO submit to server or some other action
        angular.copy($scope.user, currentUser);
        $scope.jsonResult = $resource("usercenter/sysUser/update").save($scope.user, function (data) {

            console.log($scope.jsonResult);

            if ($scope.jsonResult.result === 'success') {
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
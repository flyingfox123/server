/**
 * 账户管理
 * Created by Magic on 2015/2/4.
 */
var app = angular.module('myApp');

app.controller('AccountCtrl', function ($scope, $resource, $modal) {
    $scope.accountInfoBean='';
    $resource('pay/account/queryAccount', {}, {query: {isArray: false}}).query(function(data) {
        $scope.accounts = data.list;
    });

    /**
     * 查看账户详情
     */
    $scope.accountDetail = function(index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/account/accountDetail.html',
            controller: 'AccDetailCtrl',
            backdrop: 'static',
            resolve: {
                currentAccount: function () {
                    return $scope.accounts[index];
                }
            }
        });
    }

    /**
     * 冻结账户
     */
    $scope.disableAccount = function(index) {

        var accountId = $scope.accounts[index].accountId;

        $scope.jsonResult = $resource("pay/account/disableAccount").save(accountId, function(data) {
            if ($scope.jsonResult.result === 'success') {
                $scope.accounts[index].state = 2;
                alert("冻结账户成功!");
            } else {
                alert("冻结账户失败!");
            }
        });
    };

    /**
     * 解冻账户
     */
    $scope.enableAccount = function(index) {

        var accountId = $scope.accounts[index].accountId;

        $scope.jsonResult = $resource("pay/account/enableAccount").save(accountId, function (data) {

            if ($scope.jsonResult.result === 'success') {
                $scope.accounts[index].state = 1;
                alert("解冻账户成功!");
            } else {
                alert("解冻账户失败!");
            }
        });
    }

    /**
     *按条件查询账户
     */
    $scope.queryByCondition = function() {

        console.log($scope.accountInfoBean);

        $resource('pay/account/queryAccByCondition', {}, {
            query:
            {
                method:'POST',
                isArray: false
            }
        }).query($scope.accountInfoBean, function(data) {
            $scope.accounts = data.list;
        });
    }


    //$scope.pages = [];
    //$scope.totalPage = 1;
    //$scope.pageSize = 10;
    //$scope.currentPage = 1;
    ////获取初始化查询的页面
    //var data = $resource('pay/account/queryAccount', {}, {query: {isArray: false}}).query($scope.accountInfoBean, function(){
    //    $scope.accounts = data.list;
    //    $scope.totalPage = Math.ceil(data.totalCount/$scope.pageSize);
    //    $scope.endPage = $scope.totalPage;
    //    //生成数字链接
    //    if ($scope.currentPage > 1 && $scope.currentPage < $scope.totalPage) {
    //
    //        $scope.pages = [
    //            $scope.currentPage - 1,
    //            $scope.currentPage,
    //            $scope.currentPage + 1
    //        ];
    //    } else if ($scope.currentPage == 1 && $scope.totalPage > 1) {
    //        $scope.pages = [
    //            $scope.currentPage,
    //            $scope.currentPage + 1
    //        ];
    //    } else if ($scope.currentPage == $scope.totalPage && $scope.totalPage > 1) {
    //        $scope.pages = [
    //            $scope.currentPage - 1,
    //            $scope.currentPage
    //        ];
    //    }
    //    else {
    //        $scope.pages = [];
    //    }
    //});
    //
    //$scope.load = function () {
    //
    //    var data = $resource('pay/account/queryAccount', {}, {query: {isArray: false}}).query($scope.accountInfoBean, function() {
    //        $scope.accounts = data.list;
    //        //获取总页数
    //        $scope.totalPage = Math.ceil(data.totalCount/$scope.pageSize);
    //        $scope.endPage = $scope.totalPage;
    //
    //        //生成数字链接
    //        if ($scope.currentPage > 1 && $scope.currentPage < $scope.totalPage) {
    //
    //            $scope.pages = [
    //                $scope.currentPage - 1,
    //                $scope.currentPage,
    //                $scope.currentPage + 1
    //            ];
    //        } else if ($scope.currentPage == 1 && $scope.totalPage > 1) {
    //            $scope.pages = [
    //                $scope.currentPage,
    //                $scope.currentPage + 1
    //            ];
    //        } else if ($scope.currentPage == $scope.totalPage && $scope.totalPage > 1) {
    //            $scope.pages = [
    //                $scope.currentPage - 1,
    //                $scope.currentPage
    //            ];
    //        }
    //        else {
    //            $scope.pages = [];
    //        }
    //    });
    //};
    //$scope.loadCondition = function () {
    //    $scope.currentPage = 1;
    //    $scope.load();
    //
    //};
    //$scope.next = function () {
    //    if ($scope.currentPage < $scope.totalPage) {
    //        $scope.currentPage++;
    //        $scope.load();
    //    }
    //};
    //
    //$scope.prev = function () {
    //    if ($scope.currentPage > 1) {
    //        $scope.currentPage--;
    //        $scope.load();
    //    }
    //};
    //
    //$scope.loadPage = function (page) {
    //    $scope.currentPage = page;
    //    $scope.load();
    //};
});

/**
 * 账户详情页面
 *
 */
app.controller('AccDetailCtrl', function ($scope, $modalInstance, currentAccount) {

    /**
     * 用*遮掩字符串
     * @param string 需要处理的字符串
     * @param beginHiddenIndex 字符串起始需要隐藏的位数
     * @param endHiddenIndex 字符串末端需要隐藏的位数
     * 手机号（明文显示前3位和后4位，中间“*”隐藏）
     * 身份证号（明文显示前4位和后6位，中间“*”隐藏）
     * 银行卡号：（前4位+卡号后4位 中间*隐藏）
     */
    $scope.dealString = function(string, beginHiddenIndex, endHiddenIndex) {
        if(string != null && string != "") {
            var beforeStr = "";
            var otherStr = "";
            if(beginHiddenIndex > 0) {
                beforeStr = string.substring(0, beginHiddenIndex);
                otherStr = string.substring(beginHiddenIndex, string.length-endHiddenIndex);
            } else {
                otherStr = string.substring(0, string.length-endHiddenIndex);
            }
            var afterStr = string.substring(string.length-endHiddenIndex, string.length);

            var str = "";
            for (i = 0; i < otherStr.length; i++) {
                str += "*";
            }
            return beforeStr + str + afterStr;
        }
    }

    $scope.account = currentAccount;

    $scope.account.mobile = $scope.dealString($scope.account.mobile, 3, 4);

    $scope.account.idCardNo = $scope.dealString($scope.account.idCardNo, 4, 6);

    $scope.account.cardNo = $scope.dealString($scope.account.cardNo, 4, 4);

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});
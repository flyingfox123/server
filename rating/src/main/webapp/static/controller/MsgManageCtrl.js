/**
 * 用户管理
 * @type {module|*}
 */
// 定义模块
var app = angular.module('myScore');

// 为模块加载controller
app.controller("MsgManageCtrl", function ($scope, $resource, $modal) {
    $scope.MsgList = $resource("score/select/grader").query({ channelID: 'test',commodityID:'test',graderID:'lujing',pagenum:1,pagesize:10});
    $scope.getDetailInfo = function(index) {
        var modalInstance = $modal.open({
            templateUrl: 'html/msgManage/detailMsg.html',
            controller: 'DetailMsgCtrl',
            backdrop: 'static',
            resolve: {
                currentMsg: function () {
                    return $scope.MsgList[index];
                }
            }
        });
    }

    $scope.editMsg = function (index) {

        var modalInstance = $modal.open({
            templateUrl: 'html/msgManage/msgUpdate.html', // 模态窗口的地址
            controller: 'UpdateMsgCtrl',
            backdrop: 'static', // 点击modal之外窗口不关闭
            keyboard: false, // 按下Esc时，模态对话框不关闭
            resolve: {
                currentMsg: function () {
                    return $scope.MsgList[index];
                }
            }
        });
    };
    $scope.deleteMsg = function (index) {
        var answer = confirm("确定要删除吗?");
        if (answer) {
            $scope.jsonResult=  $resource('score/delete').delete({id: $scope.MsgList[index].id}, function () {
                    if ($scope.jsonResult.codeMsg =='200') {
                        $scope.MsgList.splice(index, 1);
                    } else {
                        alert($scope.jsonResult.errorMsg);
                    }

        });
    }
};
})
app.controller('DetailMsgCtrl',function($scope, $modalInstance, currentMsg) {
    $scope.message = currentMsg;
    // 追评
    $scope.sendMessage = function () {

    }

    // 关闭modal弹框
    $scope.cancel = function () {
        $modalInstance.close();
    };
});

    app.controller('UpdateMsgCtrl', function ($scope, currentMsg, $modalInstance, $resource) {
        $scope.message = angular.copy(currentMsg);

        $scope.ok = function () {
            angular.copy($scope.message, currentMsg);
            $scope.jsonResult = $resource("score/update").save($scope.message, function (data) {

                console.log($scope.jsonResult);

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
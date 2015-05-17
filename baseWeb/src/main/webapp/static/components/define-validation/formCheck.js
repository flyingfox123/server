/**
 * Created by Magic on 2015/3/5.
 * 对于form的数据验证
 * 提供一些基本的数据验证
 * 如果没有合适自己的请自定义验证指令
 */

var app = angular.module('myApp');

/**
 *
 */
app.directive('myCharacter', function () {
    return {
        require: '?ngModel',
        restrict: 'A',
        link: function ($scope, element, attrs, ngModel) {
            //element.
            element.onfocus();
            //attrs.
        }
    };
});

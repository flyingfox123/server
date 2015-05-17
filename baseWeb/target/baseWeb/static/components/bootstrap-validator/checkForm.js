//$(document).ready(function() {
//  $('#myForm').bootstrapValidator({
//    message: 'This value is not valid',
//    feedbackIcons: {
//      valid: 'glyphicon glyphicon-ok',
//      invalid: 'glyphicon glyphicon-remove',
//      validating: 'glyphicon glyphicon-refresh'
//    },
//    fields: {
//      username: {
//        message: 'The username is not valid',
//        validators: {
//          notEmpty: {
//            message: '用户名不可为空！'
//          },
//          stringLength: {
//            min: 6,
//            max: 20,
//            message: '用户名必须超过6个字节但小于30个字节！'
//          },
//          regexp: {
//            regexp: /^[a-zA-Z0-9_]+$/,
//            message: '用户名只能包含字母数字下划线！'
//          },
//          required:true
//        }
//      },
//      age: {
//        validators: {
//          lessThan: {
//            value: 100,
//            inclusive: true,
//            message: '年龄不得大于100！'
//          },
//          greaterThan: {
//            value: 10,
//            inclusive: false,
//            message: '年龄不得小于10！'
//          }
//        }
//      },
//      email: {
//        validators: {
//          notEmpty: {
//            message: '邮箱不可为空！'
//          },
//          emailAddress: {
//            message: '请填写正确的邮箱！'
//          }
//        }
//      },
//      phone: {
//        validators: {
//          notEmpty: {
//            message: '手机号码不可为空！'
//          },
//          emailAddress: {
//            message: '请填写正确的手机号码！'
//          }
//        }
//      }
//    }
//  });
//});
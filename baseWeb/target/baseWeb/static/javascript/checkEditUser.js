/**
 * 用户修改表单验证
 */
$().ready(function() {
  $('#userForm').validate({
    rules: {
      loginName: {
        stringMaxLength:24,
        maxLength:24,
        minLength:6,
        required:true
      },
      password: {
        maxLength:24,
        minLength:6,
        isRightfulString:true
      },
      nickName: {
        chcharacter: true,
        stringMaxLength:24,
        required:true,
        maxLength:24,
        minLength:6
      },
      age: {
        required:true,
        isAge : true
      },
      email: {
        required:true,
        email : true
      },
      phone: {
        required:true,
        isMobile : true
      }
    }
  });
});
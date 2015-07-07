package com.manyi.usercenter.event;

import com.manyi.usercenter.user.bean.UserBean;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/6/9.
 */
public class RegisterSuccessEvent extends ApplicationEvent {

    public RegisterSuccessEvent( BaseUser user) {
        super(user);
    }

}

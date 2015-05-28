package com.manyi.base.entity;

import org.apache.shiro.realm.Realm;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

/**
 * Created by Administrator on 2015/4/9.
 */
public enum State {
    //正常
    NORMAL('N'),
    //异常
    EXCEPTION('E'),
    //关闭
    CLOSE('C') ,

    TEST('T'),

    SUCCESS("success"),

    FAIL("fail")
    ;

    private char state;

    private String str;

    State(String state){this.str=state;}

    public String getString(){
        return this.str;
    }

    State( char state )
    {
        this.state = state;
    }

    public char getState()
    {
        return this.state;
    }
}

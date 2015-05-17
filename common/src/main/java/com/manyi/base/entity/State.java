package com.manyi.base.entity;

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

    TEST('T')
    ;

    private char state;

    State( char state )
    {
        this.state = state;
    }

    public char getState()
    {
        return this.state;
    }
}

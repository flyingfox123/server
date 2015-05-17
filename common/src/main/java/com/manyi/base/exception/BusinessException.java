package com.manyi.base.exception;


import com.manyi.base.entity.Type;

/**
 * Created by Administrator on 2015/4/8.
 */

public class BusinessException extends Exception
{
    private static final long serialVersionUID = 1L;
    private Type type;

    public BusinessException( Type type )
    {
        super();
        this.type = type;
    }

    public BusinessException(String message) {
        super(message);
        this.type=Type.DEFAULT_ERROR;
    }

    public BusinessException(Type type,String message)
    {
        super(message);
        this.type = type;
    }

    public BusinessException( Throwable t, Type type )
    {
        super( t );
        this.type = type;
    }

    public String toString() {
        return super.toString() + "<" + getErrorType().getErrorCode() + ">";
    }

    public Type getErrorType()
    {
        return type;
    }

}
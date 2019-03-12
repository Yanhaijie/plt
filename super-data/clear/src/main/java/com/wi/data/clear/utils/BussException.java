package com.wi.data.clear.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 业务异常
 * Created by bear on 2018/1/28.
 */
public class BussException extends  RuntimeException {

    public  BussException(){
        super();
    }

    public  BussException(String msg){
        super(msg);
    }
}

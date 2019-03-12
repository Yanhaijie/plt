package com.supermoney.loan.mg.utils;

/**
 * Created by admin on 2018-01-29.
 */
public class BussException extends  RuntimeException {

    public  BussException(){
        super();
    }

    public  BussException(String msg){
        super(msg);
    }
}

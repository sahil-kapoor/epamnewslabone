package com.epam.news.exception;


public class DataBaseException extends NewsException{

    private static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public DataBaseException(String msg){
        super(msg);
    }
    public DataBaseException(String msg, Exception e){
        super(msg);
        hiddenException = e;
    }
    public Exception getHiddenException(){
        return hiddenException;
    }

}
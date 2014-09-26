package by.epam.news.service;

import by.epam.news.exception.NewsException;

public class ServiceException extends NewsException{

    private static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public ServiceException(String msg){
        super(msg);
    }
    public ServiceException(String msg, Exception e){
        super(msg);
        hiddenException = e;
    }
    public Exception getHiddenException(){
        return hiddenException;
    }

}
package by.epam.news.exception;

public class NewsException extends Exception{

    private static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public NewsException(String msg){
        super(msg);
    }
    public NewsException(String msg, Exception e){
        super(msg);
        hiddenException = e;
    }
    public Exception getHiddenException(){
        return hiddenException;
    }

}
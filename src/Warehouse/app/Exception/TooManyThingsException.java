package Warehouse.app.Exception;

public class TooManyThingsException extends Exception{
    public TooManyThingsException(String announcement){
        super(announcement);
    }
}

package Warehouse.app.Exception;

public class NeverRentException extends  Exception{
    public NeverRentException(String announcement){
        super(announcement);
    }
}

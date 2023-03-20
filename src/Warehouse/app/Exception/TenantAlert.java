package Warehouse.app.Exception;

public class TenantAlert extends Exception{
    public TenantAlert(String announcement){
        super(announcement);
    }
}

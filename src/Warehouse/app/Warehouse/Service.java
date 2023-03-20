package Warehouse.app.Warehouse;

public class Service {
    private int serviceID;
    private String name;
    private int areaNumber;
    private int warehousePercentage;
    public static int repairPlacesNumber;
    public static int serviceLocationsNumber;
    private static int repairPlaces;
    private static int serviceLocations;

    public Service(String name, int serviceID, int areaNumber, int warehousePercentage, int repairPlacesNumber, int serviceLocationsNumber){
        this.name = name;
        this.serviceID = serviceID;
        this.areaNumber = areaNumber;
        this.warehousePercentage = warehousePercentage;
        this.serviceLocationsNumber = serviceLocationsNumber;
        this.repairPlacesNumber = repairPlacesNumber;
        repairPlaces = areaNumber * warehousePercentage /100;
        serviceLocations = areaNumber -(areaNumber * warehousePercentage /100);

    }

    public static int getRepairPlaces(){
        return repairPlaces;
    }

    public static int getServiceLocations() {
        return serviceLocations;
    }

    public static int getRepairPlacesNumber() {
        return repairPlacesNumber;
    }

    public static int getServiceLocationsNumber(){return serviceLocationsNumber;}

    public String toString(){
        return "Serwis:'" + this.name +"', ID: " + this.serviceID + ", Liczba powierzchni: " + this.areaNumber + ", Magazyn stanowi: " + this.warehousePercentage +"% serwisu";
    }

}

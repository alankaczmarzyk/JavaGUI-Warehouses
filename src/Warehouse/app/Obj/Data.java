package Warehouse.app.Obj;

import Warehouse.app.Vehicle.Amphibian;
import Warehouse.app.Vehicle.CityCar;
import Warehouse.app.Warehouse.ConsumerWarehouse;
import Warehouse.app.CarSpot.CarServiceSpot;
import Warehouse.app.CarSpot.IndependentCarServiceSpot;
import Warehouse.app.Exception.NeverRentException;
import Warehouse.app.Vehicle.Motorcycle;
import Warehouse.app.Vehicle.OffRoadCar;
import Warehouse.app.Warehouse.Service;
import Warehouse.app.Warehouse.ServiceWarehouse;

import java.time.LocalDate;

public class Data {
    public static Service loadData(Service service) throws NeverRentException {
        service = new Service("First Service", 1, 10, 50, 3, 1);
        ConsumerWarehouse cw1 = new ConsumerWarehouse("ABC", 2000, 500, LocalDate.of(2022, 2, 26), LocalDate.of(2023, 3, 27));
        ConsumerWarehouse cw2 = new ConsumerWarehouse("BCD", 300, 200, LocalDate.of(2022, 11, 28), LocalDate.of(2023, 4, 11));
        ConsumerWarehouse cw3 = new ConsumerWarehouse("DEF", 300, 10, LocalDate.of(2022, 7, 11), LocalDate.of(2023, 4, 1));
        ConsumerWarehouse cw4 = new ConsumerWarehouse("DGC", 300, 10, LocalDate.of(2022, 9, 11), LocalDate.of(2023, 5, 12));
        ConsumerWarehouse cw5 = new ConsumerWarehouse("EGS", 300, 10, LocalDate.of(2022, 4, 11), LocalDate.of(2023, 8, 4));
        ServiceWarehouse sw1 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2022, 2, 26), LocalDate.of(2023, 9, 30));
        ServiceWarehouse sw2 = new ServiceWarehouse("IJK", 30, 1250, LocalDate.of(2022, 8, 8), LocalDate.of(2023, 9, 9));
        ServiceWarehouse sw3 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2022, 3, 26), LocalDate.of(2023, 9, 17));
        ServiceWarehouse sw4 = new ServiceWarehouse("IJK", 30, 1250, LocalDate.of(2022, 9, 8), LocalDate.of(2023, 9, 15));
        ServiceWarehouse sw5 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2022, 12, 26), LocalDate.of(2023, 9, 7));
        Person p1 = new Person("Adam", "Kowalski", 132323222, "Warszawa, Gorecka 5", LocalDate.of(2020, 6, 28));
        Person p2 = new Person("Paweł", "Jarosz", 232321232, "Czestochowa, Warynskiego 5", LocalDate.of(2019, 4, 6));
        Person p3 = new Person("Marek", "Wilusz", 023021202, "Warszawa, Mokotowska 5", LocalDate.of(2020, 11, 11));
        Person p4 = new Person("Adam", "Ozga", 1232320202, "Warszawa, Kielecka 25a", LocalDate.of(2021, 1, 26));
        Person p5 = new Person("Michał", "Browar", 523302102, "Raszyn, Warszawska 135", LocalDate.of(2021, 3, 7));
        ParkingSpace ps1 = new ParkingSpace("Miejsce A", 10, 200);
        ParkingSpace ps2 = new ParkingSpace("Miejsce B", 10, 200);
        ParkingSpace ps3 = new ParkingSpace("Miejsce C", 7, 150);
        Objects o1 = new Objects("rower", 20);
        Objects o2 = new Objects("pralka", 10, 5, 3);
        Objects o3 = new Objects("telewizor", 179);
        Objects o4 = new Objects("obraz", 2);
        Objects o5 = new Objects("wiadro", 2, 2, 2);
        CarServiceSpot Ics1 = new CarServiceSpot("XYZ", 100);
        CarServiceSpot Ics2 = new CarServiceSpot("ZYX", 20);
        CarServiceSpot Ics3 = new CarServiceSpot("YZX", 100);
        IndependentCarServiceSpot icss1 = new IndependentCarServiceSpot("ZZZ", 30);
        Motorcycle m1 = new Motorcycle("Ducati", "scigacz", 1000, 234);
        Amphibian a1 = new Amphibian("ISUZU", "wojskowy", 7000, 2);
        CityCar c1 = new CityCar("Toyota", "miejskie", 1200, "automatyczna");
        OffRoadCar of1 = new OffRoadCar("Nissan", "Offroadowy", 4200, "4x4");
        return service;
    }
}

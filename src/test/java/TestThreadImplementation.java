import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;

import static CreateAddService.CreateAddCarService.addCar;
import static CreateAddService.CreateAddCarService.createCar;

public class TestThreadImplementation  extends Thread {
    String vin;
    String reg;
    String brand;
    String model;
    Integer year;
    Integer mileage;

    public TestThreadImplementation(String vin, String reg, String brand, String model, Integer year, Integer mileage) {
        this.vin = vin;
        this.reg = reg;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    @Override
    public void run() {
        Car car = createCar(vin,reg,brand,model,year,mileage);
        try {
            addCar(CarList.getInstance(), car);
        } catch (NotUniqueVinException e) {
            e.printStackTrace();
        }
    }
}

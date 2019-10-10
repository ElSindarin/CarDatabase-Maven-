package CreateAddService;

import cardatabase.Car;
import cardatabase.CarList;
import ExceptionService.NotUniqueVinException;
import java.util.Objects;

public class CreateAddCarService {

    public static Car createCar(String vin, String reg, String brand, String model, Integer year, Integer mileage) {
        return new Car(vin, reg, brand, model, year, mileage);
    }

    public static CarList addCar(CarList carDatabase, Car car) throws IllegalArgumentException, NotUniqueVinException {
        if (Objects.isNull(car)) {
            throw new IllegalArgumentException("Невозможно добавить пустой элемент в базу данных");
        }
        if (carDatabase.getCarList().containsKey(car.getVin())) {
            throw new NotUniqueVinException("Автомобиль с указанным VIN-кодом уже есть в базе данных. Добавление невозможно!");
        }
        carDatabase.getCarList().put(car.getVin(), car);
        System.out.println("Автомобиль с регистрационным номером " + car.getRegNumber() + " был успешно добавлен в базу данных");
        return carDatabase;
    }
}

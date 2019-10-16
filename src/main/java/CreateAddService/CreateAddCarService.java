package CreateAddService;

import cardatabase.Car;
import cardatabase.CarList;
import ExceptionService.NotUniqueVinException;

import java.io.IOException;
import java.util.Objects;

import static Serialization.SerializationService.saveToDatabase;

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
        try {
            saveToDatabase(car);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить автомобиль на диск!");
            e.printStackTrace();
        }
        System.out.println("Автомобиль с регистрационным номером " + car.getRegNumber() + " был успешно добавлен в базу данных");
        return carDatabase;
    }
}

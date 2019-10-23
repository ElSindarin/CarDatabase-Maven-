package EditService;

import cardatabase.CarList;
import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;

import java.io.IOException;
import java.util.Objects;

import static InputService.InputService.*;
import static Serialization.SerializationService.editInDatabase;

public class EditService {

    public static CarList editCarWithCheck(CarList carDatabase, String vin, String path) throws NoSuchElementException, EmptyDataBaseException {
        if (carDatabase.getCarList().containsKey(vin)) {
            System.out.println("В базе данных найдена машина с указанным VIN-кодом. Переходим к обновлению информации");
            editCar(carDatabase, vin, inputValidatedReg(),inputValidatedBrand(),inputValidatedModel(),inputValidatedYear(),inputValidatedMileage(), path);
        } else {
            throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных!");
        }
        return carDatabase;
    }


    public static CarList editCar(CarList carDatabase, String vin, String reg, String brand, String model, Integer year, Integer mileage, String path) throws EmptyDataBaseException {
        if (Objects.isNull(carDatabase) || carDatabase.getCarList().size() == 0) {
            throw new EmptyDataBaseException("Невозможно редактировать в пустой базе данных");
        }
        carDatabase.getCarList().get(vin).setRegNumber(reg);
        carDatabase.getCarList().get(vin).setBrand(brand);
        carDatabase.getCarList().get(vin).setModel(model);
        carDatabase.getCarList().get(vin).setYear(year);
        carDatabase.getCarList().get(vin).setMileage(mileage);
        try {
            editInDatabase(carDatabase.getCarList().get(vin), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Информация об автомобиле с VIN-кодом " + vin + " была успешно отредактирована");
        return carDatabase;
    }


}


package RemoveService;

import cardatabase.Car;
import cardatabase.CarList;
import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static Serialization.SerializationService.deleleCar;
import static Serialization.SerializationService.deleteAllCars;
import static ValidityCheckService.ValidityCheckService.checkIfDataBaseEmpty;

public class RemoveService {
    public static CarList removeCar(CarList carDatabase, String vin) throws NoSuchElementException, EmptyDataBaseException {
        if (Objects.isNull(vin)) {
            throw new IllegalArgumentException("Невозможно удалить машину с несуществующим VIN-кодом");
        }
        checkIfDataBaseEmpty(carDatabase);
        if (carDatabase.getCarList().containsKey(vin)) {
            System.out.println("В базе данных найдена машина с указанным VIN-кодом. Переходим к удалению информации");
            carDatabase.getCarList().remove(vin);
            try {
                deleleCar(new File(vin + ".json"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных");
        }
        return carDatabase;

    }

    public static CarList removeAllCars(CarList carDatabase, String carDatabasePath) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        carDatabase.getCarList().clear();
        try {
            deleteAllCars(new File(carDatabasePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Вся информация была удалена из базы данных");
        return carDatabase;
    }

    public static CarList removeByYear(CarList carDatabase, Integer lowerYear, Integer upperYear, String path) throws EmptyDataBaseException, IOException, NoSuchElementException {
        checkIfDataBaseEmpty(carDatabase);
        System.out.println(path);
        for (String vin: carDatabase.getCarList().keySet()) {
            Integer year = carDatabase.getCarList().get(vin).getYear();
            if ((year >= lowerYear) && (year <= upperYear)) {
                File file = new File (path + "\\" + carDatabase.getCarList().get(vin).getVin() + ".json");
                file.delete();
            }
        }
        carDatabase.getCarList().values().removeIf(car -> (car.getYear() >= lowerYear) && (car.getYear() <= upperYear));
        return carDatabase;
    }
}

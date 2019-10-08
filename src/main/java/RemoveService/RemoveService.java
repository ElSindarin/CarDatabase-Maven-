package RemoveService;

import cardatabase.Car;
import cardatabase.CarList;
import cardatabase.EmptyDataBaseException;
import cardatabase.NoSuchElementException;
import com.hilleljavaelementary.DatabaseValidator;

import java.util.Iterator;
import java.util.Scanner;

import static ValidityCheckService.ValidityCheckService.checkIfDataBaseEmpty;

public class RemoveService {
    public static CarList removeCar(CarList carDatabase, String vin) throws NoSuchElementException, EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        if (carDatabase.getCarList().containsKey(vin)) {
            System.out.println("В базе данных найдена машина с указанным VIN-кодом. Переходим к удалению информации");
            carDatabase.getCarList().remove(vin);
        } else {
            throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных");
        }
        return carDatabase;

    }

    public static CarList removeAllCars(CarList carDatabase) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        carDatabase.getCarList().clear();
        System.out.println("Вся информация была удалена из базы данных");
        return carDatabase;
    }

    public static CarList removeByYear(CarList carDatabase, Integer lowerYear, Integer upperYear) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        carDatabase.getCarList().values().removeIf(car -> (car.getYear() >= lowerYear) && (car.getYear() <= upperYear));
        return carDatabase;
    }
}

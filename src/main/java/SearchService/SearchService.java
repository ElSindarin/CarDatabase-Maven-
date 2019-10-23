package SearchService;

import cardatabase.Car;
import cardatabase.CarList;
import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;

import static ValidityCheckService.ValidityCheckService.checkIfDataBaseEmpty;

public class SearchService {

    public static CarList searchByVIN(CarList carDatabase, String vin) throws NoSuchElementException, EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        if (carDatabase.getCarList().containsKey(vin)) {
            System.out.println("В базе данных найдена машина с указанным VIN-кодом.");
            System.out.println(carDatabase.getCarList().get(vin).toString());
        } else {
            throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных");
        }
        return carDatabase;
    }

    public static CarList searchByRegNum(CarList carDatabase, String reg) throws NoSuchElementException, EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        boolean wasFound = false;
        for (Car car : carDatabase.getCarList().values()) {
            if (car.getRegNumber().equals(reg)) {
                System.out.println("В базе данных найдена машина с указанным регистрационным номером.");
                System.out.println(car.toString());
                wasFound = true;
            }
        }
        if (!wasFound) {
            throw new NoSuchElementException("Автомобиль с указанным регистрационным номером отсутствует в базе данных");
        }
        return carDatabase;
    }

    public static CarList searchByBrandAndModel(CarList carDatabase, String brand, String model) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        for (Car car : carDatabase.getCarList().values()) {
            if (car.getBrand().equals(brand) && (car.getModel().equals(model))) {
                System.out.println(car.toString());
            }
        }
        return carDatabase;
    }

    public static CarList searchByYearRange(CarList carDatabase, Integer lowerYear, Integer upperYear) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        for (Car car : carDatabase.getCarList().values()) {
            if (car.getYear() >= lowerYear && (car.getYear() <= upperYear)) {
                System.out.println(car.toString());
            }
        }
        return carDatabase;
    }

    public static CarList searchByMileageRange(CarList carDatabase, Integer lowerMileage, Integer upperMileage) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        for (Car car : carDatabase.getCarList().values()) {
            if (car.getMileage() >= lowerMileage && (car.getMileage() <= upperMileage)) {
                System.out.println(car.toString());
            }
        }
        return carDatabase;
    }
}

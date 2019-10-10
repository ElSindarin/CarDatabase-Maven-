package SortService;

import ExceptionService.EmptyDataBaseException;
import cardatabase.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ValidityCheckService.ValidityCheckService.checkIfDataBaseEmpty;

public class SortService {

    public static CarList sortData (CarList carDatabase, Comparator<Car> carComparator) throws EmptyDataBaseException {
        checkIfDataBaseEmpty(carDatabase);
        List<Car> sortedList = new ArrayList<>(carDatabase.getCarList().values());
        Collections.sort(sortedList, carComparator);
        for (Car car: sortedList) {
            System.out.println(car.toString());
        }
        return carDatabase;
    }

    public static void sortByBrand (CarList carDatabase) {
        try {
            sortData(carDatabase, new CarBrandComparator());
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortByModel (CarList carDatabase) {
        try {
            sortData(carDatabase, new CarModelComparator());
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortByYear (CarList carDatabase) {
        try {
            sortData(carDatabase, new CarYearComparator());
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortByMileage (CarList carDatabase) {
        try {
            sortData(carDatabase, new CarMileageComparator());
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortByBrandAndModel (CarList carDatabase){
        try {
            sortData(carDatabase, new CarBrandComparator().thenComparing(new CarModelComparator()));
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

}

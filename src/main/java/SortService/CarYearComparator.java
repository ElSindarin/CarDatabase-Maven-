package SortService;
import cardatabase.Car;

import java.util.Comparator;

public class CarYearComparator implements Comparator<Car> {
    public int compare (Car a, Car b) {
        return a.getYear().compareTo(b.getYear());
    }
}

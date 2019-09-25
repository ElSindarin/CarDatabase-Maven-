package cardatabase;
import java.util.Comparator;

public class CarMileageComparator implements Comparator<Car> {
    public int compare (Car a, Car b) {
        return a.getMileage().compareTo(b.getMileage());
    }
}

package cardatabase;
import ExceptionService.EmptyDataBaseException;
import com.hilleljavaelementary.*;
import java.util.*;

public class CarList {

    private Map<String, Car> carList = new HashMap<>();

    public Map<String, Car> getCarList() {
        return carList;
    }

    public static void showCarList(CarList carDataBase) throws EmptyDataBaseException {
        if (DatabaseValidator.isCollectionEmpty(carDataBase.carList)) {
            throw new EmptyDataBaseException("Нет данных для отображения!");
        } else {
            System.out.println("По данному запросу найдены следующие автомобили");
            int i = 0;
            for (Car car : carDataBase.carList.values()) {
                System.out.print(i + 1 + ". ");
                System.out.println(car.toString());
                i++;
            }
        }
    }

    public Collection<Car> getCarDataBase () {
        return carList.values();
    }

}
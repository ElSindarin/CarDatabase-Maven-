import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;
import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static CreateAddService.CreateAddCarService.addCar;
import static RemoveService.RemoveService.removeCar;

public class TestDeleteOperations {
    CarList carDatabase = new CarList();


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_removeCar_with_null_vin_expect_IllegalArgumentException () {
        String vin = null;
        expectedException.expect(IllegalArgumentException.class);
        try {
            removeCar(carDatabase,vin);
        } catch (NoSuchElementException | EmptyDataBaseException e) {
        }
    }

    @Test
    public void test_removeCar_with_car_not_in_collection_expect_NoSuchElementException () {
        Car car = new Car("1AAAA11AAAA111111","AA1211AA","Lada","Kalina",1988,100000);
        String vin = "9BBBB11AAAA111111";
        carDatabase.getCarList().put(car.getVin(),car);
        expectedException.expect(NoSuchElementException.class);
        try {
            removeCar(carDatabase,vin);
        } catch (NoSuchElementException | EmptyDataBaseException e) {
        }
    }
}

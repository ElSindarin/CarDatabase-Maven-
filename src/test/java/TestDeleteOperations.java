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
    private CarList carDatabase = CarList.getInstance();


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_removeCar_with_null_vin_expect_IllegalArgumentException () throws NoSuchElementException, EmptyDataBaseException {
        String vin = null;
        expectedException.expect(IllegalArgumentException.class);
        removeCar(carDatabase,vin);
    }

    @Test
    public void test_removeCar_with_car_not_in_collection_expect_NoSuchElementException () throws NoSuchElementException, EmptyDataBaseException {
        Car car = new Car("1AAAA11AAAA111111","AA1211AA","Lada","Kalina",1988,100000);
        String vin = "9BBBB11AAAA111111";
        carDatabase.getCarList().put(car.getVin(),car);
        expectedException.expect(NoSuchElementException.class);
        removeCar(carDatabase,vin);
    }

    @Test()
    public void test_removeCar_empty_database_expect_EmptyDatabaseException () throws NoSuchElementException, EmptyDataBaseException {
        Car car = new Car("1AAAA11AAAA111111","AA1211AA","Lada","Kalina",1988,100000);
        expectedException.expect(EmptyDataBaseException.class);
        removeCar(carDatabase,car.getVin());
    }
}

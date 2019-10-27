import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;
import cardatabase.Car;
import cardatabase.CarList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static RemoveService.RemoveService.removeCar;

public class TestDeleteOperations extends TestAbstractCarInitialization{
    private CarList carDatabase = CarList.getInstance();

    @Before
    public void removeCars () {
        carDatabase.getCarList().clear();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_removeCar_with_null_vin_expect_IllegalArgumentException () throws NoSuchElementException, EmptyDataBaseException {
        expectedException.expect(IllegalArgumentException.class);
        removeCar(carDatabase,null);
    }

    @Test
    public void test_removeCar_with_car_not_in_collection_expect_NoSuchElementException () throws NoSuchElementException, EmptyDataBaseException {
        Car car = initCar();
        String vin = "9BBBB11AAAA111111";
        carDatabase.getCarList().put(car.getVin(),car);
        expectedException.expect(NoSuchElementException.class);
        removeCar(carDatabase,vin);
    }

    @Test()
    public void test_removeCar_empty_database_expect_EmptyDatabaseException () throws NoSuchElementException, EmptyDataBaseException {
        expectedException.expect(EmptyDataBaseException.class);
        removeCar(carDatabase,initCar().getVin());
    }
}

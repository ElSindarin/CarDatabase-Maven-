import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;
import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static CreateAddService.CreateAddCarService.addCar;
import static SearchService.SearchService.searchByVIN;

public class TestSearchOperations extends TestAbstractCarInitialization{
    private CarList carDatabase = CarList.getInstance();

    @Before
    public void removeCars () {
        carDatabase.getCarList().clear();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_searchByVIN_empty_database_expect_EmptyDatabaseException() throws NoSuchElementException, EmptyDataBaseException {
        Car car = initCar();
        expectedException.expect(EmptyDataBaseException.class);
        searchByVIN(carDatabase, car.getVin());
    }

    @Test
    public void test_searchByVIN_null_car_expect_NullPointerException() throws NoSuchElementException, EmptyDataBaseException {
        Car car = null;
        expectedException.expect(NullPointerException.class);
        searchByVIN(carDatabase, car.getVin());
    }

    @Test
    public void test_searchByVIN_valid_car_contained_in_database_expect_found () throws NotUniqueVinException, NoSuchElementException, EmptyDataBaseException {
        Car car = initCar();
        addCar(carDatabase,car);
        searchByVIN(carDatabase,car.getVin());
        Assertions.assertThat(carDatabase.getCarList()).containsKeys(car.getVin());
    }
}

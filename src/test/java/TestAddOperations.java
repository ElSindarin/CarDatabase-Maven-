import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static CreateAddService.CreateAddCarService.addCar;

public class TestAddOperations extends TestAbstractCarInitialization{

    private CarList carDataBase = CarList.getInstance();

    @Before
            public void removeCars () {
            carDataBase.getCarList().clear();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test()
    public void test_add_null_car_expect_IllegalArgumentException() throws NotUniqueVinException {
        expectedException.expect(IllegalArgumentException.class);
        addCar(carDataBase,null);
    }

    @Test()
    public void test_add_non_null_car_expect_has_this_car () throws NotUniqueVinException {
        Car car = initCar();
        addCar(carDataBase,car);
        Assert.assertThat(carDataBase.getCarDataBase(), CoreMatchers.hasItem(car));
    }

    @Test()
    public void test_add_car_already_exist_expect_NotUniqueVinException() throws NotUniqueVinException {
        addCar(carDataBase,initCar());
        expectedException.expect(NotUniqueVinException.class);
        addCar(carDataBase,initCar());
    }
}

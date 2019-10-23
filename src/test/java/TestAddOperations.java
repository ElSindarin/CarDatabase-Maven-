import ExceptionService.EmptyDataBaseException;
import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;
import org.assertj.core.condition.Not;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static CreateAddService.CreateAddCarService.addCar;

public class TestAddOperations {

    private CarList carDataBase = CarList.getInstance();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test()
    public void test_add_null_car_expect_IllegalArgumentException() throws NotUniqueVinException {
        Car car = null;
        expectedException.expect(IllegalArgumentException.class);
        addCar(carDataBase,car);
    }

    @Test()
    public void test_add_non_null_car_expect_has_this_car () throws NotUniqueVinException {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        addCar(carDataBase,car);
        Assert.assertThat(carDataBase.getCarDataBase(), CoreMatchers.hasItem(car));
    }

    @Test()
    public void test_add_car_already_exist_expect_NotUniqueVinException() throws NotUniqueVinException {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        addCar(carDataBase,car);
        expectedException.expect(NotUniqueVinException.class);
        addCar(carDataBase,car);
    }



}

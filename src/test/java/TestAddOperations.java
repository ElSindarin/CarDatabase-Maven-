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

    CarList carDataBase = new CarList();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test()
    public void test_add_null_car_expect_IllegalArgumentException() {
        Car car = null;
        expectedException.expect(IllegalArgumentException.class);
        try {
            addCar(carDataBase,car);
        } catch (NotUniqueVinException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, carDataBase.getCarDataBase().size());
    }

    @Test()
    public void test_add_non_null_car_expect_has_this_car () {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        try {
            addCar(carDataBase,car);
        } catch (NotUniqueVinException e) {
            e.printStackTrace();
        }
        Assert.assertThat(carDataBase.getCarDataBase(), CoreMatchers.hasItem(car));
    }



}

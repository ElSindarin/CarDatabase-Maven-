import cardatabase.Car;
import cardatabase.CarList;
import cardatabase.EmptyDataBaseException;
import cardatabase.NoSuchElementException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


public class TestCrudOperations {
/*
    CarList carDataBase = new CarList();

    @Test()
    public void test_add_null_car_expect_empty_database () {
        Car car = null;
        carDataBase.addCar(car);
        Assert.assertEquals(0, carDataBase.getCarDataBase().size());
    }

    @Test()
    public void test_add_non_null_car_expect_has_this_car () {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        carDataBase.addCar(car);
        Assert.assertThat(carDataBase.getCarDataBase(), CoreMatchers.hasItem(car));
    }

    @Test
    public void test_edit_car_expected_database_has_edited_car () {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        carDataBase.addCar(car);
        try {
            carDataBase.editCar(carDataBase.getCarList().get(car.getVin()).getVin());
        } catch (NoSuchElementException | EmptyDataBaseException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertThat(carDataBase.getCarList().values(), CoreMatchers.hasItem(carDataBase.getCarList().get(car.getVin())));
    }
*/
}

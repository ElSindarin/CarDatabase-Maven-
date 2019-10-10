import ExceptionService.EmptyDataBaseException;
import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static CreateAddService.CreateAddCarService.addCar;
import static EditService.EditService.editCar;
import static cardatabase.CarList.showCarList;

public class TestEditOperations {

    CarList carDataBase = new CarList();



    @Test
    public void test_edit_car_expected_database_has_edited_car () {
        Car car = new Car("1AAAA11AAAA111111","AA1211AA","Lada","Kalina",1988,100000);
        try {
            addCar(carDataBase,car);
        } catch (NotUniqueVinException e) {
            e.printStackTrace();
        }
        editCar(carDataBase, car.getVin(),"BB1111BB","NeLada","NeKalina",1900,5555);
        Assert.assertThat(carDataBase.getCarList().values(), CoreMatchers.hasItem(carDataBase.getCarList().get(car.getVin())));
        try {
            showCarList(carDataBase);
        } catch (EmptyDataBaseException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test ()
    public void test_edit_null_car_expect_NullPointerException() {
        Car car = null;
        expectedException.expect(NullPointerException.class);
        editCar(carDataBase, car.getVin(),car.getRegNumber(),car.getBrand(),car.getModel(),car.getYear(),car.getMileage());
    }
}

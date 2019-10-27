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

public class TestEditOperations extends TestAbstractCarInitialization{

    private CarList carDataBase = CarList.getInstance();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_edit_car_expected_database_has_edited_car () throws EmptyDataBaseException, NotUniqueVinException {
        Car car = initCar();
        addCar(carDataBase,car);
        editCar(carDataBase, car.getVin(),"BB1111BB","NeLada","NeKalina",1900,5555, "C:\\Users\\Admin\\IdeaProjects\\cardatabase\\src\\main\\java\\cardatabase");
        Assert.assertThat(carDataBase.getCarList().values(), CoreMatchers.hasItem(carDataBase.getCarList().get(car.getVin())));
        showCarList(carDataBase);
    }

    @Test ()
    public void test_edit_null_car_expect_NullPointerException() throws EmptyDataBaseException {
        Car car = null;
        expectedException.expect(NullPointerException.class);
        editCar(carDataBase, car.getVin(),car.getRegNumber(),car.getBrand(),car.getModel(),car.getYear(),car.getMileage(), "C:\\Users\\Admin\\IdeaProjects\\cardatabase\\src\\main\\java\\cardatabase");
    }

    @Test()
    public void test_edit_car_empty_database_expect_EmptyDatabaseException () throws EmptyDataBaseException {
        Car car = initCar();
        expectedException.expect(EmptyDataBaseException.class);
        editCar(carDataBase,car.getVin(),car.getRegNumber(),car.getBrand(),car.getModel(),car.getYear(),car.getMileage(), "C:\\Users\\Admin\\IdeaProjects\\cardatabase\\src\\main\\java\\cardatabase");
    }
}

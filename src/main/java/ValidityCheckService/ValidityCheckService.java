package ValidityCheckService;
import cardatabase.CarList;
import ExceptionService.EmptyDataBaseException;
import com.hilleljavaelementary.DatabaseValidator;

public class ValidityCheckService {

    public static boolean checkVinValidity (String vin) {
        return DatabaseValidator.isVINValidated(vin);
    }

    public static boolean checkRegNumberValidity (String reg) {
        return DatabaseValidator.isRegNumberValidated(reg);
    }

    public static boolean checkBrandValidity (String brand) {
        return !DatabaseValidator.isStringEmpty(brand);
    }

    public static boolean checkModelValidity (String model) {
        return !DatabaseValidator.isStringEmpty(model);
    }

    public static boolean checkYearValidity (Integer year) {
        return ((year >= 1900 && year <= 2019) || (year == 0));
    }

    public static boolean checkMileageValidity (Integer mileage) {
        return (mileage >=0 && mileage <= 2000000);
    }

    public static void checkIfDataBaseEmpty (CarList carList) throws EmptyDataBaseException {
        if (DatabaseValidator.isCollectionEmpty(carList.getCarList())) {
            throw new EmptyDataBaseException("Нет данных для отображения!");
        }
    }
}

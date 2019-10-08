package InputService;
import java.util.Scanner;

import static ValidityCheckService.ValidityCheckService.*;

public class InputService {

    public static String inputStringParameter (String parameterDescription) {
        System.out.println(parameterDescription);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static Integer inputIntegerParameter (String parameterDescription) {
        System.out.println(parameterDescription);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String inputValidatedVin () {
        String vin = inputStringParameter("Введите VIN-код автомобиля. Формат кода: цифра, 4 больших лат. буквы, 2 цифры, 4 больших лат. буквы, 6 цифр:");
        if (!checkVinValidity(vin)) {
            System.out.println("Введён некорректный VIN-код. Повторите попытку :(");
            vin = inputValidatedVin();
        }
        return vin;
    }

    public static String inputValidatedReg () {
        String reg = inputStringParameter("Введите регистрационный номер автомобиля в формате 2 больших лат. буквы, 4 цифры, 2 больших лат. буквы:");
        while (!checkRegNumberValidity(reg)) {
            System.out.println("Недопустимый формат регистрационного номера. Необходимо повторить процедуру ввода.");
            reg = inputValidatedReg();
        }
        return reg;
    }

    public static String inputValidatedBrand () {
        String brand = inputStringParameter("Введите марку автомобиля:");
        while (!checkBrandValidity(brand)) {
            System.out.println("Поле не может быть пустым. Необходимо повторить процедуру ввода.");
            brand = inputValidatedBrand();
        }
        return brand;
    }

    public static String inputValidatedModel () {
        String model = inputStringParameter("Введите модель автомобиля:");
        while (!checkModelValidity(model)) {
            System.out.println("Поле не может быть пустым. Необходимо повторить процедуру ввода.");
            model = inputValidatedModel();
        }
        return model;
    }

    public static Integer inputValidatedYear () {
        Integer year = inputIntegerParameter("Введите год выпуска (от 1900 до 2019):");
        while (!checkYearValidity(year)) {
            System.out.println("Некорректный формат года выпуска. Необходимо повторить процедуру ввода.");
            year = inputValidatedYear();
        }
        return year;
    }

    public static Integer inputValidatedMileage () {
        Integer mileage = inputIntegerParameter("Введите пробег (от 0 до 2 000 000):");
        while (!checkMileageValidity(mileage)) {
            System.out.println("Некорректный формат пробега. Необходимо повторить процедуру ввода.");
            mileage = inputValidatedMileage();
        }
        return mileage;
    }
}

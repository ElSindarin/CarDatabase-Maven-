package ExportImportService;

import ExceptionService.NotUniqueVinException;
import cardatabase.Car;
import cardatabase.CarList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static CreateAddService.CreateAddCarService.addCar;
import static CreateAddService.CreateAddCarService.createCar;
import static ValidityCheckService.ValidityCheckService.*;

public class ImportService {
    private static Logger log = Logger.getLogger(ImportService.class.getName());

    public static CarList ImportFromCSV (CarList carDatabase, String input) throws IOException, NotUniqueVinException, IllegalArgumentException {
        log.setLevel(Level.ALL);
        Path path = validateImportPath(input);
        List<String> lineList = Files.readAllLines(path);
        for (String line: lineList) {
            String [] carInput = line.split(",");
            if (!carDatabase.getCarList().containsKey(carInput[0])) {
                validateCarInput(carInput);
                addCar(carDatabase, validateCarInput(carInput));
                log.log(Level.INFO, "Добавление в базу данных. Информация об авто: " + carInput.toString());
            } else {
                log.log(Level.WARNING, "Неуспешная попытка добавления автомобиля в базу данных.");
            }
        }

        return carDatabase;
    }

    public static Path validateImportPath (String input) throws IOException, IllegalArgumentException {
        Path path = Paths.get(input);
        if (!path.toFile().exists() ) {
            String msg = "Не существует файла с указанным адресом!";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!path.toFile().isFile() || !path.toFile().getCanonicalPath().endsWith(".csv")) {
            String msg = "Введённый путь не является файлом в CSV-формате!";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }

        return path;
    }

    public static Car validateCarInput (String[] carInput) throws IllegalArgumentException {
        if (carInput.length != 7) {
            String msg = "В файле содержится строка, которая не является правильно записанными данными об автомобиле!";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkVinValidity(carInput[1])) {
            String msg = "VIN-код несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkRegNumberValidity(carInput[2])) {
            String msg = "Регистрационный номер несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkBrandValidity(carInput[3])) {
            String msg = "Марка несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkModelValidity(carInput[4])) {
            String msg = "Модель несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkYearValidity(Integer.valueOf(carInput[5]))) {
            String msg = "Год выпуска несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        if (!checkMileageValidity(Integer.valueOf(carInput[6]))) {
            String msg = "Пробег несоответствующего формата";
            log.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
        return createCar(carInput[1],carInput[2],carInput[3],carInput[4],Integer.valueOf(carInput[5]),Integer.valueOf(carInput[6]));
    }
}

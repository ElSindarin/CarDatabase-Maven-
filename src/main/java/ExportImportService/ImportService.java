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
            throw new IllegalArgumentException("Не существует файла с указанным адресом!");
        }
        if (!path.toFile().isFile() || !path.toFile().getCanonicalPath().endsWith(".csv")) {
            throw new IllegalArgumentException("Введённый путь не является файлом в CSV-формате!");
        }

        return path;
    }

    public static Car validateCarInput (String[] carInput) throws IllegalArgumentException {
        if (carInput.length != 7) {
            throw new IllegalArgumentException("В файле содержится строка, которая не является правильно записанными данными об автомобиле!");
        }
        if (!checkVinValidity(carInput[1])) {
            throw new IllegalArgumentException("VIN-код несоответствующего формата");
        }
        if (!checkRegNumberValidity(carInput[2])) {
            throw new IllegalArgumentException("Регистрационный номер несоответствующего формата");
        }
        if (!checkBrandValidity(carInput[3])) {
            throw new IllegalArgumentException("Марка несоответствующего формата");
        }
        if (!checkModelValidity(carInput[4])) {
            throw new IllegalArgumentException("Модель несоответствующего формата");
        }
        if (!checkYearValidity(Integer.valueOf(carInput[5]))) {
            throw new IllegalArgumentException("Год выпуска несоответствующего формата");
        }
        if (!checkMileageValidity(Integer.valueOf(carInput[6]))) {
            throw new IllegalArgumentException("Пробег несоответствующего формата");
        }
        return createCar(carInput[1],carInput[2],carInput[3],carInput[4],Integer.valueOf(carInput[5]),Integer.valueOf(carInput[6]));
    }
}

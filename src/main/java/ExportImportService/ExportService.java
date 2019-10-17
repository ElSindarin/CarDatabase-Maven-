package ExportImportService;

import cardatabase.Car;
import cardatabase.CarList;

import javax.naming.InsufficientResourcesException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ExportService {

    public static void exportToCSV (CarList carDatabase, String input) throws IOException, InsufficientResourcesException, IllegalArgumentException {
        Path path = Paths.get(input);
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Введённый путь не является директорией!");
        }
        File file = new File(input + "\\database.csv");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        file.setReadable(true);
        file.setWritable(true);
        if (file.getUsableSpace() - getDatabaseSizeInBytes(carDatabase) < 0) {
            throw new InsufficientResourcesException("На диске недостаточно места, чтобы записать базу данных в CSV-файл");
        }
        FileWriter fileWriter = new FileWriter(file.getCanonicalPath());
        for (Map.Entry<String, Car> entry: carDatabase.getCarList().entrySet()) {
            fileWriter.append(entry.getKey());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getVin());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getRegNumber());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getBrand());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getModel());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getYear().toString());
            fileWriter.append(',');
            fileWriter.append(entry.getValue().getMileage().toString());
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println(carDatabase.getCarList().size() + " авто экспортированы в CSV-файл");
    }
    
    public static Long getDatabaseSizeInBytes (CarList carDatabase) {
        Long totalBytes = 0L;
        for (Map.Entry<String, Car> entry: carDatabase.getCarList().entrySet()) {
            totalBytes += entry.getKey().getBytes().length +
                    entry.getValue().getVin().getBytes().length +
                    entry.getValue().getRegNumber().getBytes().length +
                    entry.getValue().getBrand().getBytes().length +
                    entry.getValue().getModel().getBytes().length +
                    entry.getValue().getYear().toString().getBytes().length +
                    entry.getValue().getMileage().toString().getBytes().length;
        }
        return totalBytes;
    }
}

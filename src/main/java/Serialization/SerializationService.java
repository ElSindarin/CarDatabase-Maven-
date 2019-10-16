package Serialization;

import cardatabase.Car;
import cardatabase.CarList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Objects;

public class SerializationService {

    public static void saveToDatabase (Car car) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileOutputStream os = new FileOutputStream(car.getVin() + ".json");
        os.write(mapper.writeValueAsBytes(car));
    }

    public static void readFromDatabase (CarList carDatabase, String path) throws IOException {
        File folder = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        if (folder.isDirectory()) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.getCanonicalPath().endsWith(".json")) {
                    FileInputStream is = new FileInputStream(file.getName());
                    Car car = mapper.readValue(is, Car.class);
                    carDatabase.getCarList().put(car.getVin(), car);
                }
            }
        }
    }

    public static void deleleCar (File file) throws IOException {
        System.out.println(file.getCanonicalPath());
        if (file.getCanonicalPath().endsWith(".json")) {
            if (file.delete()) {
                System.out.println("Данные об автомобиле удалены из хранилища данных");
            } else {
                throw new NoSuchFileException("Данный файл отсутствует в хранилище данных");
            }
        } else {
            throw new IOException("Данный файл не является данными базы данных и не может быть удалён!");
        }
    }

    public static void deleteAllCars (File folder) throws IOException {
        for (File file: folder.listFiles()) {
            if (file.getCanonicalPath().endsWith(".json")) {
                file.delete();
            }
        }
    }

    public static void editInDatabase (Car car, String path) throws IOException {
        File file = new File(path + "\\" + car.getVin());
        file.delete();
        saveToDatabase(car);
    }
}

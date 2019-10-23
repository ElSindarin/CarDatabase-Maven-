package cardatabase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class Car implements Serializable {

    public Car(@JsonProperty("vin") String vin, @JsonProperty("regNumber") String regNumber, @JsonProperty("brand") String brand, @JsonProperty("model") String model, @JsonProperty("year") Integer year, @JsonProperty("mileage") Integer mileage) {
        this.vin = vin;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    @Getter
    private final String vin;
    @Getter
    @Setter
    private String regNumber;
    @Getter
    @Setter
    private String brand;
    @Getter
    @Setter
    private String model;
    @Getter
    @Setter
    private Integer year;
    @Getter
    @Setter
    private Integer mileage;

    @Override
    public String toString() {
        return "VIN-код: " + vin +
                ", Рег. №: " + regNumber +
                ", Марка: " + brand +
                ", Модель: " + model +
                ", Год выпуска: " + year +
                ", Пробег: " + mileage;
    }
}

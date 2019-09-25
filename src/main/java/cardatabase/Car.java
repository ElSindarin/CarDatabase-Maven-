package cardatabase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Car {
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

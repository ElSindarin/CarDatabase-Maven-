package cardatabase;
public class Car {
    private final String vin;
    private String regNumber;
    private String brand;
    private String model;
    private Integer year;
    private Integer mileage;

    public Car(String vin, String regNumber, String brand, String model, Integer year, Integer mileage) {
        this.vin = vin;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return  "VIN-код: " + vin +
                ", Рег. №: " + regNumber +
                ", Марка: " + brand +
                ", Модель: " + model +
                ", Год выпуска: " + year +
                ", Пробег: " + mileage;
    }
}

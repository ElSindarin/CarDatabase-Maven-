package cardatabase;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarList {

    private Map<String, Car> carList = new HashMap<>();

    private Car createCar() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        String vin;
        System.out.println("Введите VIN-код автомобиля");
        vin = sc.nextLine();
        return assignDataToVin(vin);
    }

    public void addCar() {
        System.out.println("Для добавления нового автомобиля в базу данных, необходимо ввести данные.");
        try {
            Car car = createCar();
            carList.put(car.getVin(), car);
            System.out.println("Автомобиль с регистрационным номером " + car.getRegNumber() + " был успешно добавлен в базу данных");
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат ввода данных. Повторите всю процедуру заново");
            addCar();
        }

    }

    public void editCar() throws NoSuchElementException {
        System.out.println("Для редактирования информации об автомобиле введите VIN-код (либо введите 0, чтобы вернуться в меню):");
        String regNumber;
        Scanner sc = new Scanner(System.in);
        regNumber = sc.nextLine();
        if (!regNumber.equals("0")) {
            if (carList.containsKey(regNumber)) {
                System.out.println("В базе данных найдена машина с указанным VIN-кодом. Переходим к обновлению информации");
                String reg = checkValidity();
                carList.get(regNumber).setRegNumber(reg);
                System.out.println("Введите марку автомобиля:");
                carList.get(regNumber).setBrand(sc.nextLine());
                System.out.println("Введите модель автомобиля:");
                carList.get(regNumber).setModel(sc.nextLine());
                System.out.println("Введите год выпуска автомобиля:");
                carList.get(regNumber).setYear(sc.nextInt());
                System.out.println("Введите пробег автомобиля:");
                carList.get(regNumber).setMileage(sc.nextInt());
            } else {
                throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных!");
            }
            editCar();
        } else {
            return;
        }
    }

    // Методы удаления

    public void removeCar() throws NoSuchElementException {
        System.out.println("Для удаления информации об автомобиле введите VIN-код (либо введите 0, чтобы вернуться в меню:");
        String regNumber;
        Scanner sc = new Scanner(System.in);
        regNumber = sc.nextLine();
        if (!regNumber.equals("0")) {
            if (carList.containsKey(regNumber)) {
                System.out.println("В базе данных найдена машина с указанным VIN-кодом. Переходим к удалению информации");
                carList.remove(regNumber);
                removeCar();
            }
            else {
                throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных");
            }
            removeCar();
        } else {
            return;
        }
    }

    public void removeAllCars () {
        carList.clear();
        System.out.println("Вся информация была удалена из базы данных");
    }

    public void removeByYear () {
        System.out.println("Для удаления информации об автомобиле по году выпуска введите год выпуска, с которого хотите начать удаление (либо введите 0, чтобы вернуться в меню):");
        Integer lowerYear, upperYear;
        Scanner sc = new Scanner(System.in);
        lowerYear = sc.nextInt();
        if (lowerYear != 0) {
            System.out.println("Теперь введите год, на котором хотите завершить удаление:");
            upperYear = sc.nextInt();
            for (Iterator<Car> iterator = carList.values().iterator();iterator.hasNext();) {
                Car car = iterator.next();
                if ((car.getYear() >= lowerYear) && (car.getYear() <= upperYear)) {
                    iterator.remove();
                }
            }
            removeByYear();
        } else {
            return;
        }

    }

    // Поисковые методы

    public void searchCarByVIN() throws NoSuchElementException {
        System.out.println("Для нахождения информации об автомобиле по VIN-коду введите VIN-код (либо введите 0, чтобы вернуться в меню)");
        String regNumber;
        Scanner sc = new Scanner(System.in);
        regNumber = sc.nextLine();
        if (!regNumber.equals("0")) {
            if (carList.containsKey(regNumber)) {
                System.out.println("В базе данных найдена машина с указанным VIN-кодом.");
                System.out.println(carList.get(regNumber).toString());
                searchCarByVIN();
            } else {
                throw new NoSuchElementException("Автомобиль с указанным VIN-кодом отсутствует в базе данных");
            }
            searchCarByVIN();
        } else {
            return;
        }
    }

    public void searchCarByRegNum() throws NoSuchElementException {
        System.out.println("Для нахождения информации об автомобиле по регистрационному номеру введите регистрационный номер (либо введите 0, чтобы вернуться в меню)");
        String regNumber;
        boolean wasFound = false;
        Scanner sc = new Scanner(System.in);
        regNumber = sc.nextLine();
        if (!regNumber.equals("0")) {
            for (Car car : carList.values()) {
                if (car.getRegNumber().equals(regNumber)) {
                    System.out.println("В базе данных найдена машина с указанным регистрационным номером.");
                    System.out.println(car.toString());
                    wasFound = true;
                }
            }
            if (!wasFound){
                throw new NoSuchElementException("Автомобиль с указанным регистрационным номером отсутствует в базе данных");
            }
            searchCarByRegNum();
        } else {
            return;
        }
    }

    public void searchCarByMarkAndModel() {
        CarList searchResult = new CarList();
        String brand, model;
        Scanner sc = new Scanner(System.in);
        System.out.println("Для поиска автомобиля по марке и модели введите марку (либо введите 0, чтобы вернуться в меню):");
        brand = sc.nextLine();
        if (!brand.equals("0")) {
            System.out.println("Теперь введите модель:");
            model = sc.nextLine();
            for(Car car : carList.values()) {
                if (car.getBrand().equals(brand) && (car.getModel().equals(model))) {
                    searchResult.carList.put(car.getVin(),car);
                }
            }
            try {
                searchResult.showCarList(searchResult);
            } catch (EmptyDataBaseException e) {
                System.out.println(e.getMessage());
            }
            searchCarByMarkAndModel();
        } else {
            return;
        }
    }

    public void searchCarByYearRange() {
        CarList searchResult = new CarList();
        Integer lowerYear, upperYear;
        Scanner sc = new Scanner(System.in);
        System.out.println("Для поиска автомобиля по году выпуска введите год, с которого хотите начать фильтр (либо введите 0, чтобы вернуться в меню):");
        lowerYear = sc.nextInt();
        if (lowerYear != 0) {
            System.out.println("Теперь введите год, которым хотите завершить фильтр:");
            upperYear = sc.nextInt();
            for (Car car : carList.values()) {
                if (car.getYear() >= lowerYear && (car.getYear() <= upperYear)) {
                    searchResult.carList.put(car.getVin(), car);
                }
            }
            try {
                searchResult.showCarList(searchResult);
            } catch (EmptyDataBaseException e) {
                System.out.println(e.getMessage());
            }
            searchCarByYearRange();
        } else {
            return;
        }
    }

    public void searchCarByMileage() {
        CarList searchResult = new CarList();
        Integer lowerMileage, upperMileage;
        Scanner sc = new Scanner(System.in);
        System.out.println("Для поиска автомобиля по величине пробега введите показания одометра, с которых хотите начать фильтр (либо введите 0, чтобы вернуться в меню):");
        lowerMileage = sc.nextInt();
        if (lowerMileage != 0) {
            System.out.println("Теперь введите показания одометра, которыми хотите завершить фильтр:");
            upperMileage = sc.nextInt();
            for (Car car : carList.values()) {
                if (car.getMileage() >= lowerMileage && (car.getMileage() <= upperMileage)) {
                    searchResult.carList.put(car.getVin(),car);
                }
            }
            try {
                searchResult.showCarList(searchResult);
            } catch (EmptyDataBaseException e) {
                System.out.println(e.getMessage());
            }
            searchCarByMileage();
        } else {
            return;
        }
    }

    // Методы сортировок

    public void sortData (Comparator<Car> carComparator) {
        List<Car> sortedList = new ArrayList<>(carList.values());
        Collections.sort(sortedList, carComparator);
        for (Car car: sortedList) {
            System.out.println(car.toString());
        }
    }

    public void sortByBrand () {
        sortData(new CarBrandComparator());
    }

    public void sortByModel () {
        sortData(new CarModelComparator());
    }

    public void sortByYear () {
        sortData(new CarYearComparator());
    }

    public void sortByMileage () {
        sortData(new CarMileageComparator());
    }

    public void sortByBrandAndModel (){
        sortData(new CarBrandComparator().thenComparing(new CarModelComparator()));
    }

    // Утилитные методы

    public void showCarList(CarList carDataBase) throws EmptyDataBaseException{
        if (carDataBase.carList.size() == 0) {
            throw new EmptyDataBaseException("Нет данных для отображения!");
        } else {
            System.out.println("По данному запросу найдены следующие автомобили");
            int i = 0;
            for (Car car : carList.values()) {
                System.out.print(i + 1 + ". ");
                System.out.println(car.toString());
                i++;
            }
        }
    }

    public static Car assignDataToVin (String vin) {
        Scanner sc = new Scanner(System.in);
        String brand, model;
        Integer year;
        Integer mileage;
        String reg = checkValidity();
        System.out.println("Введите марку автомобиля:");
        brand = sc.nextLine();
        System.out.println("Введите модель автомобиля:");
        model = sc.nextLine();
        System.out.println("Введите год выпуска автомобиля:");
        year = sc.nextInt();
        System.out.println("Введите пробег автомобиля:");
        mileage = sc.nextInt();
        return new Car(vin, reg, brand, model, year, mileage);
    }

    public static String checkValidity () {
        String reg;
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(^[A-Z]{2}\\d{4}[A-Z]{2}$)|(^[А-Я]{2}\\d{4}[А-Я]{2}$)");
        Matcher matcher;
        do {
            System.out.println("Введите регистрационный номер автомобиля:");
            reg = sc.nextLine();
            matcher = pattern.matcher(reg);
            if (!matcher.find(0)) {
                System.out.println("Недопустимый формат регистрационного номера. Необходимо повторить процедуру ввода.");
            }
        } while (!matcher.find(0));
        return reg;
    }

}
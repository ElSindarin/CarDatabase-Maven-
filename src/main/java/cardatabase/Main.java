package cardatabase;

import ExceptionService.EmptyDataBaseException;
import ExceptionService.NoSuchElementException;
import ExceptionService.NotUniqueVinException;

import java.io.IOException;
import java.util.Scanner;

import static CreateAddService.CreateAddCarService.*;
import static EditService.EditService.editCarWithCheck;
import static InputService.InputService.*;
import static RemoveService.RemoveService.*;
import static SearchService.SearchService.*;
import static Serialization.SerializationService.readFromDatabase;
import static SortService.SortService.*;
import static ValidityCheckService.ValidityCheckService.checkIfDataBaseEmpty;
import static cardatabase.CarList.showCarList;

public class Main {

    public static void main(String[] args) {
        CarList carDataBase = new CarList();
        String carDataBasePath = loadDatabase(carDataBase);
        callMainMenu(carDataBase, carDataBasePath);
    }

    public static void callMainMenu(CarList carDataBase, String path) {
        System.out.println("Добро пожаловать в меню по работе с базой данных автомобилей!");
        byte number = 0;
        Scanner sc = new Scanner(System.in);
        mainMenu:
        while (number != -1) {
            showMainMenuText();
            number = sc.nextByte();
            switch (number) {
                case 1: {

                    callSearchMenu(carDataBase);
                    break;
                }
                case 2: {
                    try {
                        System.out.println("Открыто меню добавления автомобиля в базу данных. Чтобы выйти из меню, вместо VIN-кода введите 0");
                        String vin = inputValidatedVin();
                        if (vin.equals("0")) {
                            break;
                        }
                        String reg = inputValidatedReg();
                        String brand = inputValidatedBrand();
                        String model = inputValidatedModel();
                        Integer year = inputValidatedYear();
                        Integer mileage = inputValidatedMileage();
                        addCar(carDataBase, createCar(vin, reg, brand, model, year, mileage));
                    } catch (NotUniqueVinException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    String vin;
                    try {
                        checkIfDataBaseEmpty(carDataBase);
                        do {
                            System.out.println("Для редактирования информации об автомобиле введите VIN-код (либо введите 0, чтобы вернуться в меню):");
                            vin = inputValidatedVin();
                            if (!vin.equals("0")) {
                                carDataBase = editCarWithCheck(carDataBase, vin, path);
                            }
                        } while (!vin.equals("0"));

                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    callDeleteMenu(carDataBase, path);
                    break;
                }
                case 5: {
                    callSortMenu(carDataBase);
                    break;
                }
                case 0: {
                    continue mainMenu;
                }
                case -1: {
                    return;
                }
                default: {
                    System.out.println("Введённая вами опция не существует!");
                    break;
                }
            }
        }
    }

    public static void callSearchMenu(CarList carDataBase) {
        try {
            checkIfDataBaseEmpty(carDataBase);
        } catch (EmptyDataBaseException e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner sc = new Scanner(System.in);
        byte number = 0;
        while (number != -1) {
            showSearchMenu();
            number = sc.nextByte();
            switch (number) {
                case 1: {
                    String vin;
                    try {
                        do {
                            System.out.println("Для нахождения информации об автомобиле по VIN-коду введите VIN-код (либо введите 0, чтобы вернуться в меню)");
                            vin = inputValidatedVin();
                            searchByVIN(carDataBase, vin);
                        } while (!vin.equals("0"));
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    String reg;
                    try {
                        do {
                            System.out.println("Для нахождения информации об автомобиле по регистрационному номеру введите регистрационный номер (либо введите 0, чтобы вернуться в меню)");
                            reg = inputValidatedReg();
                            searchByRegNum(carDataBase, reg);
                        } while (!reg.equals("0"));
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    String brand, model;
                    try {
                        do {

                            System.out.println("Для поиска автомобиля по марке и модели введите марку (либо введите 0, чтобы вернуться в меню):");
                            brand = inputValidatedBrand();
                            if (!brand.equals("0")) {
                                System.out.println("Теперь введите модель:");
                                model = inputValidatedModel();
                                searchByBrandAndModel(carDataBase, brand, model);
                            }
                        } while (!brand.equals("0"));
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    try {
                        Integer lowerYear, upperYear;
                        do {
                            System.out.println("Для поиска автомобиля по году выпуска введите год, с которого хотите начать фильтр (либо введите 0, чтобы вернуться в меню):");
                            lowerYear = inputValidatedYear();
                            if (!(lowerYear == 0)) {
                                System.out.println("Теперь введите год, которым хотите завершить фильтр:");
                                upperYear = inputValidatedYear();
                                searchByYearRange(carDataBase, lowerYear, upperYear);
                            }
                        } while (lowerYear != 0);
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    try {
                        Integer lowerMileage, upperMileage;
                        do {
                            System.out.println("Для поиска автомобиля по величине пробега введите показания одометра, с которых хотите начать фильтр (либо введите 0, чтобы вернуться в меню):");
                            lowerMileage = inputValidatedMileage();
                            if (lowerMileage != 0) {
                                System.out.println("Теперь введите показания одометра, которыми хотите завершить фильтр:");
                                upperMileage = inputValidatedMileage();
                                searchByMileageRange(carDataBase, lowerMileage, upperMileage);
                            }
                        } while (lowerMileage != 0);
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 7: {
                    try {
                        showCarList(carDataBase);
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Выбранная вами опция не существует!");
                    break;
                }
            }
        }

    }

    public static void callDeleteMenu(CarList carDataBase, String path) {
        Scanner sc = new Scanner(System.in);
        byte number = 0;
        while (number != -1) {
            showDeleteMenu();
            number = sc.nextByte();
            switch (number) {
                case 1: {
                    try {
                        String vin;
                        do {
                            System.out.println("Для удаления информации об автомобиле введите VIN-код (либо введите 0, чтобы вернуться в меню:");
                            vin = inputValidatedVin();
                            if (!vin.equals("0")) {
                                removeCar(carDataBase, vin);
                            }
                        } while (!vin.equals("0"));
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        Integer lowerYear, upperYear;
                        do {
                            System.out.println("Для удаления информации об автомобиле по году выпуска введите год выпуска, с которого хотите начать удаление (либо введите 0, чтобы вернуться в меню):");
                            lowerYear = inputValidatedYear();
                            if (lowerYear != 0) {
                                System.out.println("Теперь введите год, на котором хотите завершить удаление:");
                                upperYear = sc.nextInt();
                                removeByYear(carDataBase, lowerYear, upperYear, path);
                            }
                        } while (lowerYear != 0);
                    } catch (EmptyDataBaseException | NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    try {
                        removeAllCars(carDataBase, path);
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Выбранная вами опция не существует!");
                    break;
                }
            }
        }
    }

    public static void callSortMenu(CarList carDataBase) {
        Scanner sc = new Scanner(System.in);
        byte number = 0;
        while (number != -1) {
            showSortMenu();
            number = sc.nextByte();
            switch (number) {
                case 1: {
                    sortByBrand(carDataBase);
                    break;
                }
                case 2: {
                    sortByModel(carDataBase);
                    break;
                }
                case 3: {
                    sortByYear(carDataBase);
                    break;
                }
                case 4: {
                    sortByMileage(carDataBase);
                    break;
                }
                case 5: {
                    sortByBrandAndModel(carDataBase);
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Выбранная вами опция не существует!");
                    break;
                }
            }
        }
    }

    public static void showMainMenuText() {
        System.out.println("Выберите опцию и введите соответствующее число:");
        System.out.println("1 - Открыть меню поиска");
        System.out.println("2 - Внести автомобиль в базу данных");
        System.out.println("3 - Редактировать информацию об автомобиле по VIN-коду");
        System.out.println("4 - Открыть меню удаления информации");
        System.out.println("5 - Открыть меню сортировки");
        System.out.println("0 - Вернуться в предыдущее меню");
        System.out.println("-1 - Завершить программу");
    }

    public static void showSearchMenu() {
        System.out.println("Меню поиска:");
        System.out.println("1 - Поиск по VIN-коду автомобиля");
        System.out.println("2 - Поиск по регистрационному номеру автомобиля");
        System.out.println("3 - Поиск по марке и модели автомобиля");
        System.out.println("4 - Поиск по году выпуска автомобиля (от ... и до ... включительно)");
        System.out.println("5 - Поиск по величине пробега (от ... и до ... включительно)");
        System.out.println("7 - Отобразить список всех машин");
        System.out.println("0 - Вернуться в предыдущее меню");
    }

    public static void showDeleteMenu() {
        System.out.println("Меню удаления:");
        System.out.println("1 - Удаление автомобиля по VIN-коду");
        System.out.println("2 - Удаление автомобиля по году выпуска от ... и до ...");
        System.out.println("3 - Очистка всей базы данных автомобилей");
        System.out.println("0 - Вернуться в предыдущее меню");
    }

    public static void showSortMenu() {
        System.out.println("Активировано меню сортировки:");
        System.out.println("1 - Сортировать по марке автомобиля");
        System.out.println("2 - Сортировать по модели автомобиля");
        System.out.println("3 - Сортировать по году выпуска автомобиля");
        System.out.println("4 - Сортировать по пробегу автомобиля");
        System.out.println("5 - Сортировать по марке, а затем по модели автомобиля");
        /*
        System.out.println("1 - Добавить поле сортировки по марке автомобиля");
        System.out.println("2 - Добавить поле сортировки по модели автомобиля");
        System.out.println("3 - Добавить поле сортировки по году выпуска автомобиля");
        System.out.println("4 - Добавить поле сортировки по пробегу автомобиля");
        System.out.println("5 - Выполнить сортировку");*/
        System.out.println("0 - Вернуться в предыдущее меню");
    }

    public static String loadDatabase (CarList carDataBase) {
        String carDataBasePath = "C:\\Users\\Admin\\IdeaProjects\\cardatabase";
        System.out.println("Введите адрес, по которому у вас расположен каталог с файлами базы данных, либо нажмите 0, чтобы использовать путь по умолчанию");
        Scanner scanner = new Scanner(System.in);
        String path;
        if (!(path = scanner.nextLine()).equals("0")) {
            carDataBasePath = path;
        }
        try {
            readFromDatabase(carDataBase, carDataBasePath);
        } catch (IOException e) {
            System.out.println("Не удалось загрузить базу данных с диска!");
            e.printStackTrace();
        }
        return carDataBasePath;
    }
}

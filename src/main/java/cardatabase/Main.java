package cardatabase;
import com.hilleljavaelementary.DatabaseValidator;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        CarList carDataBase = new CarList();
        callMainMenu(carDataBase);
    }

    public static void callMainMenu (CarList carDataBase) {
        System.out.println("Добро пожаловать в меню по работе с базой данных автомобилей!");
        byte number = 0;
        Scanner sc = new Scanner(System.in);
        mainMenu: while (number != -1) {
            showMainMenuText();
            number = sc.nextByte();
            switch (number) {
                case 1: {

                    callSearchMenu(carDataBase);
                    break;
                }
                case 2: {
                    carDataBase.addCar();
                    break;
                }
                case 3: {
                    try {
                        carDataBase.editCar();
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    callDeleteMenu(carDataBase);
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
            carDataBase.checkIfDataBaseEmpty();
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
                    try {
                        carDataBase.searchCarByVIN();
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        carDataBase.searchCarByRegNum();
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        carDataBase.searchCarByMarkAndModel();
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    try {
                        carDataBase.searchCarByYearRange();
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    try {
                        carDataBase.searchCarByMileage();
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 7: {
                    try {
                        carDataBase.showCarList(carDataBase);
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

    public static void callDeleteMenu (CarList carDataBase) {
        Scanner sc = new Scanner(System.in);
        byte number = 0;
        while (number != -1) {
            showDeleteMenu();
            number = sc.nextByte();
            switch (number) {
                case 1: {
                    try {
                    carDataBase.removeCar();
                    } catch (NoSuchElementException | EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        carDataBase.removeByYear();
                    } catch (EmptyDataBaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        carDataBase.removeAllCars();
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

    public static void callSortMenu (CarList carDataBase) {
        Scanner sc = new Scanner(System.in);
        byte number = 0;
        while (number != -1) {
            showSortMenu();
            number = sc.nextByte();
            switch (number) {
                case 1: {
                    carDataBase.sortByBrand();
                    break;
                }
                case 2: {
                    carDataBase.sortByModel();
                    break;
                }
                case 3: {
                    carDataBase.sortByYear();
                    break;
                }
                case 4: {
                    carDataBase.sortByMileage();
                    break;
                }
                case 5: {
                    carDataBase.sortByBrandAndModel();
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

    public static void showMainMenuText () {
        System.out.println("Выберите опцию и введите соответствующее число:");
        System.out.println("1 - Открыть меню поиска");
        System.out.println("2 - Внести автомобиль в базу данных");
        System.out.println("3 - Редактировать информацию об автомобиле по VIN-коду");
        System.out.println("4 - Открыть меню удаления информации");
        System.out.println("5 - Открыть меню сортировки");
        System.out.println("0 - Вернуться в предыдущее меню");
        System.out.println("-1 - Завершить программу");
    }

    public static void showSearchMenu () {
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

    public static void showSortMenu () {
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
}

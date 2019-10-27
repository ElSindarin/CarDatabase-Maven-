import cardatabase.Car;

public abstract class TestAbstractCarInitialization {
    protected Car initCar () {
        Car car = new Car("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        return car;
    }
}

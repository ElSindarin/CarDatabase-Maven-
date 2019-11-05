import cardatabase.CarList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultiThreadingAccess {
    CarList carDatabase = CarList.getInstance();

    @Before
    public void removeCars () {
        carDatabase.getCarList().clear();
    }

    @Test
    public void test_multithreading_access_to_getInstance () throws InterruptedException {
        Thread thread1 = new TestThreadImplementation("1AAAA11AAAA111111","AA1111AA","Lada","Kalina",1988,100000);
        Thread thread2 = new TestThreadImplementation("2AAAA11AAAA111111","AA2111AA","Lada","Kalina",1988,100000);
        Thread thread3 = new TestThreadImplementation("3AAAA11AAAA111111","AA3111AA","Lada","Kalina",1988,100000);
        Thread thread4 = new TestThreadImplementation("4AAAA11AAAA111111","AA4111AA","Lada","Kalina",1988,100000);
        Thread thread5 = new TestThreadImplementation("5AAAA11AAAA111111","AA5111AA","Lada","Kalina",1988,100000);
        Thread thread6 = new TestThreadImplementation("6AAAA11AAAA111111","AA6111AA","Lada","Kalina",1988,100000);
        Thread thread7 = new TestThreadImplementation("7AAAA11AAAA111111","AA7111AA","Lada","Kalina",1988,100000);
        Thread thread8 = new TestThreadImplementation("8AAAA11AAAA111111","AA8111AA","Lada","Kalina",1988,100000);
        Thread thread9 = new TestThreadImplementation("9AAAA11AAAA111111","AA9111AA","Lada","Kalina",1988,100000);
        Thread thread10 = new TestThreadImplementation("0AAAA11AAAA111111","AA0111AA","Lada","Kalina",1988,100000);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();

        Assert.assertEquals(carDatabase.getCarList().size(),10);
    }
}

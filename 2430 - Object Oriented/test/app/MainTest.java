package app;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTest {
	public static void main(String [] args) {
        List<Failure> failedList;
        Result result;

        System.out.println("-----------CHAMBER-TEST----------");
        result = JUnitCore.runClasses(ChamberTest.class);
        System.out.println("*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount());
        
        System.out.println("-----------DOOR-TEST------------");
        result = JUnitCore.runClasses(DoorTest.class);
        System.out.println("*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount());
        
        System.out.println("-----------PASSAGESECTION-TEST---------------");
        result = JUnitCore.runClasses(PassageSectionTest.class);
        System.out.println("*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount());
        
        System.out.println("-----------PASSAGE-TEST----------");
        result = JUnitCore.runClasses(PassageTest.class);
        System.out.println("*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount());
    }
}

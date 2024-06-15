package test.polymorphismTest;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.UUID;


public class RunTestBook {

        public static void main(String[] args) {

            Result result = JUnitCore.runClasses(TestBook.class);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println(result.wasSuccessful());

        }
}

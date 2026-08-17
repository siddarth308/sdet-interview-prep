// https://www.geeksforgeeks.org/java/exceptions-in-java/

package Java.ExceptionHandling;

public class TryCatchDemo {

    public static void main(String[] args) {
        try {
            System.out.println("Outer try block started");

            // Inner try block 1
            try {
                int n = 10;
                int res = n / 0;
            } catch (ArithmeticException e) {
                System.out.println("Caught ArithmeticException:" + e.getClass().getName());
            }

            // Inner try block 2
            try {
                String s = null;
                System.out.println(s.length());
            } catch (NullPointerException e) {
                System.out.println("Caught NullPointerException: " + e.getClass().getName());
            }
        } catch (Exception e) {
            System.out.println("Caught exception in outer try-catch: " + e);
        } finally {
            System.out.println("Finally block executed");
        }

        System.out.println("Program continues after nested try-catch");
    }
}

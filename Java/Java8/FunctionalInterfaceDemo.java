// https://www.youtube.com/watch?v=WXIoT8z4bNY
// https://www.geeksforgeeks.org/java/java-functional-interfaces/

package Java.Java8;

@FunctionalInterface

interface Square {
    int calculate(int x);
}

public class FunctionalInterfaceDemo {
    public static void main(String args[]) {
        int a = 5;

        // lambda expression to define the calculate method
        Square s = (int x) -> x * x;

        // parameter passed and return type must be same as defined in the prototype
        int ans = s.calculate(a);
        System.out.println(ans);
    }
}
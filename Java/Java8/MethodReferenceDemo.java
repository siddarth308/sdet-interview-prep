// https://www.geeksforgeeks.org/java/java-method-references/

package Java.Java8;

import java.util.Arrays;

public class MethodReferenceDemo {

    // Method
    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {

        String[] names = { "Geek1", "Geek2", "Geek3" };

        // Using method reference to print each name
        Arrays.stream(names).forEach(MethodReferenceDemo::print);

    }
}

// Reference to a Static Method-

class MathUtil {
    static void square(int n) {
        System.out.println(n * n);
    }
}

class GFG {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(2, 3, 4);
        list.forEach(MathUtil::square);
    }
}

// Reference to an Instance Method of a Particular Object

class Printer {
    void print(String msg) {
        System.out.println(msg);
    }
}

class GFG {
    public static void main(String[] args) {

        Printer printer = new Printer();
        List<String> data = Arrays.asList("Java", "Spring", "Boot");

        data.forEach(printer::print);
    }
}

// Reference to an Instance Method

class GFG {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("java", "spring", "microservice");

        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}

// Reference to a Constructor-

class Student {
    Student() {
        System.out.println("Student object created");
    }
}

class GFG {
    public static void main(String[] args) {

        Supplier<Student> supplier = Student::new;
        supplier.get();
    }
}
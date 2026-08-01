package Java.ExceptionHandling;

import java.io.*;

public class CheckedExceptionDemo {

    public static void main(String[] args) throws IOException {

        // Getting the current root directory
        String root = System.getProperty("user.dir");
        System.out.println("Current root directory: " + root);

        // Adding the file name to the root directory
        String path = root + "\\message.txt";
        System.out.println("File path: " + path);

        // Reading the file from the path in the local directory
        try {
            FileReader f = new FileReader(path);

            // Creating an object as one of the ways of taking input
            BufferedReader b = new BufferedReader(f);

            // Printing the first 3 lines of the file
            for (int counter = 0; counter < 3; counter++)
                System.out.println(b.readLine());

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }
}

package Java.Collections;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {

    public static void main(String[] args) {

        ArrayList<String> foods = new ArrayList<String>();

        foods.add("pizza");
        foods.add("burger");
        foods.add("cookie");

        Iterator<String> it = foods.iterator();

        System.out.println(it.next());
    }

}

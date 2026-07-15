package Java.Collections;

import java.util.ArrayList;
import java.util.Collections;

//  '*' imports all the classes inside package, but it takes extra space(increases size of code)    
import java.util.*;

/**
 * ArraylistDemo
 */
public class ArraylistDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // add elements
        list.add(1);
        list.add(2);

        System.out.println(list);

        // get elements
        int element = list.get(1);
        System.out.println(element);

        // add el in between
        list.add(2, 6);
        System.out.println(list);

        // set element
        list.set(0, 3);
        System.out.println(list);

        // delete element
        list.remove(2);
        System.out.println(list);

        // size
        int size = list.size();
        System.out.println(size);

        // loops
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();

        // sorting
        Collections.sort(list);
        System.out.println(list);

    }
}

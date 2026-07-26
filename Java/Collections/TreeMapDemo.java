// https://www.geeksforgeeks.org/java/treemap-in-java/

package Java.Collections;

import java.util.TreeMap;

public class TreeMapDemo {

    static void Constructor() {
        // Creating an empty HashMap
        TreeMap<Integer, String> m = new TreeMap<>();

        m.put(10, "Geeks");
        m.put(20, "For");
        m.put(30, "Geeks");

        // Creating the TreeMap using the Map
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>(
                m);

        // Printing the elements of TreeMap
        System.out.println("TreeMap: " + tm);
    }

}

// https://www.geeksforgeeks.org/java/treemap-in-java/

// HashMap is implemented using a hash table, while TreeMap is implemented using a 
// Red-Black Tree. HashMap does not maintain any ordering of keys and provides average O(1) 
// time complexity for put, get, and remove. TreeMap stores keys in sorted order and provides 
// O(log n) time complexity for these operations because it maintains a balanced tree. HashMap 
// allows one null key, whereas TreeMap does not allow null keys under natural ordering because 
// it needs to compare keys. I would use HashMap when I need fast lookups, and TreeMap when I need 
// the keys to remain sorted or when I need operations like finding the smallest, largest, or a 
// range of keys.

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

// https://www.geeksforgeeks.org/java/java-util-hashmap-in-java-with-examples/
// HashMap is implemented using a hash table, while TreeMap is implemented using a 
// Red-Black Tree. HashMap does not maintain any ordering of keys and provides average O(1) 
// time complexity for put, get, and remove. TreeMap stores keys in sorted order and provides 
// O(log n) time complexity for these operations because it maintains a balanced tree. HashMap 
// allows one null key, whereas TreeMap does not allow null keys under natural ordering because 
// it needs to compare keys. I would use HashMap when I need fast lookups, and TreeMap when I need 
// the keys to remain sorted or when I need operations like finding the smallest, largest, or a 
// range of keys.

package Java.Collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        // Create a HashMap
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Add elements to the HashMap
        hashMap.put("John", 25);
        hashMap.put("Jane", 30);
        hashMap.put("Jim", 35);

        // Iterate through the HashMap
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

}

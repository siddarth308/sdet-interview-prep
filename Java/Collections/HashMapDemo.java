// https://www.geeksforgeeks.org/java/java-util-hashmap-in-java-with-examples/

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

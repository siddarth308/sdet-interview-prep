// https://www.geeksforgeeks.org/java/hashset-in-java/

// HashSet is backed by a HashMap, while TreeSet is backed by a TreeMap, which uses a
//  Red-Black Tree. Both store only unique elements, but HashSet does not maintain any order 
//  and provides average O(1) time complexity for add, remove, and contains. TreeSet maintains 
//  \elements in sorted order and performs these operations in O(log n) time. HashSet allows one 
//  null element, whereas TreeSet generally does not allow null with natural ordering because it
//   must compare elements. I use HashSet when I need fast uniqueness checks, and TreeSet when I 
//   need unique elements in sorted order or need navigation methods like first(), last(), higher(),
//    and lower().

package Java.Collections;

import java.util.HashSet;

public class HashSetDemo {

    public static void main(String[] args) {

        HashSet<String> hs = new HashSet<>();

        // Adding elements to above Set using add() method
        hs.add("Geek");
        hs.add("For");
        hs.add("Geeks");
        hs.add("A");
        hs.add("B");
        hs.add("Z");

        System.out.println("HashSet : " + hs);

        // Removing the element B
        hs.remove("B");

        // Printing the updated HashSet elements
        System.out.println("HashSet after removing element : " + hs);

        // Returns false if the element is not present
        System.out.println("B exists in Set : " + hs.remove("B"));
    }
}

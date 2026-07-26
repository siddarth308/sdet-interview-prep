// https://www.geeksforgeeks.org/java/treeset-in-java-with-examples/

// HashSet is backed by a HashMap, while TreeSet is backed by a TreeMap, which uses a
//  Red-Black Tree. Both store only unique elements, but HashSet does not maintain any order 
//  and provides average O(1) time complexity for add, remove, and contains. TreeSet maintains 
//  \elements in sorted order and performs these operations in O(log n) time. HashSet allows one 
//  null element, whereas TreeSet generally does not allow null with natural ordering because it
//   must compare elements. I use HashSet when I need fast uniqueness checks, and TreeSet when I 
//   need unique elements in sorted order or need navigation methods like first(), last(), higher(),
//    and lower().

package Java.Collections;

import java.util.Set;

public class TreeSet {

    public static void main(String[] args) {
        // Creating a Set interface with reference to TreeSet class
        Set<String> ts = new TreeSet<>();

        // Elements are added using add() method
        ts.add("Geek");
        ts.add("For");
        ts.add("Geeks");

        // Print all elements inside object
        System.out.println(ts);
    }
}

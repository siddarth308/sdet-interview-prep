package Java.Collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapVsHashMap {

    public static void main(String[] args) {

        /*
         * ============================================================
         * 1. HashMap
         * ============================================================
         *
         * - Not thread-safe
         * - Allows ONE null key
         * - Allows multiple null values
         * - Average O(1) get/put
         */

        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("user1", "Rahul");
        hashMap.put("user2", "Priya");

        // Null key is allowed
        hashMap.put(null, "A");

        // Multiple null values are allowed
        hashMap.put("user3", null);
        hashMap.put("user4", null);

        System.out.println("HashMap: " + hashMap);

        /*
         * ============================================================
         * 2. Hashtable
         * ============================================================
         *
         * - Thread-safe
         * - Does NOT allow null key
         * - Does NOT allow null values
         * - Legacy class
         * - Generally not preferred for new code
         */

        Hashtable<String, String> hashtable = new Hashtable<>();

        hashtable.put("user1", "Rahul");
        hashtable.put("user2", "Priya");

        // Uncomment to see NullPointerException
        // hashtable.put(null, "Rahul");
        // hashtable.put("user1", null);

        /*
         * ============================================================
         * 3. Collections.synchronizedMap()
         * ============================================================
         *
         * - Makes an existing Map thread-safe through synchronization
         * - Access is synchronized
         * - Can have more contention under heavy concurrency
         */

        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        synchronizedMap.put("user1", "Rahul");
        synchronizedMap.put("user2", "Priya");

        System.out.println("SynchronizedMap: " + synchronizedMap);

        /*
         * ============================================================
         * 4. ConcurrentHashMap
         * ============================================================
         *
         * - Thread-safe
         * - Designed for concurrent access
         * - Better concurrency than synchronizedMap
         * - Does NOT allow null keys
         * - Does NOT allow null values
         */

        ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put("user1", "Rahul");
        concurrentMap.put("user2", "Priya");

        System.out.println("ConcurrentHashMap: " + concurrentMap);

        // Uncomment to see NullPointerException
        // concurrentMap.put(null, "Rahul");
        // concurrentMap.put("user1", null);

        /*
         * ============================================================
         * 5. Important ConcurrentHashMap Operations
         * ============================================================
         */

        concurrentMap.putIfAbsent("user3", "Amit");

        System.out.println("After putIfAbsent: " + concurrentMap);

        // Atomic operation
        concurrentMap.computeIfAbsent(
                "user4",
                key -> "Neha");

        System.out.println("After computeIfAbsent: " + concurrentMap);

        /*
         * ============================================================
         * 6. Why ConcurrentHashMap instead of HashMap?
         * ============================================================
         *
         * HashMap:
         * -> Not thread-safe
         *
         * ConcurrentHashMap:
         * -> Thread-safe
         * -> Designed for multiple threads
         * -> Better concurrency
         *
         * Example:
         *
         * Thread 1 ──┐
         * Thread 2 ──┤
         * Thread 3 ──┼──> ConcurrentHashMap
         * Thread 4 ──┘
         */

        /*
         * ============================================================
         * 7. Important Interview Concept
         * ============================================================
         *
         * Thread-safe collection does NOT automatically make
         * every sequence of operations atomic.
         *
         * BAD:
         *
         * if (!concurrentMap.containsKey("user5")) {
         * concurrentMap.put("user5", "Siddarth");
         * }
         *
         * BETTER:
         *
         * concurrentMap.putIfAbsent("user5", "Siddarth");
         *
         */

        /*
         * ============================================================
         * QUICK REVISION
         * ============================================================
         *
         * HashMap
         * -> Fast
         * -> Not thread-safe
         * -> 1 null key
         * -> Multiple null values
         *
         * Hashtable
         * -> Thread-safe
         * -> Legacy
         * -> No null key/value
         *
         * synchronizedMap
         * -> HashMap + synchronization
         * -> Thread-safe
         * -> More contention possible
         *
         * ConcurrentHashMap
         * -> Modern concurrent Map
         * -> Thread-safe
         * -> Better concurrency
         * -> No null key/value
         */
    }
}
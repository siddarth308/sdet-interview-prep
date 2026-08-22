package Java.Collections;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(4);
        pq.add(3);
        pq.add(2);
        pq.add(1);

        System.out.println(pq);
        Iterator<Integer> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.println(pq.poll() + " ");
        }

    }

}

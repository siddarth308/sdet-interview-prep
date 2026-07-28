// https://www.geeksforgeeks.org/dsa/stack-implementation-using-deque/

package Java.Collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackDemo {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.pop() + " popped from stack");
        System.out.println("Top element is: " + stack.peek());

        // 30 popped from deque
        // Top element is: 20

    }

}

package DSA.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElementII {

    static ArrayList<Integer> nextGreater(int[] arr) {

        int n = arr.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }

        Stack<Integer> st = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            int curr = arr[i % 2];

            while (!st.isEmpty() && st.peek() <= curr) {
                st.pop();
            }
            if (i < n && !st.isEmpty()) {
                res[i] = st.peek();
            }
            st.push(curr);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int val : res) {
            result.add(val);
        }

        return result;

    }
}
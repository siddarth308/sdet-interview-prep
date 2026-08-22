package DSA.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElementII {

    static int[] nextGreater(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }

        Stack<Integer> st = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            int curr = nums[i % n];

            while (!st.isEmpty() && st.peek() <= curr) {
                st.pop();
            }
            if (i < n && !st.isEmpty()) {
                res[i] = st.peek();
            }
            st.push(curr);
        }
        return res;

    }

}
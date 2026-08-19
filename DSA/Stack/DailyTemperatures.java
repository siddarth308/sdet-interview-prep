package DSA.Stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    static void dailyTemp(int[] T) {
        int n = T.length;

        int[] daysWait = new int[n];

        Arrays.fill(daysWait, -1);

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!s.isEmpty() && T[s.peek()] < T[i]) {
                daysWait[s.peek()] = i - s.peek();

                s.pop();
            }
            s.push(i);
        }
    }

}

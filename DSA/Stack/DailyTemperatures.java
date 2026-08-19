package DSA.Stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;

        int[] daysWait = new int[n];

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!s.isEmpty() &&
                    temperatures[s.peek()] < temperatures[i]) {

                daysWait[s.peek()] = i - s.peek();

                s.pop();
            }

            s.push(i);
        }

        return daysWait;
    }
}
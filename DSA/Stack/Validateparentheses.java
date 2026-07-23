package DSA.Stack;

import java.util.Stack;

public class Validateparentheses {

    public static boolean isBalanced(String s) {

        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else if (c == ')' || c == '}' || c == ']') {

                // no opening bracket
                if (st.isEmpty())
                    return false;

                char top = st.peek();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '['))

                    return false;

                st.pop();
            }

        }
        return st.isEmpty();

    }

}

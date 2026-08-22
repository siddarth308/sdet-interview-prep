/*
-------------------------------------------------------
Problem: Valid Parentheses
Difficulty: Easy
LeetCode: #20
Pattern: Stack

Approach:
- Push opening brackets onto the stack.
- For every closing bracket, check the top element.
- If it matches, pop it.
- Stack should be empty at the end.

Time Complexity: O(n)
Space Complexity: O(n)

Key Learning:
- Stack follows LIFO.
- Useful for matching nested structures.
- Check stack is not empty before popping.

Interview Follow-up:
- Can this be solved without Stack?
- How would you validate HTML/XML tags?
-------------------------------------------------------
*/

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
                // peak means show top element w/o removing it

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

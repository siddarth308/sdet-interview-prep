// https://www.geeksforgeeks.org/dsa/evaluate-the-value-of-an-arithmetic-expression-in-reverse-polish-notation-in-java/

package DSA.Stack;

public class EvaluateReversePolishNotation {

    public static int eval(String[] tokens) {

        int i = 0;
        int lastNum = -1;

        while (i < tokens.length) {
            if (tokens[i].equals("+") ||
                    tokens[i].equals("-") ||
                    tokens[i].equals("*") ||
                    tokens[i].equals("/")) {
                int val1 = Integer.valueOf(tokens[lastNum - 1]);
                int val2 = Integer.valueOf(tokens[lastNum]);

                int ans = 0;
                if (tokens[i].equals("*")) {
                    ans = val1 * val2;
                } else if (tokens[i].equals("/")) {
                    ans = val1 / val2;
                } else if (tokens[i].equals("+")) {
                    ans = val1 + val2;
                } else if (tokens[i].equals("-")) {
                    ans = val1 - val2;
                }

                tokens[lastNum - 1] = Integer.toString(ans);
                lastNum--;
            } else {
                lastNum++;
                tokens[lastNum] = tokens[i];
            }
            i++;

        }
        return Integer.valueOf(tokens[lastNum]);
    }
}

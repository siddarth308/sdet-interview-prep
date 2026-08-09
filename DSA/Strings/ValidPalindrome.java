package DSA.Strings;

// Naive Approach
public class ValidPalindrome {

    // public boolean isPalindrome(String s) {

    // StringBuilder s1 = new StringBuilder();
    // for(char ch: s.toCharArray()) {
    // if(Character.isLetterOrDigit(ch)){
    // s1.append(Character.toLowerCase(ch));
    // }
    // }

    // StringBuilder rev = new StringBuilder(s1.toString());
    // rev.reverse();
    // return s1.toString().equals(rev.toString());
    // }
    // }

    // Optimal Approach- The idea is to use the two-pointer approach to check if a
    // sentence is a palindrome. We place one pointer at the start and the other at
    // the end of the string, moving them toward each other while comparing
    // characters.
    // We skip any non-alphanumeric characters and convert uppercase letters to
    // lowercase to ensure a case-insensitive comparison. If the characters at both
    // pointers don't match, we return false. If the pointers cross without a
    // mismatch, the sentence is a palindrome.

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}

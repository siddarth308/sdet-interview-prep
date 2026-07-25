package DSA.Strings;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);

    }

    // HashMap -

    public boolean areAnagram(String s1, String s2) {

        if (s1.length() != s2.length())
            return false;

        HashMap<Character, Integer> charCount = new HashMap<>();

        for (char ch : s1.toCharArray())
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

        for (char ch : s2.toCharArray())
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

        for (var pair : charCount.entrySet()) {
            if (pair.getValue() != 0) {
                return false;
            }
        }
        return true;

    }

}

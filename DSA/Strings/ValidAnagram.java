/*
-------------------------------------------------------
Problem: Valid Anagram
Difficulty: Easy
LeetCode: #242
Pattern: HashMap / Character Frequency

Approach:
- Count the frequency of each character.
- Compare frequencies for both strings.
- If all frequencies match, the strings are anagrams.

Time Complexity: O(n)
Space Complexity: O(n)

Key Learning:
- Frequency counting using HashMap or array.
- Strings are anagrams only if character counts match.

Interview Follow-up:
- How would you solve it if the strings contain Unicode characters?
- How would you solve it without using HashMap?
-------------------------------------------------------
*/

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

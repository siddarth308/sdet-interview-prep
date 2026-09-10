package DSA.SlidingWindow;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {

        int[] count = new int[26];

        int left = 0;
        int maxFreq = 0;
        int res = 0;

        for (int right = 0; right < s.length(); right++) {

            count[s.charAt(right) - 'A']++;

            maxFreq = Math.max(maxFreq,
                    count[s.charAt(right) - 'A']);

            int windowLength = right - left + 1;

            int replacements = windowLength - maxFreq;

            while (replacements > k) {

                count[s.charAt(left) - 'A']--;

                left++;

                windowLength = right - left + 1;

                replacements = windowLength - maxFreq;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
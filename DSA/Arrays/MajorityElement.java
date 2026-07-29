
// -------------------------------------------------------
// Problem: Majority Element
// Difficulty: Easy
// LeetCode: #169
// Pattern: HashMap / Boyer-Moore Voting Algorithm
// Companies: Amazon, Microsoft, Google, Meta, JP Morgan

// Approach 1:
// - Count frequency using HashMap.
// - Return the element whose frequency is greater than n/2.

// Approach 2 (Optimal):
// - Boyer-Moore Voting Algorithm.
// - Maintain a candidate and count.
// - Increment count for the same element.
// - Decrement for different elements.
// - Final candidate is the majority element.

// Time Complexity:
// HashMap -> O(n)
// Boyer-Moore -> O(n)

// Space Complexity:
// HashMap -> O(n)
// Boyer-Moore -> O(1)

// Key Learning:
// - Frequency counting.
// - Candidate elimination.
// - Boyer-Moore Voting Algorithm.

// Interview Follow-up:
// - What if no majority element exists?
// - Can you verify the candidate in another pass?
// -------------------------------------------------------
// */

import java.util.HashMap;

public class MajorityElement {

    static int majorityNumber(int arr[], int n) {
        int ans = -1;

        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            if (freq.containsKey(arr[i])) {
                freq.put(arr[i], freq.get(arr[i] + 1));
            } else {
                freq.put(arr[i], 1);
            }

            if (freq.get(arr[i]) > n / 2)
                ans = arr[i];
        }
        return ans;
    }

}

// Moore's Voting Algorithm,

static int MajorityElement(int arr[]) {

    int n = arr.length;

    int candidate = -1, count = 0;

    for (int num : arr) {

        if (count == 0) {

            candidate = num;
            count = 1;
        } else if (num == candidate) {
            count++;
        } else {
            count--;
        }
    }

    count = 0;

    for (int num : arr) {
        if (num == candidate) {
            count++;
        }
    }

    if (count > n / 2) {
        return candidate;
    } else {
        return -1;
    }
}

// more optimal, no need of second loop -

class Solution {
    public int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}

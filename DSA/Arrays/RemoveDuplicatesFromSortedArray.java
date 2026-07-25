/*
-------------------------------------------------------
Problem: Remove Duplicates from Sorted Array
Difficulty: Easy
LeetCode: #26
Pattern: Two Pointers

Approach:
- Maintain a slow pointer for unique elements.
- Traverse using a fast pointer.
- Copy only new unique values.

Time Complexity: O(n)
Space Complexity: O(1)

Key Learning:
- In-place array modification.
- Efficient use of two pointers.

Interview Follow-up:
- What if duplicates can appear at most twice?
-------------------------------------------------------
*/

public class RemoveDuplicatesFromSortedArray {

    static int removeDuplicates(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return n;

        int idx = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[idx++] = arr[i];
            }
        }

        return idx;
    }

}

/*
Problem: Merge Sorted Array
Difficulty: Easy
Pattern: Two Pointers
Time Complexity: O(m + n)
Space Complexity: O(1)

Key Learning:
- Merge from the end to avoid overwriting elements.
*/

public class MergeSortedArray {

    // using 2 pointers

    static void mergedArray(int[] arr1, int[] arr2) {

        int n = arr1.length, m = arr2.length;

        int i = 0, j = 0;

        int[] merged = new int[n + m];

        int k = 0;

        while (i < n && j < m) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < n) {
            merged[k++] = arr1[i++];
        }

        while (j < m) {
            merged[k++] = arr2[j++];
        }

        for (i = 0; i < n; i++) {
            arr1[i] = merged[i];
        }

        for (j = 0; j < m; j++) {
            arr2[j] = merged[n + j];
        }
    }

}

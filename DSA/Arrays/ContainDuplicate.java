/*
-------------------------------------------------------
Problem: Contains Duplicate
Difficulty: Easy
LeetCode: #217
Pattern: HashSet

Approach:
- Traverse the array.
- Store elements in a HashSet.
- If an element already exists, return true.

Time Complexity: O(n)
Space Complexity: O(n)

Key Learning:
- HashSet stores only unique values.
- Duplicate detection using constant-time lookup.

Interview Follow-up:
- What if duplicates must be within distance k?
-------------------------------------------------------
*/

public class ContainDuplicate {

    public boolean containsDuplicate(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (nums[i] == nums[j]) {
                    return true;
                }
            }

        }
        return false;
    }

    // Better approach -

}

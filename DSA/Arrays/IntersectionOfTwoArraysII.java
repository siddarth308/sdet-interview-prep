// Need to do again

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    // nums1 and nums2 are the two input arrays.
    public int[] intersect(int[] nums1, int[] nums2) {

        ArrayList<Integer> res = new ArrayList<>();
        boolean[] used = new boolean[nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {

                if (!used[j] && nums1[i] == nums2[j]) {
                    res.add(nums1[i]);
                    used[j] = true;
                    break;
                }
            }
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }
}
// 2 arrays - 
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i ++){

        for (int j= i+1; j < n; j ++){

       if(nums[i] + nums[j] == target) {
        return new int[]{i,j};
       }

    }

}
return new int[]{};
    }
}

//Using Sorting & 2 pointer technique

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;

        while(left < right) {
            int sum = nums[left] + nums[right];
        
        if(sum == target)
        return new int[]{left, right};

        else if (sum > target)
        right--;

        else left++;
    }
    return new int[]{};
}

}

//But here the issue will be for - nums =
// [3,2,4]
// target =
// 6
// Output
// [0,2]
// Expected
// [1,2]

//as after sorting indexing is changed to best is hashmap only
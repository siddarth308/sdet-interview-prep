// https://www.geeksforgeeks.org/dsa/move-zeroes-end-array/

public class MoveZeroes {

    public void MoveZeroes(int[] nums) {

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i];
            }
        }

        while (count < nums.length) {
            nums[count++] = 0;
        }
    }

}

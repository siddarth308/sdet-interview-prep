public class MaximumSubarray {

    static int MaxSubarray(int[] arr) {

        int res = arr[0];

        for (int i = 0; i < arr.length; i++) {
            int currsum = 0;

            for (int j = i; j < arr.length; j++) {
                currsum = arr[j] + currsum;
                res = Math.max(currsum, res);
            }

        }

        return res;
    }

    // Using Kadane's Algorithm
    static int MaxSubarray(int[] arr) {

        int res = arr[0];
        int maxEnding = arr[0];

        for(int i=1; i< arr.length; i++){
            maxEnding = Math.max(maxEnding + arr[i], arr[i]);

            res = Math.max(res, maxEnding);
        }
        return res;
}

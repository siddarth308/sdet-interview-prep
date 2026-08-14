package DSA.BinarySearch;

public class FindFirstAndLastPosition {

    static int findFirst(int[] arr, int x) {
        int low = 0, high = arr.length - 1;

        int first = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return first;
    }

    static int findLastt(int[] arr, int x) {
        int low = 0, high = arr.length - 1;

        int last = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                last = mid;
                low = mid + 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return last;
    }
}
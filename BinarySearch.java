public class BinarySearch {
    public static void main(String[] args) {
        // Some test cases
        int[] arr = { 1, 2, 4, 5, 6 };
        int target = 6;

        // The function is called like this
        int result = binarySearchRecursive(arr, target, 0, arr.length - 1);

        int result2 = binarySearchIterative(arr, target);

        if (result == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index by recursive function " + result);
        }

        if (result2 == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index by iterative function " + result2);
        }
    }

    /*
     * Recursive binary search
     * 
     * @return The index of target if found, else -1.
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        // If left > right, it means the target is not in the array
        if (left > right) {
            return -1; // Target not found
        }

        // Calculate the middle index to divide the array into two halves
        int mid = left + (right - left) / 2; // to avoid overflow

        // If the middle element is the target, then we can just
        // return the index of the middle element
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            // If the middle element is greater than the target, search in the left half
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            // If the middle element is less than the target, search in the right half
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }

    /*
     * Iterative binary search
     * 
     * @return The index of target if found, else -1.
     */
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // to avoid overflow

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1; // Target not found
    }
}
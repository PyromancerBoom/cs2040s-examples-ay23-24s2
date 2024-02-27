public class IterativeMergeSort {
    public static void mergeSort(int[] array) {
        int lengthOfArr = array.length;

        // Outer loop runs for different sizes of subarrays to be merged
        for (int size = 1; size < lengthOfArr; size *= 2) {
            // Inner loop merges subarrays of specified size
            for (int left = 0; left < lengthOfArr - 1; left += 2 * size) {
                // Calculate right and mid indices based on subarray size
                int mid = Math.min(left + size - 1, lengthOfArr - 1);
                int right = Math.min(mid + size, lengthOfArr - 1);

                // Merge two subarrays
                merge(array, left, mid, right);
            }
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays for left and right subarrays
        // aka auxiliary arrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy elements to temporary arrays
        // left half
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[left + i];
        }

        // right half
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        // Merge elements back into the original array
        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftArr.length && j < rightArr.length) {
            // compare head of left and right subarrays
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from left and right arrays
        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 4, 3, 6, 5 };
        mergeSort(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

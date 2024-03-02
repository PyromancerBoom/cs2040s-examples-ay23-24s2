public class IterativeMergeSort {
    // similar idea to sort a queue using another queue with merge sort
    public static void mergeSort(int[] array) {
        if (array == null) {
            return;
        }
        int lengthOfArr = array.length;

        // This loop is responsible for controlling the size of the subarrays that we
        // are going to merge
        // We start with size 1 (individual elements), then we merge pairs of elements,
        // then quadruplets, and so on
        for (int size = 1; size < lengthOfArr; size *= 2) {

            // This loop goes through the array, picking two subarrays of the current size
            // and merging them
            // The 'left' variable is the starting index of the first subarray
            for (int left = 0; left < lengthOfArr - 1; left += 2 * size) {

                // 'mid' is the ending index of the first subarray, and 'right' is the ending
                // index of the second subarray.
                int mid = left + size - 1;
                if (mid >= lengthOfArr) {
                    mid = lengthOfArr - 1;
                }
                int right = mid + size;
                if (right >= lengthOfArr) {
                    right = lengthOfArr - 1;
                }
                // Merge the two subarrays into the original array.
                merge(array, left, mid, right);
            }
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // create temporary arrays for left and right subarrays
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
            // compare "head" of left and right subarrays
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
        int[] arr = { 10, 5, -100, -1, 0, 1, 2, 3, 4, 8, 7, 6, 9 };
        mergeSort(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

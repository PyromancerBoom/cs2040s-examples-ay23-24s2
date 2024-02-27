public class QuickSort {

    // Swap two elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Hoare's partition function
    private static int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;
            }
        }
    }

    // Quick sort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = hoarePartition(arr, low, high);
            quickSort(arr, low, p);
            quickSort(arr, p + 1, high);
        }
    }

    // Main function
    public static void main(String[] args) {
        int[] arr = { 6, 8, 2, 16, 22, 3, 9, 33, 6, 17 };
        quickSort(arr, 0, arr.length - 1);

        // Print the sorted array
        System.out.println("Sorted array:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}

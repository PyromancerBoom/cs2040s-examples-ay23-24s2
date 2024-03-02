import java.util.Arrays;

/*
 * This is the code to do insertion sort recursively
 */
public class RecursiveInsertionSort {

    // Recursive function to sort an array using insertion sort
    public static void insertionSortRecursive(int[] array, int n) {
        /*
         * Uncomment the print lines to see the sorting in action
         * or feel free to add your own print statements
         */

        // System.out.println("Sorting subarray: " +
        // Arrays.toString(Arrays.copyOfRange(array, 0, n)));

        // base case - the array has only one element, it's already sorted
        if (n <= 1) {
            return;
        }

        // Recursively sort the subarray
        insertionSortRecursive(array, n - 1);

        // Insert the last element into its correct position in the sorted subarray
        int lastElement = array[n - 1];
        int j = n - 2;

        // System.out.println("Inserting " + lastElement + " into sorted subarray: "
        // + Arrays.toString(Arrays.copyOfRange(array, 0, n - 1)));

        while (j >= 0 && array[j] > lastElement) {
            // System.out.println("Shifting " + array[j] + " to the right");
            array[j + 1] = array[j]; // Shift element to the right
            j--; // Move to the previous element
        }

        // Place the last element in its correct position
        array[j + 1] = lastElement;
        // System.out.println("Array after insertion: " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        // generate a random array
        int size = 10; // assuming size >= 0

        int[] randomArr = new int[size];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100);
        }
        System.out.println("Initial Array: " + Arrays.toString(randomArr));

        System.out.println();

        insertionSortRecursive(randomArr, randomArr.length);
        System.out.println("Sorted Random Array: " + Arrays.toString(randomArr));

    }
}

import java.util.LinkedList;

public class QueueSort {

    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = 10000;
        while (n-- > 0) {
            queue.offer(n);
        }

        // System.out.println("Original queue: " + queue);

        naiveSort(queue);

        // optimisedSortMerge(queue);

        System.out.println("Sorted queue: ");
    }

    /*
     * Naive sorting
     */
    public static void naiveSort(LinkedList<Integer> queue) {
        LinkedList<Integer> sortedQueue = new LinkedList<>();
        int n = queue.size();

        for (int i = 0; i < n; i++) {
            int minElement = Integer.MAX_VALUE; // why do this?
            int minIndex = 0;

            // finding the minimum element in the queue
            for (int j = 0; j < n - i; j++) {
                // pop off
                int element = queue.poll();

                // compare
                if (element < minElement) {
                    minElement = element;
                    minIndex = j;
                }

                // push back
                queue.offer(element);
            }

            // remove the minimum element and add it to the sorted queue
            for (int j = 0; j < minIndex; j++) {
                queue.offer(queue.poll());
            }
            sortedQueue.offer(queue.poll());

            // add the remaining elements back to the original queue
            for (int j = 0; j < n - i - 1; j++) {
                queue.offer(queue.poll());
            }
        }

        // putting the sorted elements back into the original queue
        while (!sortedQueue.isEmpty()) {
            queue.offer(sortedQueue.poll());
        }
    }

    /*
     * Optimised sorting
     */
    public static void optimisedSortMerge(LinkedList<Integer> queue) {
        int size = queue.size();

        // Iterate through sub-queue sizes from 1 to n
        for (int subSize = 1; subSize < size; subSize *= 2) {
            // Iterate through each sub-queue pair
            for (int i = 0; i < size - subSize; i += 2 * subSize) {
                merge(queue, i, i + subSize, Math.min(i + 2 * subSize, size));
            }
        }
    }

    // merge routine for the optimised sorting
    private static void merge(LinkedList<Integer> queue, int leftStart, int mid, int rightEnd) {
        LinkedList<Integer> tempQueue = new LinkedList<>();
        int leftIndex = leftStart;
        int rightIndex = mid;

        // merge elements from both sub-queues into the temporary queue
        while (leftIndex < mid && rightIndex < rightEnd) {
            // compare elements from the two sub-queues
            if (queue.get(leftIndex) <= queue.get(rightIndex)) {
                tempQueue.offer(queue.get(leftIndex));
                leftIndex++;
            } else {
                tempQueue.offer(queue.get(rightIndex));
                rightIndex++;
            }
        }

        // add remaining elements from the non-empty sub-queue
        while (leftIndex < mid) {
            tempQueue.offer(queue.get(leftIndex));
            leftIndex++;
        }
        while (rightIndex < rightEnd) {
            tempQueue.offer(queue.get(rightIndex));
            rightIndex++;
        }

        // Copy sorted elements back to the original queue
        for (int i = leftStart; i < rightEnd; i++) {
            queue.set(i, tempQueue.poll());
        }
    }
}

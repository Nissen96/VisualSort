import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends VisualSort {
    QuickSort() {
        super("Quick Sort");
    }

    /**
     * Helper function for the first call to Quick Sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Call Quick Sort on entire array
        quickSort(A, 0, A.length - 1);
    }

    /**
     * Sort array with the Quick Sort algorithm
     * A partition element is chosen and the array is partitioned
     * The subparts are recursively sorted
     * @param A - The array to sort
     * @param p - The start index of the current subarray
     * @param r - The end index of the current subarray
     */
    private void quickSort(int[] A, int p, int r) {
        // Stop when array is sorted
        if (p < r) {
            // Partition elements
            int q = partition(A, p, r);

            // Sort left and right partition
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }

    /**
     * Partitioning is done by first choosing a random partition element
     * The array is then split into two halves
     * The left half contains each element smaller than the partition and the right each larger
     * The partition element is inserted in the middle
     * @param A - The array to sort
     * @param p - The start index of the current subarray to partition
     * @param r - The end index of the current subarray to partition
     * @return the index of the partition element
     */
    private int partition(int[] A, int p, int r) {
        // Get a random element in range (p..r) to partition by
        int randIdx = ThreadLocalRandom.current().nextInt(p, r + 1);
        int x = A[randIdx];

        // Swap the random element with the last
        Util.swap(A, randIdx, r);
        visualize(A);

        // Iterate over array and partition elements <= x to the left and > x to the right
        int i = p - 1;
        for (int j = p; j < r; j++) {
            // If the element is <= x, place it at the end of the left partition
            // The element already there (which is > x) is placed at the position of the element
            // - which is at the end of the right partition
            if (A[j] <= x) {
                i++;
                Util.swap(A, i, j);
                visualize(A);
            }
        }

        // Insert x in the middle of the partitions
        Util.swap(A, i + 1, r);
        visualize(A);
        return i + 1;
    }
}

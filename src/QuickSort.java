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
        quickSort(A, 0, A.length - 1);
    }

    /**
     * Sort array with the Quick Sort algorithm
     * A partition element is chosen and the array is partitioned
     * The subparts are recursively sorted
     * @param A - The array to sort
     * @param p - The index of the first element of the left part of the array
     * @param r - The index of the last element of the right part of the array
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
     * @param p - The index of the first element of the left part of the array to partition
     * @param r - The index of the last element of the right part of the array to partition
     * @return the index of the partition element
     */
    private int partition(int[] A, int p, int r) {
        // Get a random element in range (p..r) to partition by
        int randIdx = ThreadLocalRandom.current().nextInt(p, r + 1);
        int x = A[randIdx];

        // Swap the random element with the last
        swap(A, randIdx, r);

        // Iterate over array and partition elements <= x to the left and > x to the right
        int i = p - 1;
        for (int j = p; j < r; j++) {
            // If the element is <= x, place it at the end of the left partition
            // The element already there (which is > x) is placed at the position of the element
            // - which is at the end of the right partition
            if (A[j] <= x) {
                i++;
                swap(A, i, j);
            }
        }

        // Insert x in the middle of the partitions
        swap(A, i + 1, r);
        return i + 1;
    }
}

public class TimSort extends MergeSort {
    TimSort() {
        super("Tim Sort");
    }

    private final int RUN = 32;

    /**
     * Sort array with the Tim Sort algorithm
     * Tim Sort is a combination of Insertion Sort and Merge Sort
     * Blocks or "runs" of numbers of a given size are sorted with insertion sort
     * The runs are then merged together
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;

        // Sort individual subarrays of size RUN
        for (int i = 0; i < n; i += RUN) {
            insertionSort(A, i, Math.min((i + RUN - 1), (n - 1)));
        }

        // Start merging from size RUN and double on each pass
        for (int size = RUN; size < n; size *= 2) {
            // Pick starting point of left subarray
            // Merge A[left...left + size - 1] and A[left + size...left + 2 * size - 1]
            // Increase left by 2 * size after each pass
            for (int left = 0; left < n; left += 2 * size) {
                // Find ending point of left subarray
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                // Merge subarray A[left...mid] and A[mid + 1...right]
                merge(A, left, mid, right);
            }
        }
    }

    /**
     * Sort array from left index to right index with the Insertion Sort algorithm
     * @param A - Array to sort
     * @param left - Start index
     * @param right - End index
     */
    private void insertionSort(int[] A, int left, int right) {
        // Iterate over each element
        for (int i = left + 1; i <= right; i++) {
            // Get current value and index
            int temp = A[i];
            int j = i - 1;

            // Move any previous elements smaller than the current up
            while (j >= left && A[j] > temp) {
                A[j + 1] = A[j];
                visualize(A);

                j--;
            }

            // Insert element into its location in the sorted array
            A[j + 1] = temp;
            visualize(A);
        }
    }
}

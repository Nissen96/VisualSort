public class MergeSortBU extends MergeSort {
    MergeSortBU() {
        super("Merge Sort - Bottom Up");
    }

    /**
     * Sort array with the Merge Sort algorithm
     * Merge Sort split an array in halves and merge the halves
     * into sorted parts until the entire array is sorted
     * This implementation is "bottom up" and sorts iteratively
     * It merges all elements into larger and larger sorted subparts
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;

        // The length of the parts of the list - multiplied by two on each pass
        for (int len = 1; len < n; len *= 2) {
            // Go through piles of length "len" and merge them
            for (int low = 0; low < n - len; low += 2 * len) {
                int mid  = low + len - 1;

                // Make sure not to overshoot the final element
                int hi = Math.min(low + 2 * len - 1, n - 1);
                merge(A, low, mid, hi);
            }
        }
    }
}

public class MergeSortTD extends MergeSort {
    MergeSortTD() {
        super("Merge Sort - Top Down");
    }

    /**
     * Helper function for the first call to Merge Sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Call Merge Sort on entire array
        mergeSort(A, 0, A.length - 1);
    }

    /**
     * Sort array with the Merge Sort algorithm
     * Merge Sort split an array in halves and merge the halves
     * into sorted parts until the entire array is sorted
     * This implementation is "top down" and sorts recursively
     * It merges as much as possible on each run before continuing
     * @param A - The array to sort
     * @param low - The start index of the current subarray
     * @param high - The end index of the current subarray
     */
    private void mergeSort(int[] A, int low, int high) {
        // Arrays of size 1 are trivially sorted
        if (low == high) {
            return;
        }

        // Calculate midpoint
        int middle = (low + high) / 2;

        // Recursively sort left and right side
        mergeSort(A, low, middle);
        mergeSort(A, middle + 1, high);

        // Merge arrays in place
        merge(A, low, middle, high);
    }
}

public class MergeSortTD extends MergeSort {
    MergeSortTD() {
        super("Merge Sort - Top Down");
    }

    /**
     * Helper function for the first call to merge sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        mergeSort(A,0, A.length - 1);
    }

    /**
     * Sort array with the Merge Sort algorithm
     * Merge Sort split an array in halves and merge the halves
     * into sorted parts until the entire array is sorted
     * This implementation is "top down" and sorts recursively
     * It merges as much as possible on each run before continuing
     * @param A - The array to sort
     * @param start - The index of the first element of the left part of the array
     * @param end - The index of the last element of the right part of the array
     */
    private void mergeSort(int[] A, int start, int end) {
        // Arrays of size 1 are trivially sorted
        if (start == end) {
            return;
        }

        // Calculate midpoint
        int middle = (start + end) / 2;

        // Recursively sort left and right side
        mergeSort(A, start, middle);
        mergeSort(A, middle + 1, end);

        // Merge arrays in place
        merge(A, start, middle, end);
    }
}

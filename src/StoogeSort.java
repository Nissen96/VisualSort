public class StoogeSort extends VisualSort {
    StoogeSort() {
        super("Stooge Sort");
    }

    /**
     * Helper function for the first call to Stooge Sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        stoogeSort(A, 0, A.length - 1);
    }

    /**
     * Sort array with the Stooge Sort algorithm
     * Stooge Sort swaps the first element with the last, if the first is largest
     * If there are 3 or more elements in the list, then
     *    Recursively Stooge Sort the initial 2/3 of the array
     *    Recursively Stooge Sort the final 2/3 of the array
     *    Recursively Stooge Sort the initial 2/3 of the array again
     *
     * @param A - Array to sort
     * @param start - The index of the first element of the left part of the array
     * @param end - The index of the last element of the right part of the array
     */
    private void stoogeSort(int[] A, int start, int end) {
        // If the first element is larger than the last, swap them
        if (A[start] > A[end]) {
            Util.swap(A, start, end);
            visualize(A);
        }

        // If there are 3 or more elements
        if (end - start + 1 > 2) {
            // Compute "mid" - really 1/3 up the list
            int mid = (end - start + 1) / 3;

            // Recursively sort initial 2/3, then final 2/3 and then initial 2/3 again
            stoogeSort(A, start, end - mid);
            stoogeSort(A, start + mid, end);
            stoogeSort(A, start, end - mid);
        }
    }
}

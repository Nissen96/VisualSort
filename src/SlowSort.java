public class SlowSort extends VisualSort {
    SlowSort() {
        super("Slow Sort");
    }

    /**
     * Helper function for the first call to Slow Sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        slowSort(A, 0, A.length - 1);
    }

    /**
     * Sort array with the Slow Sort algorithm
     * It is based on the principle of "multiply and surrender" - the opposite of divide and conquer
     * It finds the maximum of the sorted array,
     *   places the maximum at the end
     *   and sorts the remaining array recursively
     * @param A - Array to sort
     * @param p - The index of the first element of the left part of the array
     * @param r - The index of the last element of the right part of the array
     */
    private void slowSort(int[] A, int p, int r) {
        if (p < r) {
            // Find midpoint
            int q = (p + r) / 2;

            // Sort both halves recursively
            slowSort(A, p, q);
            slowSort(A, q + 1, r);

            // Find the maximum of the entire array
            // by finding the maximum in the now two sorted halves
            if (A[r] < A[q]) {
                // Place at the end of the list
                Util.swap(A, r, q);
                visualize(A);
            }

            // Sort everything but the maximum element recursively
            slowSort(A, p, r - 1);
        }
    }
}

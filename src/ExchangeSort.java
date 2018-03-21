public class ExchangeSort extends VisualSort {
    ExchangeSort() {
        super("Exchange Sort");
    }

    /**
     * Sort array with the Exchange Sort algorithm
     * Exchange Sort compares elements of the array and swaps those out of order
     * It compares the first element with each following element and swaps if out of order
     * This is done for each element, sorting the array from the bottom up
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;

        // Iterate over each index
        for (int i = 0; i < n - 1; i++) {
            // Compare element at index with each following element
            for (int j = i + 1; j < n; j++) {
                // Swap if any smaller element found
                if (A[i] > A[j]) {
                    Util.swap(A, i, j);
                    visualize(A);
                }
            }
        }
    }
}

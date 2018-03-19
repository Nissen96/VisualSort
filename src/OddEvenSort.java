public class OddEvenSort extends VisualSort {
    OddEvenSort() {
        super("Odd-Even Sort");
    }

    /**
     * Sort array with the Odd-Even Sort algorithm
     * Odd-Even Sort is a variation of Bubble Sort
     * It's divided into two phases, an odd and an even phase
     * In the odd phase, Bubble Sort is performed on odd-indexed elements
     * In the even phase, Bubble Sort is performed on even-indexed elements
     * This pushes all the larger numbers one up and all the smaller one down on each pass
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Stop when array is sorted
        boolean sorted = false;
        while (!sorted) {
            sorted = true;

            // Perform Bubble Sort on all odd/even pairs
            for (int i = 1; i < A.length - 1; i += 2) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    sorted = false;
                }
            }

            // Perform Bubble Sort on all even/odd pairs
            for (int i = 0; i < A.length - 1; i += 2) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    sorted = false;
                }
            }
        }
    }
}

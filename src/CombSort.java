public class CombSort extends VisualSort {
    CombSort() {
        super("Comb Sort");
    }

    /**
     * Sort array with the Comb Sort algorithm
     * Comb Sort is a variation of Bubble Sort with a shrinking gap size (instead of constant 1)
     * The gap shrinks by a factor of 1.3 in every iteration until 1
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;

        // Initialize gap
        int gap = n;

        // Make sure loop runs first time
        boolean swapped = true;

        // Run while gap is more than 1 or last iteration caused a swap
        while (gap != 1 || swapped) {
            // Find next gap size
            gap = getNextGap(gap);

            // Initialize swapped to false to check for swaps
            swapped = false;

            // Compare all elements with current gap
            for (int i = 0, j = n - gap; i < j; i++) {
                if (A[i] > A[i + gap]) {
                    // Swap A[i] and A[i + gap]
                    Util.swap(A, i, i + gap);
                    visualize(A);

                    // Set swapped
                    swapped = true;
                }
            }
        }
    }

    /**
     * Find next gap between elements
     * @param gap - Current gap
     * @return New gap size
     */
    private int getNextGap(int gap) {
        // Shrink gap by Shrink factor 1.3 (empirically found)
        gap /= 1.3;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }
}

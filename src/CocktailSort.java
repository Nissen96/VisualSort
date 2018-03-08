public class CocktailSort extends VisualSort {
    CocktailSort() {
        super("Cocktail Sort");
    }

    /**
     * Sort array with the Cocktail Sort algorithm
     * Cocktail Sort is a variant of Bubble Sort - it sorts in both directions on each pass
     * This places the greatest value to the top and the smallest to the bottom on each pass
     * This resembles the motion of a cocktail shaker
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Keep track of the current start and end index for performance improvements
        int startIdx = 0;
        int endIdx = A.length - 1;
        int newStartIdx, newEndIdx;

        // Do until sorted
        while (startIdx <= endIdx) {
            // Set temp indices
            newStartIdx = endIdx;
            newEndIdx = startIdx;

            // Bubble largest element upwards like bubble sort
            for (int i = startIdx; i < endIdx; i++) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    newEndIdx = i;
                    visualize(A);
                }
            }

            // New end index is at the last made swap
            endIdx = newEndIdx;

            // Do the same downwards for the smallest element
            for (int i = endIdx - 1; i >= startIdx; i--) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    newStartIdx = i;
                    visualize(A);
                }
            }

            // New start index is at the last made swap
            startIdx = newStartIdx + 1;
        }
    }
}

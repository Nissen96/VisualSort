public class BubbleSort extends VisualSort {
    BubbleSort() {
        super("Bubble Sort");
    }

    /**
     * Sort array with the Bubble Sort algorithm
     * Bubble Sort starts from the bottom on each iteration and swaps elements, which are out of order
     * This "bubbles" the largest value to the top on each iteration
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int length = A.length;

        // Repeatedly step through the list until sorted
        for (int i = 0; i < length - 1; i++) {
            boolean swapped = false;

            // Compare each pair of adjacent elements
            // Swap them if they are out of order
            for (int j = 0; j < length - i - 1; j++) {
                if (A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                    swapped = true;
                    visualize(A);
                }
            }

            // Array is sorted if no swaps were performed
            if (!swapped) {
                break;
            }
        }
    }
}

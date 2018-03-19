public class SelectionSort extends VisualSort {
    SelectionSort() {
        super("Selection Sort");
    }

    /**
     * Sort array with the Selection Sort algorithm
     * Select the currently smallest element of the array and insert into the sorted array
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int length = A.length;
        int min;

        // Iterate over each element, looking for the smallest non-sorted
        for (int i = 0; i < length - 1; i++) {
            // Current element is set as smallest
            min = i;

            // Compare to the rest of the array
            for (int j = i + 1; j < length; j++) {
                // Update minimum when a smaller element is found
                if (A[j] < A[min]) {
                    min = j;
                }
                // Visualize the search for the smallest element
                visualize(A, j);
            }

            // Swap minimum element with the current
            swap(A, min, i);
        }
    }
}

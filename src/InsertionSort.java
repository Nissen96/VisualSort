public class InsertionSort extends VisualSort {
    InsertionSort() {
        super("Insertion Sort");
    }

    /**
     * Sort array with the Insertion Sort algorithm
     * The algorithm "inserts" the next element into the sorted part of the array
     * It removes one element from the input data, finds its location in the sorted array and inserts it
     * Before inserting, all greater elements are moved upwards to make room
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int index, value;

        // Iterate over each element
        for (int i = 1; i < A.length; i++) {
            // Get current value and index
            value = A[i];
            index = i;

            // Move any previous elements smaller than the current up
            while (index > 0 && A[index - 1] > value) {
                A[index] = A[index - 1];
                index--;
                visualize(A);
            }

            // Insert element into its location in the sorted array
            A[index] = value;
            visualize(A);
        }
    }
}

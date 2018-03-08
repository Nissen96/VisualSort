public class GnomeSort extends VisualSort {
    GnomeSort() {
        super("Gnome Sort");
    }

    /**
     * Sort array with the Gnome Sort algorithm
     * Similar to Insertion Sort, but the value isn't "inserted" into its correct position
     * but instead swapped down like Bubble Sort
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int idx = 0;
        int length = A.length;

        // Do until sorted
        while (idx < length) {
            // Move forwards if the two compared elements are in correct order
            // Also if the index is at the first element
            if (idx == 0 || A[idx] >= A[idx -1]) {
                idx++;
                visualize(A);
            // If the elements are out of order, swap them and go a step back
            } else {
                swap(A, idx, idx - 1);
                idx--;
                visualize(A);
            }
        }
    }
}

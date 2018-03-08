abstract public class ShellSort extends VisualSort {
    ShellSort(String title) {
        super(title);
    }

    private int[] gaps;

    /**
     * Sort array with the Shell Sort algorithm
     * Shell sort functions like insertion sort, but sorts elements with a gap between
     * This gap decreases - a gap of 1 is exactly insertion sort
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Work down from the largest gap to a gap of 1
        for (int gap : this.gaps) {
            // Do a gapped insertion sort for this gap size
            // The first gap elements a[0..gap-1] are already in gapped order
            // (like skipping the first element in insertion sort)
            // Keep adding one more element until the entire array is gap sorted
            for (int i = gap; i < A.length; i++) {
                // Make a hole at position i
                int temp = A[i];

                // Shift earlier gap-sorted elements up until the correct location for A[i] is found
                // This is exactly like insertion sort, but it is done on sublists with gaps in between
                int j;
                for (j = i; j >= gap && A[j - gap] > temp; j -= gap) {
                    A[j] = A[j - gap];
                    visualize(A);
                }

                // Insert the original A[i] in its correct location
                A[j] = temp;
                visualize(A);
            }
        }
    }

    void setGaps(int[] gaps) {
        this.gaps = gaps;
    }
}

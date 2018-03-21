public class RandomSort extends VisualSort {
    RandomSort() {
        super("Random Sort");
    }

    /**
     * Sort array with as random an algorithm as possible
     * The algorithm creates new random arrays of ints within the same range as the original array
     * Repeats until the random array contains the same values as the original array, but sorted
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Get min and max from A
        int n = A.length;
        MinMax mm = Util.getMinMax(A);
        int min = mm.getMin();
        int max = mm.getMax();

        // Create array for side by side comparison visualization
        int[] comparison = new int[n * 2 + 1];

        // Copy A into the right side of the comparison array
        System.arraycopy(A, 0, comparison, n + 1, n);

        // Visualize the comparison array, now containing A in its right half
        try {
            clear();
            visualize(comparison);
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // Create new randomized array of ints between min and max
        int[] randomized = Util.getRandomArray(n, min, max);

        // Copy randomized array into the left side of the comparison array
        System.arraycopy(randomized, 0, comparison, 0, n);

        // Visualize side by side comparison for a second
        try {
            visualize(comparison);
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // Get new randomized arrays until one is the sorted version of A
        // It should then be sorted and contain all the same values as A
        while (!(Util.isSorted(randomized) && Util.hasSameValues(A, randomized))) {
            // Create new randomized array of ints between min and max
            randomized = Util.getRandomArray(n, min, max);

            // Copy into left half of comparison and visualize side by side
            System.arraycopy(randomized, 0, comparison, 0, n);
            visualize(comparison);
        }

        // Copy now sorted array back into A to sort it
        // Wait for a second before visualizing the sorting being completed
        System.arraycopy(randomized, 0, A, 0, n);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

    }
}

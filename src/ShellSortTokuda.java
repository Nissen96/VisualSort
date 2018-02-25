public class ShellSortTokuda extends ShellSort {
    ShellSortTokuda() {
        super("Shell Sort - Tokuda", 1);
    }

    ShellSortTokuda(int delay) {
        super("Shell Sort - Tokuda", delay);
    }

    /**
     * Shell Sort with Tokuda's gap sequence, 1992
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Tokuda's gaps
        int[] gaps = {1182, 525, 233, 103, 46, 20, 9, 4, 1};
        setGaps(gaps);
        super.doSort(A);
    }
}

public class ShellSortOrg extends ShellSort {
    ShellSortOrg() {
        super("Shell Sort - Donald Shell");
    }

    /**
     * Shell Sort with Donald Shell's original gaps, 1959
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Donald Shell's gaps
        int[] gaps = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        setGaps(gaps);
        super.doSort(A);
    }
}

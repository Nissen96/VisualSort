public class ShellSortCiura extends ShellSort {
    ShellSortCiura() {
        super("Shell Sort - Marcin Ciura", 1);
    }

    ShellSortCiura(int delay) {
        super("Shell Sort - Marcin Ciura", delay);
    }

    /**
     * Shell Sort with Marcin Ciura's gap sequence, 2001
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Marcin Ciura's gaps
        int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
        setGaps(gaps);
        super.doSort(A);
    }
}

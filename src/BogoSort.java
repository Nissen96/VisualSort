public class BogoSort extends VisualSort {
    BogoSort() {
        super("Bogo Sort");
    }

    /**
     * Sort array with the Bogo Sort algorithm
     * Bogo Sort shuffles an array randomly until sorted
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        while (!isSorted(A)) {
            shuffle(A);
            visualize(A);
        }
    }
}

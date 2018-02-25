public class NoSort extends VisualSort{
    NoSort() {
        super("No algorithm specified...", 1);
    }

    NoSort(int delay) {
        super( "No algorithm specified", delay);
    }

    /**
     * This class and method is just for error handling
     * If a user provides a wrong title for a sorting algorithm, it just does nothing     *
     * @param A - The array to do nothing to
     */
    public void doSort(int[] A) {}
}

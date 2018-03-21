public class IntroSort extends VisualSort {
    IntroSort() {
        super("Intro Sort");
    }

    public void doSort(int[] A) {
        int maxDepth = (int) (Math.log(A.length) / Math.log(2)) * 2;
        introSort(A, 0, A.length - 1, maxDepth);
    }

    private static void introSort(int[] A, int p, int r, int maxDepth) {
        int n = A.length;
        if (n <= 1) {
            return;
        }

        if (maxDepth == 0)
            heapSort(A);
        else {
            int q = partition(A, p, r);
            introSort(A, p, q, maxDepth - 1);
            introSort(A, q + 1, r, maxDepth - 1);
        }
    }

    private static int partition(int[] A, int p, int r) {
        return -1;
    }

    private static void heapSort(int[] A) {

    }
}

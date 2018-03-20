public class PigeonholeSort extends VisualSort {
    PigeonholeSort() {
        super("Pigeonhole Sort");
    }

    public void doSort(int[] A) {
        MinMax mm = Util.getMinMax(A);
        int min = mm.getMin();
        int max = mm.getMax();
        int range = max - min + 1;

        int[] phole = new int[range];
        for (int i = 0; i < A.length; i++) {
            phole[A[i] - min]++;
            visualize(A, i);
        }
        int index = 0;

        for (int j = 0; j < range; j++) {
            while (phole[j]-- > 0) {
                A[index++] = j + min;
                visualize(A);
            }
        }
    }
}

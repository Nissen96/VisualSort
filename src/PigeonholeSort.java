public class PigeonholeSort extends VisualSort {
    PigeonholeSort() {
        super("Pigeonhole Sort");
    }

    /**
     * Sort array with the Pigeonhole Sorting algorithm
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Find minimum and maximum values in A
        MinMax mm = Util.getMinMax(A);
        int min = mm.getMin();
        int max = mm.getMax();

        // Find range
        int range = max - min + 1;

        // Create new array of buckets or empty "pigeon holes"
        // Each hole will contain matching elements
        int[] phole = new int[range];

        // Put every element in its respective hole
        for (int i = 0; i < A.length; i++) {
            phole[A[i] - min]++;
            visualize(A, i);
        }

        // Traverse through each hole
        // Add the number of each element from each hole to array
        int index = 0;
        for (int j = 0; j < range; j++) {
            // Count down # of elements in this hole
            while (phole[j]-- > 0) {
                A[index++] = j + min;
                visualize(A);
            }
        }
    }
}

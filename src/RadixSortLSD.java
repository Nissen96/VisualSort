abstract class RadixSortLSD extends RadixSort {
    RadixSortLSD(String title) {
        super(title, 1);
    }

    RadixSortLSD(String title, int delay) {
        super(title, delay);
    }

    /**
     * Sort array with the Radix Sort algorithm
     * Radix Sort sorts the array by sorting on each digit separately
     * The numbers are sorted from least significant digit and up
     * It uses Counting Sort as a subroutine
     * @param A - The array to sort
     */
    void doSort(int[] A) {
        int b = getBase();
        int k = getMax(A);

        // Compute the number of digits needed to represent k in base b
        int digits = (int) (Math.log(k) / Math.log(b) + 1);

        // Sort with counting sort on each digit, from least significant
        for (int i = 0; i < digits; i++) {
            countingSort(A, i);
        }
    }
}

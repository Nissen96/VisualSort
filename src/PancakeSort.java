public class PancakeSort extends VisualSort {
    PancakeSort() {
        super("Pancake Sort");
    }

    /**
     * Sort array with the Pancake Sort algorithm
     * The point is to sort an array with only the operation:
     *   flip(arr, i): Reverse array from 0 to i
     * This resembles flipping a stack of pancakes
     * On each pass the largest element is placed at the top
     * This is done by firstly finding the largest element
     * The array up until and including this is then flipped, placing it as the first element
     * The entire array up until the last largest element is flipped again, moving the element to the end
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        int len = A.length;

        // Start from the complete array
        // and one by one reduce current size by one
        for (int curSize = len; curSize > 1; curSize--) {
            // Find index of the maximum element in A[0..cur_size-1]
            int maxIdx = getMaxIdx(A, curSize);

            // Move the maximum element to the end of the current array
            // if not already there
            if (maxIdx != curSize - 1) {
                // First move maximum number to the beginning
                flip(A, maxIdx);

                // Then move the maximum number to the end by reversing the current array
                flip(A, curSize - 1);
            }
        }
    }

    /**
     * Get the index of the largest element in the array
     * @param A - The array to search
     * @param n - The index to stop searching at
     * @return the index of the largest element
     */
    private int getMaxIdx(int[] A, int n) {
        int i, maxIdx;
        // Keep track of the index of the largest element
        for (i = 0, maxIdx = 0; i < n; i++) {
            // Update if larger
            if (A[i] > A[maxIdx])
                maxIdx = i;
            visualize(A, i);
        }
        return maxIdx;
    }

    /**
     * Reverse an array up until the index i
     * @param A - The array to reverse
     * @param i - The index to stop at
     */
    private void flip(int[] A, int i) {
        int start = 0;
        // Swap each outer elements and move inwards
        while (start < i) {
            Util.swap(A, start, i);
            visualize(A);

            start++;
            i--;
        }
    }
}

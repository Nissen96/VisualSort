import java.util.Arrays;

public class CountingSort extends VisualSort {
    CountingSort() {
        super("Counting Sort");
    }

    /**
     * Sort array with the counting sort algorithm
     * It counts the number of elements with each distinct value
     * The position is then determined for each by calculating the cumulative sum
     * Only sorts integers - This implementation only sorts non-negatives
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Find largest element in the array
        int k = Util.getMax(A);

        // Make a copy of A - Usually an empty array of size A.length would be made
        // But it is more visually pleasing to do everything in the same array as the unsorted values
        int[] B = Util.arrayCopy(A);

        // Make an array to count each distinct value
        int[] C = new int[k + 1];

        // Let C[i] contain the number of elements equal to i
        C[0]--;
        for (int i = 0; i < A.length; i++) {
            C[A[i]] += 1;
            // This visualization simply shows the iteration
            visualize(A, i);
        }

        // Let C[i] contain the number of elements less than or equal to i
        // This is the same as inputting the cumulative sum up until i for each C[i]
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
            // Same visualization as above
            visualize(A, i);
        }

        // Iterate backwards over B (keeps the sort stable)
        // Insert the values from B in their correct spot in A (found in C)
        int value;
        for (int i = B.length - 1; i >= 0; i--) {
            value = B[i];
            A[C[value]] = value;
            C[value]--;

            visualize(A);
        }
    }
}

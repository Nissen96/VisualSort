public class MergeSort3Way extends MergeSort {
    MergeSort3Way() {
        super("3-Way Merge Sort");
    }

    /**
     * Helper function for the first call to Merge Sort
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Call Merge Sort on entire array
        mergeSort3Way(A, 0, A.length);
    }

    /**
     * Sort array with the 3-Way Merge Sort algorithm
     * Like regular Merge Sort but the array is split in 3 on each pass
     * @param A - Array to sort
     * @param low - The start index of the current subarray
     * @param high - The end index of the current subarray
     */
    private void mergeSort3Way(int[] A, int low, int high) {
        // Arrays of size 1 are trivially sorted
        if (high - low <= 1) {
            return;
        }

        // Split array into 3 parts
        int size = high - low;
        int mid1 = low + (size / 3);
        int mid2 = low + 2 * (size / 3) + 1;

        // Sort each part recursively
        mergeSort3Way(A, low, mid1);
        mergeSort3Way(A, mid1, mid2);
        mergeSort3Way(A, mid2, high);

        // Merge sorted arrays
        merge3Way(A, low, mid1, mid2, high);
    }

    /**
     * Merge three array parts in sorted order
     * @param A - Array to merge in
     * @param low - Start index of the current subarray
     * @param mid1 - First middle index of the current subarray
     * @param mid2 - Second middle index of the current subarray
     * @param high - End index of the current subarray
     */
    private void merge3Way(int[] A, int low, int mid1, int mid2, int high) {
        // Create copy of A to merge from
        int[] B = Util.arrayCopy(A);

        // Indices to keep track of current position in left, middle and right part of array
        int i = low;
        int j = mid1;
        int k = mid2;

        int idx = low;

        // Choose smallest element between all three parts
        // Do until one of the parts are empty
        while (i < mid1 && j < mid2 && k < high) {
            if (B[i] < B[j]) {
                if (B[i] < B[k]) {
                    A[idx++] = B[i++];
                } else {
                    A[idx++] = B[k++];
                }
            } else {
                if (B[j] < B[k]) {
                    A[idx++] = B[j++];
                } else {
                    A[idx++] = B[k++];
                }
            }

            visualize(A);
        }

        // Case where first and second part have remaining values
        while (i < mid1 && j < mid2) {
            if (B[i] < B[j]) {
                A[idx++] = B[i++];
            } else {
                A[idx++] = B[j++];
            }

            visualize(A);
        }

        // Case where second and third part have remaining values
        while (j < mid2 && k < high) {
            if (B[j] < B[k]) {
                A[idx++] = B[j++];
            } else {
                A[idx++] = B[k++];
            }

            visualize(A);
        }

        // Case where first and third part have remaining values
        while (i < mid1 && k < high) {
            if (B[i] < B[k]) {
                A[idx++] = B[i++];
            } else {
                A[idx++] = B[k++];
            }

            visualize(A);
        }

        // Copy remaining values from first part
        while (i < mid1) {
            A[idx++] = B[i++];
            visualize(A);
        }

        // Copy remaining values from second part
        while (j < mid2) {
            A[idx++] = B[j++];
            visualize(A);
        }

        // Copy remaining values from third part
        while (k < high) {
            A[idx++] = B[k++];
            visualize(A);
        }
    }
}

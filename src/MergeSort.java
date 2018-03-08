abstract class MergeSort extends VisualSort {
    /* MergeSort is an abstract class providing the merge method
    to the concrete implementations of merge sort*/
    MergeSort(String title) {
        super(title);
    }

    /**
     * Merge two sorted parts of an array into one
     * @param A - The array to merge from and to
     * @param low - The index of the first element of the left part of the array
     * @param mid - The split point between the array parts to merge
     * @param hi - The index of the last element of the right part of the array
     */
    void merge(int[] A, int low, int mid, int hi) {
        // Get size of left and right sublist
        int leftSize = mid - low + 1;
        int rightSize = hi - mid;

        // Copy left and right sublist into new arrays
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];
        System.arraycopy(A, low, left, 0, leftSize);
        System.arraycopy(A, mid + 1, right, 0, rightSize);

        // Compare elements from the start of left and right
        // Place the smallest at the current starting position in the list A
        int i = 0;
        int j = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i] < right[j]) {
                A[low] = left[i];
                i++;
            } else {
                A[low] = right[j];
                j++;
            }
            // Update position in A
            low++;
            visualize(A);
        }

        // Insert remaining elements from left array (if any)
        int k = low;
        while (i < leftSize) {
            A[k] = left[i];
            i++;
            k++;
            visualize(A);
        }

        // Insert remaining elements from right array (if any)
        while (j < rightSize) {
            A[k] = right[j];
            visualize(A);
            j++;
            k++;
        }
    }
}

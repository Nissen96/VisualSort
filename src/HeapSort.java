public class HeapSort extends VisualSort {
    HeapSort() {
        super("Heap Sort");
    }

    /**
     * Sort array with the Heap Sort algorithm
     * The Heap Sort first builds a max heap from the array
     * Now the largest element is the root, the first element of the array
     * This is removed and inserted into the sorted part of the array, which is not part of the heap
     * Max-Heapify the heap again and do until the array is sorted
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int heapSize = A.length;

        // Build the max-heap
        buildMaxHeap(A);

        for (int i = A.length - 1; i > 0; i--) {
            // Swap the largest element, the root, into the sorted part of the array
            Util.swap(A, 0, i);
            visualize(A);

            // The element is then removed from the heap
            heapSize--;

            // Max-Heapify the element that was swapped into the root position
            maxHeapify(A, 0, heapSize);
        }
    }

    /**
     * Build a max heap from an array of numbers
     * @param A - The array to build a max-heap from
     */
    private void buildMaxHeap(int[] A) {
        int heapSize = A.length;
        // Call max heapify on each node of the tree
        // starting from the parent of the last leaf
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            maxHeapify(A, i, heapSize);
        }
    }

    /**
     * Insert an element in a heap into its correct position
     * Done by moving sifting it down the "tree" until it satisfies heap property
     * @param A - The array (priority queue)
     * @param i - The index of the element
     * @param heapSize - The size of the heap
     */
    private void maxHeapify(int[] A, int i, int heapSize) {
        // Get the indices of the current element's children
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Find largest node among the current and its two children
        int largest = i;
        if (left < heapSize && A[left] > A[i]) {
            largest = left;
        }
        if (right < heapSize && A[right] > A[largest]) {
            largest = right;
        }

        // Swap the current node with the largest found
        if (largest != i) {
            Util.swap(A, i, largest);
            visualize(A);

            // Max-Heapify recursively on the largest element
            maxHeapify(A, largest, heapSize);
        }
    }
}

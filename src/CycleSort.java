public class CycleSort extends VisualSort {
    CycleSort() {
        super("Cycle Sort");
    }

    /**
     * Sort array with the Cycle Sort algorithm
     * For each element a, we can find its position in the sorted list
     * This is done by counting all the elements in the list smaller than a
     * That position is being held by another element b, which we then move
     * When any element is moved to the original position of a, a cycle has been completed
     * This repeats until the array is sorted
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        // Loop through the array to find cycles to rotate
        int item, pos, tmp;
        for (int cycleStart = 0; cycleStart < A.length - 1; cycleStart++) {
            // Get first item of the cycle
            item = A[cycleStart];

            // Find where to put the item by counting all elements that come before it
            pos = cycleStart;
            for (int i = cycleStart + 1; i < A.length; i++) {
                if (A[i] < item) {
                    pos++;
                    visualize(A, i);
                }
            }

            // Continue if the item is at its correct position
            // Then there is no cycle there
            if (pos == cycleStart)
                continue;

            // Skip any duplicates
            while (item == A[pos])
                pos++;

            // Insert item into found position and get the element previously held by this position
            tmp = A[pos];
            A[pos] = item;
            item = tmp;

            visualize(A);

            // Rotate the rest of the cycle
            while (pos != cycleStart) {
                // Find where to put the item
                pos = cycleStart;
                for (int i = cycleStart + 1; i < A.length; i++) {
                    if (A[i] < item) {
                        pos++;
                        visualize(A, i);
                    }
                }

                // Skip any duplicates
                while (item == A[pos])
                    pos++;

                // Insert item into found position and get the element previously at this position
                tmp = A[pos];
                A[pos] = item;
                item = tmp;

                visualize(A);
            }
        }
    }
}

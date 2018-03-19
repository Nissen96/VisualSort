abstract class RadixSort extends VisualSort {
    RadixSort(String title) {
        super(title);
    }

    private int base;

    int getBase() {
        return base;
    }

    void setBase(int base) {
        this.base = base;
    }

    /**
     * Sort array of numbers on a specific digit with Counting Sort
     * @param A - The array to sort
     * @param digit - The digit to sort by
     */
    void countingSort(int[] A, int digit) {
        // Make a copy of A and a counter C which stores the count of each digit
        // The numbers are in the given base and C therefore only needs to store base positions
        int[] B = arrayCopy(A);
        int[] C = new int[base];
        int digitOfNumber;

        // Count the number of occurrences of each digit in B
        for (int i = 0; i < B.length; i++) {
            // Get the digit from B on position 'digit'
            digitOfNumber = (int) (B[i] / Math.pow(base, digit)) % base;
            C[digitOfNumber]++;
            //visualize(A, i);
        }

        // Compute the cumulative # of digits of C
        for (int i = 1; i < base; i++) {
            C[i] += C[i - 1];
        }

        // Iterate backwards over B (keeps the sort stable)
        // Insert the values from B in their correct spot in A (found in C)
        for (int i = A.length - 1; i >= 0; i--) {
            // Get the digit from B on position 'digit'
            digitOfNumber = (int) (B[i] / Math.pow(base, digit)) % base;
            C[digitOfNumber]--;
            A[C[digitOfNumber]] = B[i];

            visualize(A);
        }
    }
}

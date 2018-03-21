import java.util.ArrayList;
import java.util.List;

abstract public class RadixSortMSD extends RadixSort {
    RadixSortMSD(String title) {
        super(title);
    }

    /**
    * Sort array with the Radix Sort (MSD) algorithm
    * Radix Sort sorts the array by sorting on each digit separately
    * Radix Sort (MSD) sorts from most significant digit and up
    * @param A - The array to sort
    */
    public void doSort(int[] A) {
        // Compute the number of digits needed to represent the max value in the base
        int base = getBase();
        int max = Util.getMax(A);
        int digits = (int) (Math.log(max) / Math.log(base) + 1);

        // Sort array recursively with Raidx Sort (MSD)
        radixSortMSD(A, digits, 0, A.length);
    }

    /**
     * Sort a subarray with Radix Sort (MSD)
     * Sort on the digit d in each number and place numbers in their correct bucket
     * Sort each bucket recursively
     * @param A - The array to sort
     * @param digit - The current digit to sort on
     * @param low - The start index of the subarray
     * @param high - The end index of the subarray
     */
    private void radixSortMSD(int[] A, int digit, int low, int high) {
        // Stop when the current digit to sort on reaches 0
        if (digit < 1)
            return;

        // Create a bucket for each number in the base
        int base = getBase();
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < base; i++) {
            buckets.add(i, new ArrayList<>());
        }

        // Add each number to their bucket
        for (int i = low; i < high; i++) {
            // Get the digit from each number on the current digit position
            int num = A[i];
            int digitOfNumber = (int) (num / Math.pow(base, digit - 1)) % base;

            // Add the number to the corresponding digit bucket
            buckets.get(digitOfNumber).add(num);
        }

        // Concatenate each bucket into the array in order
        int idx = low;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                A[idx++] = num;
                visualize(A);
            }
        }

        // Sort each bucket recursively on the next digit downwards
        idx = low;
        for (List<Integer> bucket : buckets) {
            radixSortMSD(A, digit - 1, idx, idx + bucket.size());

            // Start idx of next bucket
            idx += bucket.size();
        }
    }
}

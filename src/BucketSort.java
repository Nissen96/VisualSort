import java.util.ArrayList;
import java.util.List;

public class BucketSort extends VisualSort {
    BucketSort() {
        super("Bucket Sort");
    }

    /**
     * Sort array with the Bucket Sort algorithm
     * Bucket Sort does the following:
     *   1) Create a bucket for each element in the list
     *   2) Insert each element into a bucket with index (value * length / (maxVal + 1))
     *   3) Sort each individual bucket with insertion sort
     *   4) Concatenate all buckets
     *
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;
        int max = Util.getMax(A);

        // Create n empty buckets
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i : A) {
            buckets.add(new ArrayList<>());
        }

        // Put array elements in correct buckets
        for (int val : A) {
            // Calculate bucket index for value
            int bucketIdx = val * n / (max + 1);
            buckets.get(bucketIdx).add(val);
        }

        // Sort individual buckets
        for (List<Integer> bucket : buckets) {
            insertionSort(bucket);
        }

        // Concatenate all buckets into A[]
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets.get(i).size(); j++) {
                A[idx++] = buckets.get(i).get(j);
                visualize(A);
            }
        }
    }

    /**
     * Sort list with the Insertion Sort algorithm
     * The algorithm "inserts" the next element into the sorted part of the array
     * It removes one element from the input data, finds its location in the sorted array and inserts it
     * Before inserting, all greater elements are moved upwards to make room
     * @param list - List to sort
     */
    private static void insertionSort(List<Integer> list) {
        int idx, val;

        // Iterate over each element
        for (int i = 1; i < list.size(); i++) {
            // Get current value and index
            val = list.get(i);
            idx = i;

            // Move any previous elements smaller than the current up
            while (idx > 0 && list.get(idx - 1) > val) {
                list.set(idx, list.get(idx - 1));
                idx--;
            }

            // Insert element into its location in the sorted array
            list.set(idx, val);
        }
    }
}

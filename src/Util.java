import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    /**
     * Copy an array
     * @param array - The array to copy
     * @return the copy of the array
     */
    static int[] arrayCopy(int[] array) {
        // Make a new array of the same size as the old
        int[] copy = new int[array.length];

        // Copy and return the new array
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    /**
     * Swap two elements in an array
     * @param array - The array to swap in
     * @param i - The index of the first element
     * @param j - The index of the second element
     */
    static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Check if an array is sorted
     * @param array - The array to check
     * @return whether or not the array is sorted
     */
    static boolean isSorted(int[] array) {
        // Check that no element is larger than the next
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Randomize an array in place
     * @param array - Array to randomize
     */
    static void shuffle(int[] array) {
        Random rnd = ThreadLocalRandom.current();

        int idx, tmp;
        // Iterate over array from the top and down
        for (int i = array.length - 1; i > 0; i--) {
            // Pick a random index to swap the current with
            idx = rnd.nextInt(i + 1);

            // Swap element at index idx and i
            tmp = array[idx];
            array[idx] = array[i];
            array[i] = tmp;
        }

    }

    /**
     * Get the largest value in an array
     * @param array - The array to search
     * @return the largest value in the array
     */
    static int getMax(int[] array) {
        // Get min and max from array and return max
        return getMinMax(array).getMax();
    }

    /**
     * Get the smallest value in an array
     * @param array - The array to search
     * @return the smallest value in the array
     */
    static int getMin(int[] array) {
        // Get min and max from array and return min
        return getMinMax(array).getMin();
    }

    static MinMax getMinMax(int[] array) {
        // Initialize min and max as the first value
        int min = array[0];
        int max = array[0];

        // Update min and max until smallest and largest values are found
        for (int val : array) {
            if (val < min) min = val;
            if (val > max) max = val;
        }

        // Return min and max
        return new MinMax(min, max);
    }

}

/**
 * Class for storing minimum and maximum values from an array
 */
class MinMax {
    private int min;
    private int max;

    MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

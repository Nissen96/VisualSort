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
        // Initialize the largest value to the first
        int max = array[0];

        // Update max if any larger value is found
        for (int i : array) {
            if (i > max) max = i;
        }
        return max;
    }
}

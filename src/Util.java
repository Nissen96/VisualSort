import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Util {
    /**
     * Get an already sorted array of integers
     * @param size - Number of elements in array
     * @return sorted array
     */
    static int[] getRange(int size) {
        // Get a range of ints from 1..size
        return IntStream.range(1, size + 1).toArray();
    }

    /**
     * Get a sorted array of integers in a given range
     * @param size - Number of elements in array
     * @param offset - Integer to start from
     * @return sorted array
     */
    static int[] getRange(int size, int offset) {
        // Get a range of ints from offset...offset + size
        return IntStream.range(offset, offset + size + 1).toArray();
    }

    /**
     * Get an array of integers in reverse order
     * @param size - Number of elements in array
     * @return array sorted in reverse order
     */
    static int[] getReversedRange(int size) {
        // Get a range of ints from size..1
        return IntStream.range(1, size + 1).map(i -> size + 1 - i).toArray();
    }

    /**
     * Get an array of integers in a given range in reverse order
     * @param size - Number of elements in array
     * @param offset - Integer to end on
     * @return array sorted in reverse order
     */
    static int[] getReversedRange(int size, int offset) {
        return IntStream.range(1, size + 1).map(i -> offset + size - i).toArray();
    }

    /**
     * Get an array of integers in random order
     * @param size - Number of elements in array
     * @return array in random order
     */
    static int[] getRandomRange(int size) {
        // Get a sorted array and randomize it
        int[] array = getRange(size);
        shuffle(array);
        return array;
    }

    /**
     * Get an array of integers in a given range in random order
     * @param size - Number of elements in array
     * @param offset - Integer to start from
     * @return array in random order
     */
    static int[] getRandomRange(int size, int offset) {
        // Get a sorted array starting from a given integer and randomize it
        int[] array = getRange(size, offset);
        shuffle(array);
        return array;
    }

    /**
     * Get an array of random integers
     * @param size - Number of elements in array
     * @return array of random integers
     */
    static int[] getRandomArray(int size) {
        Random rnd = ThreadLocalRandom.current();

        // Create a new array of the given size
        int[] array = new int[size];

        // Fill array with random ints
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt();
        }

        return array;
    }

    /**
     * Get an array of random integers in a given range
     * @param size - Number of elements in array
     * @param min - Minimum number in range
     * @param max - Maximum number in range
     * @return array of random integers
     */
    static int[] getRandomArray(int size, int min, int max) {
        // Create a new array of the given size
        int[] array = new int[size];

        // Fill array with random ints within range
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        return array;
    }

    /**
     * Get a random array of integers with evenly spaced duplicates
     * @param size - The number of elements in array
     * @param numDistinct - The number of distinct elements
     * @return sorted array with duplicates
     */
    static int[] getDuplicatesArray(int size, int numDistinct) {
        // Return if size isn't divisible by the number of distinct elements
        if (size % numDistinct != 0) {
            return new int[size];
        }

        // Initialize new array
        int[] array = new int[size];

        // Calculate number of each distinct integer
        int numOfEach = size / numDistinct;

        // For each distinct integer, fill the array with the correct amount
        for (int i = 0; i < numDistinct; i++) {
            for (int j = 0; j < numOfEach; j++) {
                array[i * numOfEach + j] = (i + 1) * numOfEach;
            }
        }

        shuffle(array);
        return array;
    }

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

    static boolean hasSameValues(int[] A, int[] B) {
        // Arrays must have different values if their sizes differ
        if (A.length != B.length) {
            return false;
        }

        // Create a copy of both A and B
        int[] Acopy = arrayCopy(A);
        int[] Bcopy = arrayCopy(B);

        // Sort both copies
        Arrays.sort(Acopy);
        Arrays.sort(Bcopy);

        // Check if copies are equal
        return Arrays.equals(Acopy, Bcopy);
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

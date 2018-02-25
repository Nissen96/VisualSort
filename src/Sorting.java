import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Visualizing sorting algorithms.
 * @author Alexander F. Nissen (alnis17@student.dk)
 */
public class Sorting {
    public static void main(String[] args) throws InterruptedException {
        // Reverse sorted arrays
        // int[] xxsRvs = getReversedArray(10);
        //int[] xsRvs = getReversedArray(50);
        // int[] sRvs = getReversedArray(100);
        //int[] mRvs = getReversedArray(200);
        // int[] lRvs = getReversedArray(400);
        //int[] xlRvs = getReversedArray(800);
        // int[] xxlRvs = getReversedArray(1200);

        // Duplicates arrays
        // int[] xxsDpl = getDuplicatesArray(10, 2);
        // int[] xsDpl = getDuplicatesArray(50, 5);
        // int[] sDpl = getDuplicatesArray(100, 10);
        // int[] mDpl = getDuplicatesArray(200, 20);
        // int[] lDpl = getDuplicatesArray(400, 40);
        // int[] xlDpl = getDuplicatesArray(800, 80);
        // int[] xxlDpl = getDuplicatesArray(1200, 120);

        // Random arrays
        // int[] xxsRnd = getRandomArray(10);
        int[] xsRnd = getRandomArray(50);
        // int[] sRnd = getRandomArray(100);
        int[] mRnd = getRandomArray(200);
        // int[] lRnd = getRandomArray(400);
        int[] xlRnd = getRandomArray(800);
        // int[] xxlRnd = getRandomArray(1200);

        // Slow sorting algorithms
        // Bead Sort is very fast, but the visualization is very slow
        String[] slowSorters = {
                "insertion",
                "bubble",
                "selection",
                "cocktail",
                "gnome",
                "bead",
                "cycle",
                "slow",
                "stooge"
        };
        sort(mRnd, slowSorters);

        // Quick sorting algorithms
        String[] quickSorters = {
                "mergetd",
                "mergebu",
                "quick",
                "heap",
                "count",
                "radixlsd2",
                "radixlsd10",
                "shell",
                "shelltokuda",
                "shellciura"
        };
        sort(xlRnd, quickSorters, 3);

        // Stupid sorting algorithms
        String[] dumbSorters = {"bogo"};
        sort(xsRnd, dumbSorters, 0);
    }

    /**
     * Call sort() with a delay set to 1
     * @param array - Array to sort
     * @param sorters - Algorithms to sort with
     * @throws InterruptedException - Thrown by Thread.sleep()
     */
    private static void sort(int[] array, String[] sorters) throws InterruptedException {
        sort(array, sorters, 1);
    }

    /**
     * Sort an array with each of the given sorting algorithms
     * @param array - Array to sort
     * @param sorters - Algorithms to sort with
     * @param delay - Delay between each visualization call
     * @throws InterruptedException - Thrown by Thread.sleep()
     */
    private static void sort(int[] array, String[] sorters, int delay) throws InterruptedException {
        // Go over each sorting algorithm
        VisualSort sorter;
        for (String sorterTitle : sorters) {
            // Initialize the sorting algorithm with the given delay
            sorter = displaySorter(sorterTitle, delay);

            // Display the array for a second before sorting
            sorter.visualize(array);
            Thread.sleep(1000);

            // Sort array - not done in place since the same array is used for each sorter
            sorter.sort(array);
            Thread.sleep(2000);
        }
    }

    /**
     * Get an already sorted array of integers
     * @param size - Number of elements in array
     * @return sorted array
     */
    private static int[] getSortedArray(int size) {
        // Get a range of ints from 1..size
        return IntStream.range(1, size + 1).toArray();
    }

    /**
     * Get an array of integers in reverse order
     * @param size - Number of elements in array
     * @return array sorted in reverse order
     */
    private static int[] getReversedArray(int size) {
        // Get a range of ints from size..1
        return IntStream.range(1, size + 1).map(i -> size - i + 1).toArray();
    }

    /**
     * Get an array of integers in random order
     * @param size - Number of elements in array
     * @return array in random order
     */
    private static int[] getRandomArray(int size) {
        // Get a sorted array and randomize it
        int[] array = getSortedArray(size);
        return randomize(array);
    }

    /**
     * Randomize an array
     * @param array - The array to randomize
     * @return the array with elements in random order
     */
    private static int[] randomize(int[] array) {
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

        return array;
    }

    /**
     * Get a random array of integers with evenly spaced duplicates
     * @param size - The number of elements in array
     * @param numDistinct - The number of distinct elements
     * @return sorted array with duplicates
     */
    private static int[] getDuplicatesArray(int size, int numDistinct) {
        // Return if size isn't divisible by the number of distinct elements
        if (size % numDistinct != 0) {
            return new int[size];
        }

        // Initialize new array
        int[] array = new int[size];

        // Calculate number of each distinct integer
        int numOfEachInt = size / numDistinct;

        // For each distinct integer, fill the array with the correct amount
        for (int i = 0; i < numDistinct; i++) {
            for (int j = 0; j < numOfEachInt; j++) {
                array[i * numOfEachInt + j] = (i + 1) * numOfEachInt;
            }
        }

        return randomize(array);
    }

    /**
     * Initialize a new sorting algorithm for sorting any array of integers
     * @param title - The title of the sorter to initialize
     * @param delay - The delay between each visualization call
     * @return new sorter of the input type
     */
    private static VisualSort displaySorter(String title, int delay) {
        VisualSort sorter;

        // Convert to lowercase and remove spaces and "sort"
        title = title.toLowerCase().replaceAll("\\s|\\bsort\\b","");

        // Initialize a new sorter depending on input
        switch (title) {
            case "bead":
            case "gravity":
            case "bd":
            case "grt":
                sorter = new BeadSort(delay);
                break;
            case "bogo":
            case "bg":
            case "shotgun":
            case "sht":
            case "monkey":
            case "mnk":
                sorter = new BogoSort(delay);
                break;
            case "bubble":
            case "bbl":
                sorter = new BubbleSort(delay);
                break;
            case "cocktail":
            case "cocktailshaker":
            case "ctl":
                sorter = new CocktailSort(delay);
                break;
            case "counting":
            case "count":
            case "cnt":
                sorter = new CountingSort(delay);
                break;
            case "cycle":
            case "ccl":
                sorter = new CycleSort(delay);
                break;
            case "gnome":
            case "gnm":
                sorter = new GnomeSort(delay);
                break;
            case "heap":
            case "hp":
                sorter = new HeapSort(delay);
                break;
            case "insertion":
            case "ins":
                sorter = new InsertionSort(delay);
                break;
            case "mergebu":
            case "mergebottomup":
            case "mbu":
                sorter = new MergeSortBU(delay);
                break;
            case "mergetd":
            case "mergetopdown":
            case "mtd":
                sorter = new MergeSortTD(delay);
                break;
            case "quick":
            case "qck":
                sorter = new QuickSort(delay);
                break;
            case "radix":
            case "rdx":
            case "radix2":
            case "rdx2":
            case "radixlsd":
            case "rdxlsd":
            case "radixlsd2":
            case "rdxlsd2":
                sorter = new RadixSortLSD2(delay);
                break;
            case "radix10":
            case "radixlsd10":
            case "rdx10":
            case "rdxlsd10":
                sorter = new RadixSortLSD10(delay);
                break;
            case "selection":
            case "slc":
                sorter = new SelectionSort(delay);
                break;
            case "shell":
            case "shl":
            case "shelloriginal":
            case "shellorg":
            case "shlo":
                sorter = new ShellSortOrg(delay);
                break;
            case "shellciura":
            case "shlc":
                sorter = new ShellSortCiura(delay);
                break;
            case "shelltokuda":
            case "shlt":
                sorter = new ShellSortTokuda(delay);
                break;
            case "slow":
            case "slw":
                sorter = new SlowSort(delay);
                break;
            case "stooge":
            case "stg":
                sorter = new StoogeSort(delay);
                break;
            default:
                sorter = new NoSort(delay);
        }

        return sorter;
    }
}
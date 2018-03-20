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
        int[] xxsRvs = getReversedArray(16);
        int[] xsRvs = getReversedArray(64);
        int[] sRvs = getReversedArray(144);
        int[] mRvs = getReversedArray(256);
        int[] lRvs = getReversedArray(625);
        int[] xlRvs = getReversedArray(1024);
        int[] xxlRvs = getReversedArray(4096);

        // Duplicates arrays
        int[] xxsDpl = getDuplicatesArray(16, 8);
        int[] xsDpl = getDuplicatesArray(64, 8);
        int[] sDpl = getDuplicatesArray(144, 8);
        int[] mDpl = getDuplicatesArray(256, 8);
        int[] lDpl = getDuplicatesArray(625, 5);
        int[] xlDpl = getDuplicatesArray(1024, 8);
        int[] xxlDpl = getDuplicatesArray(4096, 8);

        // Random arrays
        int[] xxsRnd = getRandomArray(16);
        int[] xsRnd = getRandomArray(64);
        int[] sRnd = getRandomArray(144);
        int[] mRnd = getRandomArray(256);
        int[] lRnd = getRandomArray(625);
        int[] xlRnd = getRandomArray(1024);
        int[] xxlRnd = getRandomArray(4096);

        // Slow sorting algorithms
        // Bead Sort is very fast, but the visualization is very slow
        String[] slowSorters = {
            //"insertion",
            //"bubble",
            //"selection",
            //"cocktail",
            //"gnome",
            //"bead",
            //"cycle",
            //"slow",
            //"stooge",
            //"pancake",
            //"oddeven"
        };
        sort(mRnd, slowSorters);

        // Medium sorting algorithms
        String[] mediumSorters = {
            "pigeon",
            "comb"
        };
        sort(xlRnd, mediumSorters, 1);

        // Quick sorting algorithms
        String[] quickSorters = {
            "quick",
            "heap",
            "mergetd",
            "mergebu",
            "count",
            "radixlsd2",
            "radixlsd4",
            "radixlsd10",
            "shell",
            "shelltokuda",
            "shellciura",
            "bitonic"
        };
        sort(xxlRnd, quickSorters);

        // Stupid sorting algorithms
        String[] dumbSorters = {"bogo"};
        sort(xsRvs, dumbSorters, 0);
    }

    /**
     * Initialize a given sorter and sort an array
     * @param array - Array to sort
     * @param sorterName - Name of algorithm to sort with
     * @param delay - Delay between each visualization call
     * @throws InterruptedException - Thrown by Thread.sleep()
     */
    private static void sort(int[] array, String sorterName, int delay) throws InterruptedException {
        // Initialize the sorting algorithm with the given delay
        VisualSort sorter = displaySorter(sorterName);
        sorter.setDelay(delay);

        // Display the array for a second before sorting
        Thread.sleep(100);
        sorter.visualize(array);
        Thread.sleep(1000);

        // Sort array - not done in place since the same array is used for each sorter
        sorter.sort(array);
        Thread.sleep(2000);
    }

    /**
     * Call sort() with multiple sorters and a delay of 1
     */
    private static void sort(int[] array, String[] sorters) throws InterruptedException {
        sort(array, sorters, 1);
    }

    /**
     * Call sort() with multiple sorters
     */
    private static void sort(int[] array, String[] sorters, int delay) throws InterruptedException {
        // Initialize each of the given sorter names and sort
        for (String sorterTitle : sorters) {
            sort(array, sorterTitle, delay);
        }
    }

    /**
     * Get an already sorted array of integers
     * @param size - Number of elements in array
     * @return sorted array
     */
    private static int[] getSortedArray(int size) {
        // Get a range of ints from 0..size - 1
        return IntStream.range(0, size).toArray();
    }

    /**
     * Get an array of integers in reverse order
     * @param size - Number of elements in array
     * @return array sorted in reverse order
     */
    private static int[] getReversedArray(int size) {
        // Get a range of ints from size..1
        return IntStream.range(0, size).map(i -> size - i - 1).toArray();
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
        int numOfEach = size / numDistinct;

        // For each distinct integer, fill the array with the correct amount
        for (int i = 0; i < numDistinct; i++) {
            for (int j = 0; j < numOfEach; j++) {
                array[i * numOfEach + j] = (i + 1) * numOfEach;
            }
        }

        return randomize(array);
    }

    /**
     * Initialize a new sorting algorithm for sorting any array of integers
     * @param title - The title of the sorter to initialize
     * @return new sorter of the input type
     */
    private static VisualSort displaySorter(String title) {
        VisualSort sorter;

        // Convert to lowercase and remove spaces and "sort"
        title = title.toLowerCase().replaceAll("\\s|\\bsort\\b","");

        // Initialize a new sorter depending on input
        switch (title) {
            case "bead":
            case "gravity":
            case "bd":
            case "grt":
                sorter = new BeadSort();
                break;
            case "bitonic":
            case "btn":
                sorter = new BitonicSort();
                break;
            case "bogo":
            case "bg":
            case "shotgun":
            case "sht":
            case "monkey":
            case "mnk":
                sorter = new BogoSort();
                break;
            case "bubble":
            case "bbl":
                sorter = new BubbleSort();
                break;
            case "cocktail":
            case "cocktailshaker":
            case "ctl":
                sorter = new CocktailSort();
                break;
            case "comb":
            case "cmb":
                sorter = new CombSort();
                break;
            case "counting":
            case "count":
            case "cnt":
                sorter = new CountingSort();
                break;
            case "cycle":
            case "ccl":
                sorter = new CycleSort();
                break;
            case "gnome":
            case "gnm":
                sorter = new GnomeSort();
                break;
            case "heap":
            case "hp":
                sorter = new HeapSort();
                break;
            case "insertion":
            case "ins":
                sorter = new InsertionSort();
                break;
            case "mergebu":
            case "mergebottomup":
            case "mbu":
                sorter = new MergeSortBU();
                break;
            case "mergetd":
            case "mergetopdown":
            case "mtd":
                sorter = new MergeSortTD();
                break;
            case "oddeven":
            case "odd-even":
            case "oev":
            case "brick":
            case "brk":
                sorter = new OddEvenSort();
                break;
            case "pancake":
            case "pnc":
                sorter = new PancakeSort();
                break;
            case "pigeonhole":
            case "pigeon":
            case "pnh":
            case "pgn":
                sorter = new PigeonholeSort();
                break;
            case "quick":
            case "qck":
                sorter = new QuickSort();
                break;
            case "radix":
            case "rdx":
            case "radix2":
            case "rdx2":
            case "radixlsd":
            case "rdxlsd":
            case "radixlsd2":
            case "rdxlsd2":
                sorter = new RadixSortLSD2();
                break;
            case "radix4":
            case "rdx4":
            case "radixlsd4":
            case "rdxlsd4":
                sorter = new RadixSortLSD4();
                break;
            case "radix10":
            case "radixlsd10":
            case "rdx10":
            case "rdxlsd10":
                sorter = new RadixSortLSD10();
                break;
            case "selection":
            case "slc":
                sorter = new SelectionSort();
                break;
            case "shell":
            case "shl":
            case "shelloriginal":
            case "shellorg":
            case "shlo":
                sorter = new ShellSortOrg();
                break;
            case "shellciura":
            case "shlc":
                sorter = new ShellSortCiura();
                break;
            case "shelltokuda":
            case "shlt":
                sorter = new ShellSortTokuda();
                break;
            case "slow":
            case "slw":
                sorter = new SlowSort();
                break;
            case "stooge":
            case "stg":
                sorter = new StoogeSort();
                break;
            default:
                sorter = new NoSort();
        }

        return sorter;
    }
}
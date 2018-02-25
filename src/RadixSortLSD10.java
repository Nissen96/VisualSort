public class RadixSortLSD10 extends RadixSortLSD {
    RadixSortLSD10() {
        super("Radix Sort LSD Base 10", 1);
    }

    RadixSortLSD10(int delay) {
        super("Radix Sort LSD Base 10", delay);
    }

    public void doSort(int[] A) {
        setBase(10);
        super.doSort(A);
    }
}
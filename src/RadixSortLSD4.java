public class RadixSortLSD4 extends RadixSortLSD {
    RadixSortLSD4() {
        super("Radix Sort LSD Base 4", 1);
    }

    RadixSortLSD4(int delay) {
        super("Radix Sort LSD Base 4", delay);
    }

    public void doSort(int[] A) {
        setBase(4);
        super.doSort(A);
    }
}
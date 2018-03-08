public class RadixSortLSD10 extends RadixSortLSD {
    RadixSortLSD10() {
        super("Radix Sort LSD Base 10");
    }

    public void doSort(int[] A) {
        setBase(10);
        super.doSort(A);
    }
}
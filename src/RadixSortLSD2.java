public class RadixSortLSD2 extends RadixSortLSD {
    RadixSortLSD2() {
        super("Radix Sort LSD Base 2");
    }

    public void doSort(int[] A) {
        setBase(2);
        super.doSort(A);
    }
}

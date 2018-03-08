public class RadixSortMSD10 extends RadixSortMSD {
    RadixSortMSD10() {
        super("Radix Sort MSD Base 10");
    }

    public void doSort(int[] A) {
        setBase(10);
        super.doSort(A);
    }
}
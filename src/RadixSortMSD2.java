public class RadixSortMSD2 extends RadixSortMSD {
    RadixSortMSD2() {
        super("Radix Sort MSD Base 2");
    }

    public void doSort(int[] A) {
        setBase(2);
        super.doSort(A);
    }
}

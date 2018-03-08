public class BitonicSort extends VisualSort {
    BitonicSort() {
        super("Bitonic Sort");
    }

    public void doSort(int[] A) {
        bitonicSort(A, 0, A.length, true);
    }

    private void bitonicSort(int[] A, int lo, int n, boolean asc)
    {
        if (n>1)
        {
            int m=n/2;
            bitonicSort(A, lo, m, !asc);
            bitonicSort(A, lo+m, n-m, asc);
            bitonicMerge(A, lo, n, asc);
        }
    }

    private void bitonicMerge(int[] A, int lo, int n, boolean asc)
    {
        if (n>1)
        {
            int m=greatestPowerOfTwoLessThan(n);
            for (int i=lo; i<lo+n-m; i++)
                compare(A, i, i+m, asc);
            bitonicMerge(A, lo, m, asc);
            bitonicMerge(A, lo+m, n-m, asc);
        }
    }

    private void compare(int[] A, int i, int j, boolean asc)
    {
        if (asc == (A[i]>A[j])) {
            swap(A, i, j);
            visualize(A);
        }
    }

    // n>=2  and  n<=Integer.MAX_VALUE
    private int greatestPowerOfTwoLessThan(int n)
    {
        int k=1;
        while (k>0 && k<n)
            k=k<<1;
        return k>>>1;
    }
}

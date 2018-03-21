public class SmoothSort extends VisualSort {
    SmoothSort() {
        super("Smooth Sort");
    }

    // List of Leonardo Numbers to avoid recomputing each time
    private static final int LEONARDO_NUMBERS[] = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167,
        8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621,
        1028457, 1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703,
        48315633, 78176337, 126491971, 204668309, 331160281, 535828591, 866988873
    };

    /**
     * Sort array with the Smooth Sort algorithm
     * //TODO Explanation
     * @param A - The array to sort
     */
    public void doSort(int[] A) {
        int n = A.length;
        int orders[] = new int[(int)(Math.log(n) / Math.log(2)) * 2];
        int trees = 0;

        for (int i = 0; i < n; i++) {
            if (trees > 1 && orders[trees-2] == orders[trees-1] + 1) {
                trees--;
                orders[trees-1]++;
            }
            else if (trees > 0 && orders[trees-1] == 1) {
                orders[trees++] = 0;
            }
            else {
                orders[trees++] = 1;
            }
            trinkle(A, i, trees-1, orders);
        }

        for (int i = n - 1; i > 0; i--) {
            if (orders[trees-1] <= 1) {
                trees--;
            }
            else {
                int right = i - 1;
                int left = right - LEONARDO_NUMBERS[orders[trees-1] - 2];

                trees++;
                orders[trees-2]--;
                orders[trees-1] = orders[trees-2]-1;

                trinkle(A, left, trees-2, orders);
                trinkle(A, right, trees-1, orders);
            }
        }

    }

    /**
     *
     * @param A
     * @param i
     * @param tree
     * @param orders
     */
    private void trinkle(int[] A, int i, int tree, int[] orders) {
        int val = A[i];
        while (tree > 0) {
            int pi = i - LEONARDO_NUMBERS[orders[tree]];
            if (A[pi] <= val) {
                break;
            }
            else if (orders[tree] > 1) {
                int ri = i-1;
                int li = ri - LEONARDO_NUMBERS[orders[tree]-2];
                if (A[pi] <= A[li] || A[pi] <= A[ri]) {
                    break;
                }
            }

            A[i] = A[pi];
            visualize(A);
            i = pi;
            tree--;
        }
        A[i] = val;
        visualize(A);

        sift(A, i, orders[tree]);
    }

    /**
     *
     * @param A
     * @param i
     * @param order
     */
    private void sift(int[] A, int i, int order) {
        int val = A[i];
        while (order > 1) {
            int right = i - 1;
            int left = right - LEONARDO_NUMBERS[order-2];
            if (val >= A[left] && val >= A[right]) {
                break;
            }
            else if (A[left] <= A[right]) {
                A[i] = A[right];
                i = right;
                order -= 2;
            }
            else {
                A[i] = A[left];
                i = left;
                order -= 1;
            }
            visualize(A);
        }
        A[i] = val;
        visualize(A);
    }
}

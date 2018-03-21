import java.util.concurrent.ThreadLocalRandom;

public class QuickSort3Way extends VisualSort {
    QuickSort3Way() {
        super("3-Way Quick Sort");
    }

    public void doSort(int[] A) {
        quickSort3Way(A, 0, A.length - 1);
    }

    // 3-way partition based quick sort
    private void quickSort3Way(int[] A, int low, int high)
    {
        if (low >= high) //1 or 0 elements
            return;

        int[] result = partition3Way(A, low, high);

        // Recur two halves
        quickSort3Way(A, low, result[0]);
        quickSort3Way(A, result[1], high);
    }

    private int[] partition3Way(int[] A, int low, int high)
    {
        // To handle 2 elements
        if (high - low <= 1)
        {
            if (A[high] < A[low]) {
                Util.swap(A, high, low);
                visualize(A);
            }
            return new int[]{low, high};
        }

        int mid = low;

        int randIdx = ThreadLocalRandom.current().nextInt(low, high + 1);
        int pivot = A[randIdx];
        //int pivot = A[high];

        while (mid <= high)
        {
            if (A[mid] < pivot) {
                Util.swap(A, low++, mid++);
                visualize(A);
            } else if (A[mid]==pivot)
                mid++;
            else if (A[mid]>pivot) {
                Util.swap(A, mid, high--);
                visualize(A);
            }
        }

        return new int[]{low - 1, mid};
    }
}

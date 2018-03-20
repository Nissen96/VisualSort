import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class SleepSort extends VisualSort {
    SleepSort() {
        super("Sleep Sort");
    }

    /**
     * Helper function to start sorting
     * @param A - Array to sort
     */
    public void doSort(int[] A) {
        // Call Sleep Sort with a sleep multiplier of 1 ms
        sleepSort(A, 1);
    }

    /**
     * Sort array with the Sleep Sort algorithm
     * Create a thread for each element in the input array
     * Sleep for an amount of time proportional to the value of corresponding array element
     * The thread with the least amount of sleeping (the smallest element) "wakes up" first
     *   and is added as the first element
     * Recurse with larger sleeping durations until sorted
     * @param A - Array to sort
     * @param delay - Multiplier for sleep length
     */
    private void sleepSort(int[] A, int delay) {
        // Keep track of when to start sleeping
        final CountDownLatch doneSignal = new CountDownLatch(A.length);

        // Count down to know which element to place
        final CountDownLatch curNum = new CountDownLatch(A.length);

        // Iterate over each element in A
        for (final int num : A) {
            // Create a new thread for each element
            new Thread(() -> {
                // Count down towards the ready signal
                doneSignal.countDown();
                try {
                    // Wait for all threads to have been created
                    doneSignal.await();

                    // Start sleeping at the same time
                    Thread.sleep(num * delay);

                    // When waking up, find out what index we're currently at and add the number
                    synchronized (A) {
                        A[A.length - (int) curNum.getCount()] = num;

                        // Count down to next index for next thread
                        curNum.countDown();
                    }
                    visualize(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Wait for all threads to finish
        try {
            curNum.await();
        } catch (InterruptedException e) {}

        // If list is still not sorted, run again with a higher duration
        // This is slower but will have fewer errors
        if (!Util.isSorted(A)) {
            sleepSort(A, delay * 2);
        }
    }
}

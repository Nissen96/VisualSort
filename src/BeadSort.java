import java.util.Arrays;

public class BeadSort extends VisualSort {
    BeadSort() {
        super("Bead Sort", 1);
    }

    BeadSort(int delay) {
        super("Bead Sort", delay);
    }

    public void doSort(int[] A) {
        int len = A.length;
        int max = getMax(A);
        int i, j;

        // Set up abacus with 1s at each bead position and 0 at empty positions
        int[][] beads = new int[len][max];
        for (i = 0; i < len; i++) {
            for (j = 0; j < A[i]; j++) {
                beads[i][j] = 1;
            }
        }

        // Count how many beads are on each post
        int colSum;
        for (j = 0; j < max; j++) {
            colSum = 0;
            for (i = 0; i < len; i++) {
                colSum += beads[i][j];
                beads[i][j] = 0;
            }

            // Mark the colSum number of bottom beads
            for (i = len - colSum; i < len; i++) {
                beads[i][j] = 1;
            }
        }

        // Sort into B first to better visualize in A afterwards
        int[] B = new int[A.length];

        // Count the number of beads in each row and insert into B
        // B will then be the sorted version of A
        for (i = 0; i < len; i++) {
            j = 0;
            while (j < max && beads[i][j] == 1)
                j++;
            B[i] = j;
        }

        // Visualize the bars moving to their correct height
        while (!Arrays.equals(A, B)) {
            for (i = 0; i < len; i++) {
                if (A[i] < B[i]) {
                    A[i]++;
                } else if (A[i] > B[i]) {
                    A[i]--;
                } else {
                    continue;
                }
                visualize(A);
            }
        }
    }
}

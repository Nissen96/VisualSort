import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class StrandSort extends VisualSort {
    StrandSort() {
        super("Strand Sort");
    }

    public void doSort(int[] A) {
        LinkedList<Integer> ll = new LinkedList<>();
        for (int val : A) ll.add(val);
        strandSort(A, ll);
    }

    private void strandSort(int[] A, LinkedList<Integer> list){
        if(list.isEmpty())
            return;

        LinkedList<Integer> result = new LinkedList<>();
        while (list.size() > 0){
            LinkedList<Integer> sorted = new LinkedList<>();
            sorted.add(list.removeFirst());
            for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                int val = it.next();
                if (sorted.peekLast() < val){
                    sorted.addLast(val);
                    it.remove();
                }
            }
            result = merge(sorted, result);
            for (int i = 0; i < result.size(); i++) {
                A[i] = result.get(i);
                visualize(A);
            }
        }
        for (Integer i : result) System.out.println(i);
    }

    private static LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right){
        LinkedList<Integer> result = new LinkedList<>();
        while(!left.isEmpty() && !right.isEmpty()){
            // change the direction of this comparison to change the direction of the sort
            if(left.peek().compareTo(right.peek()) <= 0)
                result.add(left.remove());
            else
                result.add(right.remove());
        }

        result.addAll(left);
        result.addAll(right);
        return result;
    }

}

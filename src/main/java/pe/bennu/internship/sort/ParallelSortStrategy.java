package pe.bennu.internship.sort;

import java.util.Arrays;

public class ParallelSortStrategy implements SortStrategy {
    @Override
    public void sort(double[] numArray) {
        if(numArray == null || numArray.length == 0) return;
        Arrays.parallelSort(numArray);
    }
}

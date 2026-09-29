package pe.bennu.internship.sort;

import java.util.Arrays;

public class JavaSortStrategy implements SortStrategy {
    @Override
    public void sort(double[] numArray) {
        if(numArray == null || numArray.length < 2) return;
        Arrays.sort(numArray);
    }
}

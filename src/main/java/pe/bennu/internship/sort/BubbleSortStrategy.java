package pe.bennu.internship.sort;

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(double[] numArray) {
        if(numArray == null || numArray.length < 2) return;

        int i, j;
        boolean hasSwapped;
        
        for(i = numArray.length - 1; i > 0; i--) {
            hasSwapped = false;
            for(j = 0; j < i; j++) {
                if(numArray[j] > numArray[j+1]) {
                    SortUtils.swap(numArray, j, j+1);
                    hasSwapped = true;
                }
            }

            // si en un recorrido no ha habido swaps, significa
            // que el arreglo ya está ordenado
            if(!hasSwapped) break;
        }
    }
}

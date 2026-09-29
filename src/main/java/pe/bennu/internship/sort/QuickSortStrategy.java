package pe.bennu.internship.sort;

public class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(double[] numArray) {
        if(numArray == null || numArray.length < 2) return;
        quicksort(numArray, 0, numArray.length - 1);
    }

    private static void quicksort(double[] arr, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(arr, low, high);

            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                SortUtils.swap(arr, i, j);
            }
        }

        SortUtils.swap(arr, i + 1, high);
        return i + 1;
    }
}

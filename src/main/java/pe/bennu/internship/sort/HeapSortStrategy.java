package pe.bennu.internship.sort;

public class HeapSortStrategy implements SortStrategy {
    @Override
    public void sort(double[] numArray) {
        if(numArray == null || numArray.length < 2) return;
        heapsort(numArray);
    }

    private static void heapsort(double[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }


        for (int i = n - 1; i > 0; i--) {
            SortUtils.swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(double[] arr, int n, int i) {
        int largest = i;

        // left index = 2*i + 1
        int l = 2 * i + 1;

        // right index = 2*i + 2
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;
        
        if (largest != i) {
            SortUtils.swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}

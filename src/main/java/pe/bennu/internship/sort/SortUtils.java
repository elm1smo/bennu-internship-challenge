package pe.bennu.internship.sort;

public class SortUtils {
    // no instanciable
    private SortUtils() {}

    public static void swap(double[] arr, int pos1, int pos2) {
        double tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }
}

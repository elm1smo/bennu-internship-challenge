package pe.bennu.internship.search;

public class BinarySearchStrategy implements SearchStrategy {
    @Override
    public int search(double[] numArray, double numToSearch) {
        if(numArray == null || numArray.length == 0) return -1;

        int low = 0, high = numArray.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Se chequea si el buscado está en el medio
            if (numArray[mid] == numToSearch) {
                return mid;
            }

            // Sino, si el buscado es mayor al medio..
            if (numArray[mid] < numToSearch) {
                // se busca a partir del medio+1
                low = mid + 1;
            }
            else {
                // caso contrario, se busca hasta el medio-1
                high = mid - 1;
            }
        }

        return -1;
    }
}

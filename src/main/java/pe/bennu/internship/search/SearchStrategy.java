package pe.bennu.internship.search;

public interface SearchStrategy {
    /**
     * Busca el número en el arreglo indicado.
     * 
     * @param array
     * @param numToSearch
     * @return Si el elemento existe en el arreglo, devuelve su índice; caso contrario, devuelve -1.
     */
    public int search(double[] array, double numToSearch);
}

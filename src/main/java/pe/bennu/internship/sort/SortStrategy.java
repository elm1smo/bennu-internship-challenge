package pe.bennu.internship.sort;

public interface SortStrategy {
    /**
     * Ordena el arreglo de números enviado de menor a mayor (él mismo).
     * 
     * @param numArray Arreglo a ordenar
     */
    public void sort(double[] numArray);
}

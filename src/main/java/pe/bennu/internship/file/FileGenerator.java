package pe.bennu.internship.file;

import java.nio.file.Path;

public class FileGenerator {
    // no instanciable
    private FileGenerator() {}


    /**
     * Devuelve un arreglo de numeros reales de máximo dos decimales de tamaño 'size'
     * en el rango abierto ]-size, size[ 
     * 
     * @param size Tamaño del arreglo
     * @return Arreglo aleatorio
     */
    public static double[] getRandomArray(int size) {
        // TODO
        return new double[0];
    }

    /**
     * Crea un archivo a partir de un arreglo de números. Si
     * había un archivo en la misma ruta, se sobreescribe.
     * 
     * @param path Ruta del archivo por crear
     * @param sortedArray Arreglo ordenado de los numeros ordenados previamente
     */
    public static void writeFile(Path path, double[] numArray) {
        // TODO
    }
}

package pe.bennu.internship.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
        double[] numArray = new double[size];

        for(int i = 0; i < size; i++) {
            double number = (Math.random() * size * 2.0) - size; // ]-size, size[
            double result = Math.round(number * 100.0) / 100.0; // hace que sea dos decimales
            numArray[i] = result;
        }

        return numArray;
    }

    /**
     * Crea un archivo a partir de un arreglo de números. Si
     * había un archivo en la misma ruta, se sobreescribe.
     * 
     * @param path Ruta del archivo por crear
     * @param sortedArray Arreglo ordenado de los numeros ordenados previamente
     */
    public static void writeFile(Path path, double[] numArray) throws FileOperationException {
        try(BufferedWriter writer = Files.newBufferedWriter(path, 
            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            
            for (double num : numArray) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }

        } catch(IOException ioException) {
            throw new FileOperationException("No se pudo escribir en la ruta " + path, ioException);
        }
    }
}

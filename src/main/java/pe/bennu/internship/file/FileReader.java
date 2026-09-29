package pe.bennu.internship.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    // no instanciable
    private FileReader() {}

    /**
     * Lee el archivo especificado y devuelve el arreglo de numeros leído.
     * 
     * @param path Ruta del archivo por leer.
     */
    public static double[] read(Path path, long fileSize) throws FileOperationException {
        double[] numArray = new double[0];

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int i = 0;
            numArray = new double[(int) fileSize];

            while ((line = reader.readLine()) != null) {
                numArray[i++] = Double.parseDouble(line);
            }
        } catch(IOException ioException) {
            throw new FileOperationException("No se pudo leer en la ruta " + path, ioException);
        }

        return numArray;
    }
}

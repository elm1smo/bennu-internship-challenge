package pe.bennu.internship.file;

// Para pasar IOException a una excepción unchecked (permite usar Runnable y Supplier sin
// problema)
public class FileOperationException extends RuntimeException {
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

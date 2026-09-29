package pe.bennu.internship;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.function.Supplier;

import pe.bennu.internship.file.FileGenerator;
import pe.bennu.internship.file.FileReader;
import pe.bennu.internship.search.BinarySearchStrategy;
import pe.bennu.internship.search.LinearSearchStrategy;
import pe.bennu.internship.search.SearchStrategy;
import pe.bennu.internship.sort.BubbleSortStrategy;
import pe.bennu.internship.sort.HeapSortStrategy;
import pe.bennu.internship.sort.JavaSortStrategy;
import pe.bennu.internship.sort.ParallelSortStrategy;
import pe.bennu.internship.sort.QuickSortStrategy;
import pe.bennu.internship.sort.SortStrategy;
import pe.bennu.internship.state.AppState;

public class App {
    private static final Path ORIGIN_PATH = Path.of("data/origin.txt");
    private static final Path SORTED_PATH= Path.of("data/sorted.txt");

    public static void main(String[] args) {
        boolean exitProgram = false;
        Scanner scanner = new Scanner(System.in);
        AppState state = new AppState();

        showMenu();

        while(true) {
            System.out.print("Seleccione una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch(option) {
                    case 0: // MENU
                        showMenu();
                        break;
                    case 1: { // NEW FILE
                        System.out.print("¿Cuantos numeros quiere generar?: ");
                        
                        int size = Integer.parseInt(scanner.nextLine());

                        if(size < 1) {
                            System.out.println("Debe ser de 1 a más números.");
                            break;
                        }

                        System.out.println("Generando nuevo archivo...");

                        double[] numArray = measure(
                            "Generación de números",
                            () -> FileGenerator.getRandomArray(size)
                        );

                        measure(
                            "Escritura de archivo",
                            () -> FileGenerator.writeFile(ORIGIN_PATH, numArray)
                        );
                        
                        System.out.println("Nuevo archivo generado.");
                        
                        state.markGenerated();
                        break;
                    }
                    case 2: { // READ FILE
                        if(!state.canRead()) {
                            System.out.println("Primero debes generar un archivo nuevo.");
                            break;
                        }

                        System.out.println("Leyendo archivo...");

                        double[] numArray = measure(
                            "Lectura de archivo", 
                            () -> FileReader.read(ORIGIN_PATH)
                        );
                        
                        printNumbers(numArray);

                        break;
                    }
                    case 3: { // SORT FILE
                        if(!state.canRead()) {
                            System.out.println("Primero debes generar un archivo nuevo.");
                            break;
                        }

                        System.out.print(
                            "¿Que metodo de ordenamiento quiere utilizar?:\n" +
                            "1 - Java Sort\n" + 
                            "2 - Java ParallelSort\n" + 
                            "3 - Java QuickSort\n" + 
                            "4 - Java HeapSort\n" + 
                            "5 - Java BubbleSort\n" +
                            "Seleccione: "
                        );

                        double[] numArray = FileReader.read(ORIGIN_PATH);

                        int sortMethod = Integer.parseInt(scanner.nextLine());
                        SortStrategy strategy;
                        String methodName;

                        switch(sortMethod) {
                            case 1:
                                strategy = new JavaSortStrategy();
                                methodName = "Java Sort";
                                break;
                            case 2:
                                strategy = new ParallelSortStrategy();
                                methodName = "Parallel Sort";
                                break;
                            case 3:
                                strategy = new QuickSortStrategy();
                                methodName = "QuickSort";
                                break;
                            case 4:
                                strategy = new HeapSortStrategy();
                                methodName = "HeapSort";
                                break;
                            case 5:
                                strategy = new BubbleSortStrategy();
                                methodName = "BubbleSort";
                                break;
                            default:
                                throw new Exception("Metodo invalido");
                        }

                        System.out.println("Ordenando archivo...");

                        measure(
                            methodName,
                            () -> strategy.sort(numArray)
                        );

                        measure(
                            "Escritura de archivo ordenado",
                            () -> FileGenerator.writeFile(SORTED_PATH, numArray)
                        );

                        state.markSorted();
                        break;
                    }
                    case 4: { // READ SORTED FILE
                        if(!state.canRead()) {
                            System.out.println("Primero debes generar un nuevo archivo y ordenarlo.");
                            break;
                        }

                        if (!state.canReadSorted()) {
                            System.out.println("Primero debes ordenar el archivo.");
                            break;
                        }

                        System.out.println("Leyendo archivo ordenado...");

                        double[] sortedArray = measure(
                            "Lectura de archivo ordenado",
                            () -> FileReader.read(SORTED_PATH)
                        );
                        printNumbers(sortedArray);

                        break;
                    }
                    case 5: { // SEARCH IN FILE
                        if(!state.canRead()) {
                            System.out.println("Primero debes generar un nuevo archivo.");
                            break;
                        }

                        System.out.print("¿Que numero esta buscando?: ");
                        double numToSearch = Double.parseDouble(scanner.nextLine());

                        double[] numsArray;
                        SearchStrategy strategy;

                        if(state.canSearchBinary()) {
                            System.out.println("Archivo ordenado encontrado. Se usara BinarySearch.");
                            numsArray = FileReader.read(SORTED_PATH);
                            strategy = new BinarySearchStrategy();
                        }
                        else {
                            numsArray = FileReader.read(ORIGIN_PATH);
                            strategy = new LinearSearchStrategy();
                        }

                        System.out.println("Buscando el numero...");

                        int result = measure(
                            "Busqueda de numero",
                            () -> strategy.search(numsArray, numToSearch)
                        );

                        if(result < 0) {
                            System.out.println("Numero no encontrado");
                        } else {
                            System.out.println("Numero encontrado en la posicion: " + result);
                        }
                        
                        break;
                    }
                    case 6: // EXIT
                        System.out.println("Fin");
                        // opcional (creo): borrar los archivos al final

                        exitProgram = true;

                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } catch(NumberFormatException numException) {
                System.out.println("Error: Ingrese un número");
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            if(exitProgram) break;
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println(
                "Opciones\n" +
                "-------------------------\n" +
                "0 - Menu\n" +
                "1 - Genera nuevo archivo\n" +
                "2 - Lee archivo generado\n" +
                "3 - Ordena archivo\n" +
                "4 - Lee archivo ordenado\n" +
                "5 - Buscar numero en archivo\n" +
                "6 - Salir"
            );
    }

    private static void printNumbers(double[] numArray) {
        if(numArray == null || numArray.length == 0) {
            System.out.println("Sin numeros por imprimir");
            return;
        }

        // Para evitar redimensionar el tamaño, se estima en base al
        // tamaño real del arreglo
        StringBuilder result = new StringBuilder(numArray.length * 10);

        for (Double num : numArray) {
            result.append(num).append("\n");
        }

        System.out.print(result);
    }

    private static <T> T measure(String message, Supplier<T> operation) {
        long start = System.nanoTime();

        T result = operation.get();

        long elapsed = System.nanoTime() - start;

        System.out.printf(
            "%s: %.3f ms%n",
            message,
            elapsed / 1_000_000.0
        );

        return result;
    }

    private static void measure(String message, Runnable operation) {
        long start = System.nanoTime();

        operation.run();

        long elapsed = System.nanoTime() - start;

        System.out.printf(
            "%s: %.3f ms%n",
            message,
            elapsed / 1_000_000.0
        );
    }
}
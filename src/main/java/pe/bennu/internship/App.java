package pe.bennu.internship;

import java.util.Scanner;

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
    private static final String originPath = "data/origin.txt";
    private static final String sortedPath = "data/sorted.txt";

    public static void main(String[] args) {
        boolean exitProgram = false;
        Scanner scanner = new Scanner(System.in);
        AppState state = new AppState();

        showMenu();

        while(true) {
            System.out.print("Seleccione una opcion : ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch(option) {
                    case 0: // MENU
                        showMenu();
                        break;
                    case 1: { // NEW FILE
                        System.out.print("¿Cuantos numeros quiere generar?: ");
                        
                        int size = Integer.parseInt(scanner.nextLine());

                        System.out.println("Generando nuevo archivo...");

                        FileGenerator.createRandomFile(originPath, size);
                        
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

                        double[] numArray = FileReader.read(originPath);
                        printNumbers(numArray);

                        break;
                    }
                    case 3: {// SORT FILE
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

                        double[] numArray = FileReader.read(originPath);

                        int sortMethod = Integer.parseInt(scanner.nextLine());
                        SortStrategy strategy = null;

                        switch(sortMethod) {
                            case 1:
                                strategy = new JavaSortStrategy(); break;
                            case 2:
                                strategy = new ParallelSortStrategy(); break;
                            case 3:
                                strategy = new QuickSortStrategy(); break;
                            case 4:
                                strategy = new HeapSortStrategy(); break;
                            case 5:
                                strategy = new BubbleSortStrategy(); break;
                            default:
                                throw new Exception("Metodo invalido");
                        }

                        System.out.println("Ordenando archivo...");

                        double[] sortedArray = strategy.sort(numArray);

                        FileGenerator.createSortedFile(sortedPath, sortedArray);

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

                        double[] sortedArray = FileReader.read(sortedPath);
                        printNumbers(sortedArray);

                        break;
                    }
                    case 5: { // SEARCH IN FILE
                        if(!state.canRead()) {
                            System.out.println("Primero debes generar un nuevo archivo.");
                        }

                        System.out.println("¿Que numero esta buscando?: ");
                        double numToSearch = Double.parseDouble(scanner.nextLine());

                        double[] numsArray = null;
                        SearchStrategy strategy = null;

                        if(state.canSearchBinary()) {
                            System.out.println("Archivo ordenado encontrado. Se usara BinarySearch.");
                            numsArray = FileReader.read(sortedPath);
                            strategy = new BinarySearchStrategy();    
                        }
                        else {
                            numsArray = FileReader.read(originPath);
                            strategy = new LinearSearchStrategy();
                        }

                        System.out.println("Buscando el numero...");

                        int result = strategy.search(numsArray, numToSearch);

                        if(result < 0) {
                            System.out.println("Numero no encontrado");
                        } else {
                            System.out.println("Numero encontrado en la posicion: " + result);
                        }
                        
                        break;
                    }
                    case 6: // EXIT
                        System.out.println("Fin");

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
        String result = numArray.length > 0 ? "" : "\n";

        for (Double num : numArray) {
            result += (num.toString() + "\n");
        }

        System.out.print(result);
    }
}

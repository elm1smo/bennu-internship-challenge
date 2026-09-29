# Bennu Internship Challenge

Programa de consola en Java que genera números aleatorios, los ordena usando distintos algoritmos, permite buscarlos y muestra el contenido de los archivos generados. Desarrollado como parte del proceso de selección de pŕacticas de [bennu](https://bennu.cl).

## Funcionalidades

- Generar números aleatorios y guardarlos en un archivo.
- Leer y mostrar el archivo generado.
- Ordenar los números usando uno de varios algoritmos disponibles, guardando el resultado en un archivo nuevo.
- Leer y mostrar el archivo ordenado.
- Buscar un número dentro del archivo (lineal o binaria, según corresponda).

## Decisiones de diseño

### Patrón Strategy para ordenamiento y búsqueda

Cada algoritmo de ordenamiento y búsqueda se implementa como una clase que cumple una interfaz común (`SortStrategy`, `SearchStrategy`). Esto permite:

- Agregar nuevos algoritmos sin modificar el código existente.
- Seleccionar el algoritmo en tiempo de ejecución mediante polimorfismo simple (en este caso, se obvia la capa `Context`/`Service`, ya que no aporta beneficio real para este alcance).

**Algoritmos de ordenamiento implementados:**

1. Java Sort (`Arrays.sort`)
2. Java ParallelSort (`Arrays.parallelSort`)
3. QuickSort (implementación propia)
4. HeapSort (implementación propia)
5. BubbleSort (implementación propia)

**Algoritmos de búsqueda implementados:**

- Búsqueda lineal (sobre el archivo sin ordenar)
- Búsqueda binaria (sobre el archivo ordenado, automáticamente seleccionada cuando corresponde)

### Estado centralizado (`AppState`)

No todas las opciones del menú están siempre disponibles (por ejemplo, no se puede leer un archivo ordenado si aún no se ha ordenado nada). Esto se resuelve mediante la clase `AppState` que centraliza qué operaciones son válidas en cada momento, en vez de repartir flags booleanos sueltos por el código.

`AppState` también determina automáticamente qué estrategia de búsqueda usar: si existe un archivo ordenado, se usa búsqueda binaria; si no, búsqueda lineal.

### Medición de tiempos de ejecución

Se incluye un método utilitario (`measure`) que mide y reporta el tiempo de ejecución de cada operación pertinente (generación, lectura, ordenamiento, búsqueda) lo que permite comparar la eficiencia de los distintos algoritmos de ordenamiento entre sí.

### Sin gestor de dependencias (Maven, Gradle, etc)

El proyecto no tiene dependencias externas (solo biblioteca estándar de Java), por lo que se optó por compilar directamente con `javac`/`jar`, sin un gestor de builds. Se sigue la estructura estándar de proyectos Java (`src/main/java/...`).

## Estructura del proyecto

```
.
├── src/
│   └── main/
│       └── java/
│           └── pe/bennu/internship/
│               ├── App.java
│               ├── state/
│               │   └── AppState.java
│               ├── file/
│               │   ├── FileGenerator.java
│               │   ├── FileReader.java
│               │   └── FileOperationException.java
│               ├── sort/
│               │   ├── SortStrategy.java
│               │   ├── SortUtils.java
│               │   ├── JavaSortStrategy.java
│               │   ├── ParallelSortStrategy.java
│               │   ├── QuickSortStrategy.java
│               │   ├── HeapSortStrategy.java
│               │   └── BubbleSortStrategy.java
│               └── search/
│                   ├── SearchStrategy.java
│                   ├── LinearSearchStrategy.java
│                   └── BinarySearchStrategy.java
├── Dockerfile
├── build.sh
├── .gitignore
└── README.md
```

## Requisitos

- Java 17 (JDK) si se desea compilar/ejecutar localmente.
- Docker, si se desea ejecutar en contenedor (recomendado, pues no requiere tener Java instalado).

## Cómo ejecutar

### Opción 1: Docker

```bash
docker container run -it --name internship elm1smo/internship
```

Esto descarga la imagen publicada en Docker Hub ([elm1smo/internship](https://hub.docker.com/r/elm1smo/internship)) y ejecuta el programa directamente, sin necesidad de tener Java instalado.

### Opción 2: Compilar y ejecutar localmente

Desde la raíz del proyecto:

```bash
./build.sh
java -jar app.jar
```

## Ejemplo de uso

```
Opciones
-------------------------
0 - Menu
1 - Genera nuevo archivo
2 - Lee archivo generado
3 - Ordena archivo
4 - Lee archivo ordenado
5 - Buscar numero en archivo
6 - Salir
Seleccione una opción : 1
Generando nuevo archivo
¿Cuantos numeros quiere generar? : 10
Generate file execution time : 3,15 ms
```

## Repositorio

[https://github.com/elm1smo/bennu-internship-challenge](https://github.com/elm1smo/bennu-internship-challenge)

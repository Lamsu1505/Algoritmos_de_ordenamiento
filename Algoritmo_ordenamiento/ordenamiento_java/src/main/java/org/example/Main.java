package org.example;

import org.example.Algortimos.Busqueda.*;
import org.example.Algortimos.Ordenamiento.*;
import org.example.Algortimos.Busqueda.BusquedaBinaria;
import org.example.Algortimos.Busqueda.BusquedaPorSaltos;
import org.example.Algortimos.Busqueda.BusquedaTernaria;
import org.example.Algortimos.Ordenamiento.*;
import org.example.datos.GeneradorDatos;
import org.example.utils.UtilBusqueda;
import org.example.utils.UtilOrdenamiento;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase principal del proyecto con menú interactivo para ejecutar algoritmos de ordenamiento o búsqueda.
 */
public class Main {

    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int opcionPrincipal;

            do {
                System.out.println("\n=====================================");
                System.out.println("   TALLER DE ORDENAMIENTO Y BÚSQUEDA");
                System.out.println("=====================================");
                System.out.println("1) Algoritmos de Ordenamiento");
                System.out.println("2) Algoritmos de Búsqueda");
                System.out.println("3) Salir");
                System.out.print("Seleccione una opción: ");
                opcionPrincipal = entrada.nextInt();

                switch (opcionPrincipal) {
                    case 1 -> ejecutarOrdenamiento();
                    case 2 -> ejecutarBusqueda();
                    case 3 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }

            } while (opcionPrincipal != 3);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =============================
    // MÉTODOS DE APOYO
    // =============================

    private static int seleccionarTamano() {
        System.out.println("\nSeleccione el tamaño del arreglo:");
        System.out.println("1) 10.000 elementos");
        System.out.println("2) 100.000 elementos");
        System.out.println("3) 1.000.000 elementos");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt();
        
        return switch (opcion) {
            case 1 -> 10_000;
            case 2 -> 100_000;
            case 3 -> 1_000_000;
            default -> {
                System.out.println("Opción inválida, se usará 10.000 por defecto.");
                yield 10_000;
            }
        };
    }

    private static int[] cargarDatos(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // =============================
    // OPCIÓN 1 - ORDENAMIENTO
    // =============================
    private static void ejecutarOrdenamiento() throws IOException {
        int tam = seleccionarTamano();
        String nombreArchivo = "datos_" + tam + ".txt";

        if (!Files.exists(Paths.get(nombreArchivo))) {
            System.out.println("Generando archivo aleatorio (no ordenado)...");
            GeneradorDatos.generarArchivo(nombreArchivo, tam);
        }

        int[] datosOriginales = cargarDatos(nombreArchivo);
        String[] algoritmos = {"ShakerSort", "DualPivotQuickSort", "HeapSort", "MergeSort", "RadixSort"};
        File archivoResultados = new File("resultados_ordenamiento.csv");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoResultados, true));

        escritor.write("Algoritmo,Tamaño,Tiempo(ms)\n");
        System.out.println("\n--- PRUEBAS DE ORDENAMIENTO (" + tam + " elementos) ---");

        for (String nombre : algoritmos) {
            int[] datos = Arrays.copyOf(datosOriginales, datosOriginales.length);
            long tiempo = switch (nombre) {
                case "ShakerSort" -> UtilOrdenamiento.medirEjecucion(() -> ShakerSort.ordenar(datos));
                case "DualPivotQuickSort" -> UtilOrdenamiento.medirEjecucion(() -> DualPivotQuickSort.ordenar(datos));
                case "HeapSort" -> UtilOrdenamiento.medirEjecucion(() -> HeapSort.ordenar(datos));
                case "MergeSort" -> UtilOrdenamiento.medirEjecucion(() -> MergeSort.ordenar(datos));
                case "RadixSort" -> UtilOrdenamiento.medirEjecucion(() -> RadixSort.ordenar(datos));
                default -> 0;
            };

            System.out.printf("%-20s => %d ms%n", nombre, tiempo);
            escritor.write(nombre + "," + tam + "," + tiempo + "\n");
        }

        escritor.close();
        System.out.println("\nResultados guardados en 'resultados_ordenamiento.csv'.");
    }

    // =============================
    // OPCIÓN 2 - BÚSQUEDA
    // =============================
    private static void ejecutarBusqueda() throws IOException {
        int tam = seleccionarTamano();
        String nombreArchivo = "datos_" + tam + "_ordenado.txt";

        if (!Files.exists(Paths.get(nombreArchivo))) {
            System.out.println("Generando archivo aleatorio ordenado...");
            GeneradorDatos.generarArchivoOrdenado(nombreArchivo, tam);
        }

        int[] datos = cargarDatos(nombreArchivo);
        Random random = new Random();
        int valorBuscado = datos[random.nextInt(datos.length)];

        String[] algoritmos = {"Binaria", "Ternaria", "Saltos"};
        File archivoResultados = new File("resultados_busqueda.csv");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoResultados, true));

        escritor.write("Algoritmo,Tamaño,Tiempo(µs)\n");
        System.out.println("\n--- PRUEBAS DE BÚSQUEDA (" + tam + " elementos) ---");
        System.out.println("Buscando el valor: " + valorBuscado);

        for (String nombre : algoritmos) {
            long tiempo = switch (nombre) {
                case "Binaria" -> UtilBusqueda.medirTiempo(() -> BusquedaBinaria.buscar(datos, valorBuscado));
                case "Ternaria" -> UtilBusqueda.medirTiempo(() -> BusquedaTernaria.buscar(datos, valorBuscado));
                case "Saltos" -> UtilBusqueda.medirTiempo(() -> BusquedaPorSaltos.buscar(datos, valorBuscado));
                default -> 0;
            };

            System.out.printf("%-15s => %d µs%n", nombre, tiempo);
            escritor.write(nombre + "," + tam + "," + tiempo + "\n");
        }

        escritor.close();
        System.out.println("\nResultados guardados en 'resultados_busqueda.csv'.");
    }
}

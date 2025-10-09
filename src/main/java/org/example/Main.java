package org.example;

import org.example.datos.GeneradorDatos;
import org.example.Algortimos.*;
import org.example.utils.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static int[] cargarDatos(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {
        int[] tamanos = {10000, 100000, 1000000};
        String[] algoritmos = {"ShakerSort", "DualPivotQuickSort", "HeapSort", "MergeSort", "RadixSort"};

        File archivoResultados = new File("resultados.csv");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoResultados));
        escritor.write("Algoritmo,Tamaño,Tiempo(ms)\n");

        for (int tam : tamanos) {
            String ruta = "datos_" + tam + ".txt";

            if (!Files.exists(Paths.get(ruta))) {
                System.out.println("Generando archivo para " + tam + " elementos...");
                GeneradorDatos.generarArchivo(ruta, tam);
            }

            int[] base = cargarDatos(ruta);
            System.out.println("\n--- Pruebas con " + tam + " elementos ---");

            for (String nombre : algoritmos) {
                int[] datos = Arrays.copyOf(base, base.length);

                long tiempo = switch (nombre) {
                    case "ShakerSort" -> UtilTiempo.medirEjecucion(() -> ShakerSort.ordenar(datos));
                    case "DualPivotQuickSort" -> UtilTiempo.medirEjecucion(() -> DualPivotQuickSort.ordenar(datos));
                    case "HeapSort" -> UtilTiempo.medirEjecucion(() -> HeapSort.ordenar(datos));
                    case "MergeSort" -> UtilTiempo.medirEjecucion(() -> MergeSort.ordenar(datos));
                    case "RadixSort" -> UtilTiempo.medirEjecucion(() -> RadixSort.ordenar(datos));
                    default -> 0;
                };

                escritor.write(nombre + "," + tam + "," + tiempo + "\n");
                System.out.printf("%-20s %10d elementos => %d ms%n", nombre, tam, tiempo);
            }
        }

        escritor.close();
        System.out.println("\n✅ Resultados guardados en 'resultados.csv'");
    }
}

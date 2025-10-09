package org.example.datos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase encargada de generar archivos de texto con números aleatorios.
 */
public class GeneradorDatos {

    public static void generarArchivo(String nombreArchivo, int cantidad) throws IOException {
        Random random = new Random();
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < cantidad; i++) {
                int numero = 10000000 + random.nextInt(90000000); // 8 dígitos
                escritor.write(String.valueOf(numero));
                escritor.newLine();
            }
        }
    }
}

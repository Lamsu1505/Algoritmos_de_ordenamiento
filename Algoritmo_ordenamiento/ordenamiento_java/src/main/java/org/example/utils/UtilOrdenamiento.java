package org.example.utils;

public class UtilOrdenamiento {

    public static long medirEjecucion(Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000; // milisegundos
    }
}

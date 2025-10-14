package org.example.utils;

public class UtilBusqueda {

    public static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000; // tiempo en milisegundos
    }
}

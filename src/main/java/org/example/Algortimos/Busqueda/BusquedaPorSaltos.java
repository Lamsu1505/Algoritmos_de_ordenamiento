package org.example.Algortimos.Busqueda;


/**
 * Implementación del algoritmo Jump Search (Búsqueda por Saltos).
 */
public class BusquedaPorSaltos {

    public static int buscar(int[] arreglo, int valorBuscado) {
        int n = arreglo.length;
        int paso = (int) Math.floor(Math.sqrt(n));
        int anterior = 0;

        while (arreglo[Math.min(paso, n) - 1] < valorBuscado) {
            anterior = paso;
            paso += (int) Math.floor(Math.sqrt(n));
            if (anterior >= n) return -1;
        }

        while (anterior < Math.min(paso, n)) {
            if (arreglo[anterior] == valorBuscado) return anterior;
            anterior++;
        }

        return -1;
    }
}

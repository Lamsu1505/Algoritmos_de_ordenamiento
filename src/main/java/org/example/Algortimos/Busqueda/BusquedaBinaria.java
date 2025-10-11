package org.example.Algortimos.Busqueda;

/**
 * Implementación del algoritmo de búsqueda binaria.
 * Requiere que el arreglo esté ordenado.
 */
public class BusquedaBinaria {

    public static int buscar(int[] arreglo, int valorBuscado) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == valorBuscado) return medio;
            if (arreglo[medio] < valorBuscado) izquierda = medio + 1;
            else derecha = medio - 1;
        }
        return -1;
    }
}

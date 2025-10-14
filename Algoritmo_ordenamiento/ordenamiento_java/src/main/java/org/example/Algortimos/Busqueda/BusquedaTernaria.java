package org.example.Algortimos.Busqueda;

/**
 * Implementación del algoritmo de búsqueda ternaria.
 * Divide el arreglo en tres partes en lugar de dos.
 */
public class BusquedaTernaria {

    public static int buscar(int[] arreglo, int valorBuscado) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int tercio1 = izquierda + (derecha - izquierda) / 3;
            int tercio2 = derecha - (derecha - izquierda) / 3;

            if (arreglo[tercio1] == valorBuscado) return tercio1;
            if (arreglo[tercio2] == valorBuscado) return tercio2;

            if (valorBuscado < arreglo[tercio1]) {
                derecha = tercio1 - 1;
            } else if (valorBuscado > arreglo[tercio2]) {
                izquierda = tercio2 + 1;
            } else {
                izquierda = tercio1 + 1;
                derecha = tercio2 - 1;
            }
        }
        return -1;
    }
}


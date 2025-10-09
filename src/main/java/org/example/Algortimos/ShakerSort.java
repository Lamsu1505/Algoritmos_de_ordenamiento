package org.example.Algortimos;

/**
 * Implementación del algoritmo Shaker Sort (una variación del Bubble Sort).
 */
public class ShakerSort {

    public static void ordenar(int[] arreglo) {
        boolean intercambio = true;
        int inicio = 0;
        int fin = arreglo.length - 1;

        while (intercambio) {
            intercambio = false;

            // Recorrido de izquierda a derecha
            for (int i = inicio; i < fin; i++) {
                if (arreglo[i] > arreglo[i + 1]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[i + 1];
                    arreglo[i + 1] = temp;
                    intercambio = true;
                }
            }

            if (!intercambio) break;

            intercambio = false;
            fin--;

            // Recorrido de derecha a izquierda
            for (int i = fin - 1; i >= inicio; i--) {
                if (arreglo[i] > arreglo[i + 1]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[i + 1];
                    arreglo[i + 1] = temp;
                    intercambio = true;
                }
            }

            inicio++;
        }
    }
}


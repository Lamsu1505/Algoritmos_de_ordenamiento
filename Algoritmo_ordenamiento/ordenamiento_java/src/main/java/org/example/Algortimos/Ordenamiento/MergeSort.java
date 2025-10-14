package org.example.Algortimos.Ordenamiento;

/**
 * Implementación del algoritmo Merge Sort.
 */
public class MergeSort {

    public static void ordenar(int[] arr) {
        dividir(arr, 0, arr.length - 1);
    }

    private static void dividir(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            dividir(arr, izquierda, medio);
            dividir(arr, medio + 1, derecha);
            combinar(arr, izquierda, medio, derecha);
        }
    }

    private static void combinar(int[] arr, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[izquierda + i];
        for (int j = 0; j < n2; j++) R[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = izquierda;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}

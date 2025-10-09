package org.example.Algortimos;

/**
 * Implementación del algoritmo Heap Sort.
 */
public class HeapSort {

    public static void ordenar(int[] arr) {
        int n = arr.length;

        // Construir heap (reorganizar el arreglo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            hacerHeap(arr, n, i);
        }

        // Extraer elementos uno a uno del heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            hacerHeap(arr, i, 0);
        }
    }

    private static void hacerHeap(int[] arr, int n, int i) {
        int mayor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < n && arr[izquierda] > arr[mayor]) mayor = izquierda;
        if (derecha < n && arr[derecha] > arr[mayor]) mayor = derecha;

        if (mayor != i) {
            int temp = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = temp;

            hacerHeap(arr, n, mayor);
        }
    }
}


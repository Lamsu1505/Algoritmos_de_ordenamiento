package org.example.Algortimos.Ordenamiento;

/**
 * Implementación de QuickSort con dos pivotes.
 */
public class DualPivotQuickSort {

    public static void ordenar(int[] arreglo) {
        ordenarRecursivo(arreglo, 0, arreglo.length - 1);
    }

    private static void ordenarRecursivo(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int[] pivotes = particionar(arr, bajo, alto);
            ordenarRecursivo(arr, bajo, pivotes[0] - 1);
            ordenarRecursivo(arr, pivotes[0] + 1, pivotes[1] - 1);
            ordenarRecursivo(arr, pivotes[1] + 1, alto);
        }
    }

    private static int[] particionar(int[] arr, int bajo, int alto) {
        if (arr[bajo] > arr[alto]) {
            int temp = arr[bajo];
            arr[bajo] = arr[alto];
            arr[alto] = temp;
        }

        int p = arr[bajo];
        int q = arr[alto];

        int l = bajo + 1;
        int g = alto - 1;
        int k = l;

        while (k <= g) {
            if (arr[k] < p) {
                int temp = arr[k];
                arr[k] = arr[l];
                arr[l] = temp;
                l++;
            } else if (arr[k] >= q) {
                while (arr[g] > q && k < g) g--;
                int temp = arr[k];
                arr[k] = arr[g];
                arr[g] = temp;
                g--;
                if (arr[k] < p) {
                    temp = arr[k];
                    arr[k] = arr[l];
                    arr[l] = temp;
                    l++;
                }
            }
            k++;
        }
        l--;
        g++;

        int temp = arr[bajo];
        arr[bajo] = arr[l];
        arr[l] = temp;

        temp = arr[alto];
        arr[alto] = arr[g];
        arr[g] = temp;

        return new int[]{l, g};
    }
}

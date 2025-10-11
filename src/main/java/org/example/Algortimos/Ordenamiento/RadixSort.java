package org.example.Algortimos.Ordenamiento;

/**
 * Implementación del algoritmo Radix Sort (ordenamiento por dígitos).
 */
public class RadixSort {

    public static void ordenar(int[] arr) {
        int max = obtenerMaximo(arr);
        for (int exp = 1; max / exp > 0; exp *= 10)
            contarOrdenar(arr, exp);
    }

    private static int obtenerMaximo(int[] arr) {
        int max = arr[0];
        for (int num : arr)
            if (num > max)
                max = num;
        return max;
    }

    private static void contarOrdenar(int[] arr, int exp) {
        int n = arr.length;
        int[] salida = new int[n];
        int[] conteo = new int[10];

        for (int num : arr)
            conteo[(num / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            conteo[i] += conteo[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int indice = (arr[i] / exp) % 10;
            salida[conteo[indice] - 1] = arr[i];
            conteo[indice]--;
        }

        System.arraycopy(salida, 0, arr, 0, n);
    }
}

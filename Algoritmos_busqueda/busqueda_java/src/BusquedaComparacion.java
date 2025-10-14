import java.io.*;
import java.util.*;
import java.nio.file.*;
import com.google.gson.*;
import java.time.Duration;
import java.time.Instant;

public class BusquedaComparacion {

    // === Algoritmos de búsqueda ===
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int ternarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (right >= left) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;
            if (arr[mid1] == target) return mid1;
            if (arr[mid2] == target) return mid2;
            if (target < arr[mid1]) right = mid1 - 1;
            else if (target > arr[mid2]) left = mid2 + 1;
            else { left = mid1 + 1; right = mid2 - 1; }
        }
        return -1;
    }

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return -1;
        }
        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) return -1;
        }
        if (arr[prev] == target) return prev;
        return -1;
    }

    // === Generar o leer datos ===
    public static int[] generarDatos(int n, String archivo) throws IOException {
        File f = new File(archivo);
        if (f.exists()) {
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            int[] arr = lineas.stream().mapToInt(Integer::parseInt).toArray();
            return Arrays.copyOfRange(arr, 0, n);
        } else {
            Random rand = new Random();
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < 1_000_000; i++) {
                int numero = 10_000_000 + rand.nextInt(90_000_000);
                bw.write(String.valueOf(numero));
                bw.newLine();
            }
            bw.close();
            return generarDatos(n, archivo);
        }
    }

    // === Medir tiempo ===
    public static long medirTiempo(int[] arr, String metodo, int target) {
        Instant inicio = Instant.now();
        switch (metodo) {
            case "Binary": binarySearch(arr, target); break;
            case "Ternary": ternarySearch(arr, target); break;
            case "Jump": jumpSearch(arr, target); break;
        }
        Instant fin = Instant.now();
        return Duration.between(inicio, fin).toNanos();
    }

    public static void main(String[] args) throws Exception {
        String archivoDatos = "datos.txt";
        String archivoCSV = "resultados_busqueda_java.csv";
        String archivoJSON = "resultados_busqueda_java.json";

        int[] tamaños = {10_000, 100_000, 1_000_000};
        String[] metodos = {"Binary", "Ternary", "Jump"};

        List<Map<String, Object>> resultados = new ArrayList<>();

        for (int n : tamaños) {
            int[] datos = generarDatos(n, archivoDatos);
            Arrays.sort(datos);
            int target = datos[new Random().nextInt(n)];

            for (String metodo : metodos) {
                long tiempo = medirTiempo(datos, metodo, target);
                System.out.printf("%s Search con %d elementos: %d ns%n", metodo, n, tiempo);

                Map<String, Object> r = new LinkedHashMap<>();
                r.put("Algoritmo", metodo);
                r.put("Tamaño", n);
                r.put("Tiempo_ns", tiempo);
                resultados.add(r);
            }
        }

        // Guardar CSV
        BufferedWriter csv = new BufferedWriter(new FileWriter(archivoCSV));
        csv.write("Algoritmo,Tamaño,Tiempo_ns\n");
        for (Map<String, Object> r : resultados) {
            csv.write(String.format("%s,%d,%d\n",
                    r.get("Algoritmo"), r.get("Tamaño"), r.get("Tiempo_ns")));
        }
        csv.close();

        // Guardar JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter json = new FileWriter(archivoJSON);
        gson.toJson(resultados, json);
        json.close();

        System.out.println("\n✅ Resultados guardados en CSV y JSON.");
    }
}

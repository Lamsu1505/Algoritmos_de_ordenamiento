import random
import time
import math
import os
import json
import csv
import matplotlib.pyplot as plt

# ==============================
# Algoritmos de búsqueda
# ==============================

# Fuente: GeeksforGeeks - Binary Search
# https://www.geeksforgeeks.org/binary-search/
def busqueda_binaria(arr, x):
    izquierda, derecha = 0, len(arr) - 1
    while izquierda <= derecha:
        medio = (izquierda + derecha) // 2
        if arr[medio] == x:
            return medio
        elif arr[medio] < x:
            izquierda = medio + 1
        else:
            derecha = medio - 1
    return -1

# Fuente: GeeksforGeeks - Ternary Search
# https://www.geeksforgeeks.org/ternary-search/
def busqueda_ternaria(arr, x):
    izquierda, derecha = 0, len(arr) - 1
    while izquierda <= derecha:
        tercio1 = izquierda + (derecha - izquierda) // 3
        tercio2 = derecha - (derecha - izquierda) // 3
        if arr[tercio1] == x:
            return tercio1
        elif arr[tercio2] == x:
            return tercio2
        elif x < arr[tercio1]:
            derecha = tercio1 - 1
        elif x > arr[tercio2]:
            izquierda = tercio2 + 1
        else:
            izquierda = tercio1 + 1
            derecha = tercio2 - 1
    return -1

# Fuente: GeeksforGeeks - Jump Search
# https://www.geeksforgeeks.org/jump-search/
def busqueda_por_saltos(arr, x):
    n = len(arr)
    salto = int(math.sqrt(n))
    prev = 0

    while prev < n and arr[min(salto, n) - 1] < x:
        prev = salto
        salto += int(math.sqrt(n))
        if prev >= n:
            return -1

    for i in range(prev, min(salto, n)):
        if arr[i] == x:
            return i
    return -1

# ==============================
# Generación y lectura de datos
# ==============================

def generar_arreglo(n, filename):
    """Genera un arreglo aleatorio de 8 dígitos y lo guarda en un archivo."""
    if not os.path.exists(filename):
        print(f"Generando archivo {filename} con {n} elementos...")
        arr = sorted(random.sample(range(10_000_000, 99_999_999), n))
        with open(filename, "w") as f:
            f.write("\n".join(map(str, arr)))
    else:
        print(f"Archivo {filename} ya existe, se reutilizarán los datos.")

def leer_arreglo(filename):
    """Lee un arreglo desde un archivo de texto."""
    with open(filename, "r") as f:
        return [int(line.strip()) for line in f]

# ==============================
# Medición de tiempos
# ==============================

def medir_tiempo(funcion, arr, x):
    inicio = time.perf_counter()
    funcion(arr, x)
    fin = time.perf_counter()
    return fin - inicio

# ==============================
# Ejecución principal
# ==============================

tamaños = [10_000, 100_000, 1_000_000]
archivos = [f"datos_{n}.txt" for n in tamaños]

# Generar los archivos si no existen
for n, archivo in zip(tamaños, archivos):
    generar_arreglo(n, archivo)

resultados = {"Binaria": [], "Ternaria": [], "Saltos": []}

for n, archivo in zip(tamaños, archivos):
    arr = leer_arreglo(archivo)
    x = random.choice(arr)  # elemento a buscar

    print(f"\n🔹 Ejecutando búsquedas en arreglo de {n} elementos...")

    t_binaria = medir_tiempo(busqueda_binaria, arr, x)
    t_ternaria = medir_tiempo(busqueda_ternaria, arr, x)
    t_saltos = medir_tiempo(busqueda_por_saltos, arr, x)

    resultados["Binaria"].append(t_binaria)
    resultados["Ternaria"].append(t_ternaria)
    resultados["Saltos"].append(t_saltos)

    print(f"   Búsqueda Binaria: {t_binaria:.6f} s")
    print(f"   Búsqueda Ternaria: {t_ternaria:.6f} s")
    print(f"   Búsqueda por Saltos: {t_saltos:.6f} s")

# ==============================
# Guardar resultados CSV y JSON
# ==============================

csv_filename = "resultados_busquedas.csv"
json_filename = "resultados_busquedas.json"

# Guardar en CSV con el nuevo formato
with open(csv_filename, "w", newline="") as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Algoritmo", "Tamaño", "Tiempo_ns", "Lenguaje"])
    for i, n in enumerate(tamaños):
        # Convertir de segundos a nanosegundos
        writer.writerow(["Binary", n, int(resultados["Binaria"][i] * 1_000_000_000), "Python"])
        writer.writerow(["Ternary", n, int(resultados["Ternaria"][i] * 1_000_000_000), "Python"])
        writer.writerow(["Jump", n, int(resultados["Saltos"][i] * 1_000_000_000), "Python"])

# Guardar en JSON
data_json = {
    "tamaños": tamaños,
    "resultados": resultados
}
with open(json_filename, "w") as jf:
    json.dump(data_json, jf, indent=4)

print(f"\n📁 Resultados guardados en: {csv_filename} y {json_filename}")

# ==============================
# Gráfico comparativo
# ==============================

etiquetas = [str(t) for t in tamaños]
x = range(len(tamaños))
ancho = 0.25

plt.figure(figsize=(10, 6))
plt.bar([i - ancho for i in x], resultados["Binaria"], width=ancho, label="Binaria")
plt.bar(x, resultados["Ternaria"], width=ancho, label="Ternaria")
plt.bar([i + ancho for i in x], resultados["Saltos"], width=ancho, label="Saltos")

for metodo, shift in zip(resultados.keys(), [-ancho, 0, ancho]):
    for i, valor in enumerate(resultados[metodo]):
        plt.text(i + shift, valor + 0.00001, f"{valor:.6f}s", ha='center', fontsize=9)

plt.xticks(x, etiquetas)
plt.ylabel("Tiempo de ejecución (segundos)")
plt.xlabel("Tamaño del arreglo")
plt.title("Comparación de tiempos de búsqueda en Python")
plt.legend()
plt.tight_layout()
plt.show()

# ==============================
# Explicación sobre desempeño
# ==============================

print("""
🧩 DIFERENCIAS ENTRE LENGUAJES COMPILADOS E INTERPRETADOS:

Los lenguajes compilados (como C++ o Go) traducen el código fuente directamente
a código máquina antes de ejecutarse, lo que permite que el procesador ejecute
las instrucciones de forma nativa y mucho más rápida.

En cambio, los lenguajes interpretados como Python dependen de un intérprete que
traduce el código línea por línea durante la ejecución, lo que introduce una
sobrecarga adicional en el rendimiento.

Por ello, se espera que las búsquedas implementadas en C++ o Go sean
significativamente más rápidas que en Python, especialmente en grandes volúmenes
de datos, aunque los algoritmos sean idénticos.
""")
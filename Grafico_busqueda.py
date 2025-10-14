import pandas as pd
import matplotlib.pyplot as plt

# 1. Leer el archivo CSV
df = pd.read_csv("resultados_busqueda_java.csv") 

# 2. Filtrar tamaño (opcional)
# Si quieres graficar todos los tamaños, comenta esta línea
df = df[df["Tamaño"] == 1000000]

# 3. Crear gráfico
plt.figure(figsize=(8, 5))
ax = plt.subplot()

# 4. Obtener listas únicas
algoritmos = df["Algoritmo"].unique()
lenguajes = ["Java", "Python"]

# 5. Posiciones y colores
x = range(len(algoritmos))
bar_width = 0.35
colors = {"Java": "red", "Python": "blue"}

# 6. Dibujar barras
for j, lang in enumerate(lenguajes):
    subset = df[df["Lenguaje"] == lang]
    ax.bar(
        [i + (j - 0.5) * bar_width for i in x],
        subset["Tiempo_ns"],
        width=bar_width,
        color=colors[lang],
        label=lang
    )

    # Etiquetas de tiempo sobre cada barra
    for i, row in subset.iterrows():
        plt.text(
            x=list(algoritmos).index(row["Algoritmo"]) + (j - 0.5) * bar_width,
            y=row["Tiempo_ns"] + (row["Tiempo_ns"] * 0.01 + 1),
            s=str(row["Tiempo_ns"]),
            ha='center',
            va='bottom',
            fontsize=8
        )

# 7. Personalización del gráfico
plt.title("Comparativa de tiempos de búsqueda por algoritmo y lenguaje", fontsize=13, weight='bold')
plt.xlabel("Algoritmo", fontsize=11)
plt.ylabel("Tiempo (ns)", fontsize=11)
plt.xticks(range(len(algoritmos)), algoritmos)
plt.legend(title="Lenguaje")
plt.grid(axis='y', linestyle='--', alpha=0.6)

plt.tight_layout()
plt.show()

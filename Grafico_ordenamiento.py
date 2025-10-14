import pandas as pd
import matplotlib.pyplot as plt

# 1. Leer el archivo CSV
df = pd.read_csv("resultados_ordenamiento_limpio.csv")

# 2. Filtrar por tamaño específico (opcional)
df = df[df["Tamaño"] == 1000000]

# 3. Crear gráfico
plt.figure(figsize=(10, 6))
ax = plt.subplot()

# 4. Obtener listas únicas
algoritmos = df["Algoritmo"].unique()
lenguajes = ["Java", "Python"]

# 5. Posición de barras
x = range(len(algoritmos))
bar_width = 0.35

# 6. Colores por lenguaje
colors = {"Java": "red", "Python": "blue"}

# 7. Crear barras para cada lenguaje
for j, lang in enumerate(lenguajes):
    subset = df[df["Lenguaje"] == lang]
    ax.bar(
        [i + (j - 0.5) * bar_width for i in x],
        subset["Tiempo(ms)"],
        width=bar_width,
        label=lang,
        color=colors[lang]
    )
    # Agregar etiquetas de tiempo sobre cada barra
    for i, row in subset.iterrows():
        plt.text(
            x=list(algoritmos).index(row["Algoritmo"]) + (j - 0.5) * bar_width,
            y=row["Tiempo(ms)"] + (row["Tiempo(ms)"] * 0.01),
            s=str(row["Tiempo(ms)"]),
            ha='center',
            va='bottom',
            fontsize=8
        )

# 8. Etiquetas y estilo
plt.title("Comparativa de tiempos por algoritmo y lenguaje", fontsize=14, weight='bold')
plt.xlabel("Algoritmo", fontsize=12)
plt.ylabel("Tiempo (ms)", fontsize=12)
plt.xticks(range(len(algoritmos)), algoritmos)
plt.legend(title="Lenguaje")
plt.grid(axis='y', linestyle='--', alpha=0.6)

plt.tight_layout()
plt.show()

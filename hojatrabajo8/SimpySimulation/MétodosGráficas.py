##Para facilitar la elaboración de gráficas se supone que en un día en común llegan aproximadamente 50 personas a la emergencia
from main import *
import simpy
import matplotlib.pyplot as plt
import pandas as pd


def run_experiments(recurso_objetivo, capacidades, num_pacientes=50, interval=10):
    resultados_globales = []
    for cap in capacidades:
        env = simpy.Environment()
        Enfermeras = simpy.Resource(env, capacity=2)
        RayosX = simpy.PriorityResource(env, capacity=2)
        Laboratorio = simpy.PriorityResource(env, capacity=5)
        Operaciones = simpy.PriorityResource(env, capacity=2)
        Doctores = simpy.PriorityResource(env, capacity=3)

        if recurso_objetivo == 'Doctores':
            Doctores = simpy.PriorityResource(env, capacity=cap)
        elif recurso_objetivo == 'Operaciones':
            Operaciones = simpy.PriorityResource(env, capacity=cap)
        elif recurso_objetivo == 'RayosX':
            RayosX = simpy.PriorityResource(env, capacity=cap)
        elif recurso_objetivo == 'Laboratorio':
            Laboratorio = simpy.PriorityResource(env, capacity=cap)
        elif recurso_objetivo == 'Enfermeras':
            Enfermeras = simpy.Resource(env, capacity=cap)

        resultados = []
        env.process(
            generate_simulation(env, Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, num_pacientes, interval,
                                resultados))
        env.run()

        for r in resultados:
            if r["recurso"] == recurso_objetivo:
                r["capacidad"] = cap
                resultados_globales.append(r)
    return resultados_globales


def graficar_resultados(resultados, recurso_objetivo):
    df = pd.DataFrame(resultados)
    df_filtrado = df[df["recurso"] == recurso_objetivo]

    plt.figure(figsize=(10, 6))
    for prioridad in sorted(df_filtrado["prioridad"].unique()):
        subset = df_filtrado[df_filtrado["prioridad"] == prioridad]
        agrupado = subset.groupby("capacidad")["waiting_time"].mean()
        plt.plot(agrupado.index, agrupado.values, label=f'Prioridad {prioridad}')

    plt.xlabel(f'Capacidad del recurso {recurso_objetivo}')
    plt.ylabel('Tiempo promedio de espera')
    plt.title(f'Tiempo de espera vs Capacidad - {recurso_objetivo}')
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.show()

capacidades = range(1, 11)

recurso1 = 'Doctores'
datos1 = run_experiments(recurso1, capacidades)
graficar_resultados(datos1, recurso1)


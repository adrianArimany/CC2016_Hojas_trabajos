
from main import *
import simpy
import matplotlib.pyplot as plt
import pandas as pd


def run_experiments(recurso_objetivo, capacidades, num_pacientes, interval):
    resultados_globales = []
    #Realizar variando la capacidad de un recurso en específico
    for cap in capacidades:
        #Estas con las capacidades fijas del hospital
        env = simpy.Environment()
        Enfermeras = simpy.Resource(env, capacity=2) #2 enfermeras registrando
        RayosX = simpy.PriorityResource(env, capacity=2) #2 tomógrafos
        Laboratorio = simpy.PriorityResource(env, capacity=5) #5 técnicos de laboratorio
        Operaciones = simpy.PriorityResource(env, capacity=2) #2 equipos de operaciones (cada equipo con 3 doctores y 2 enfermeras)
        Doctores = simpy.PriorityResource(env, capacity=3) #3 doctores en consulta

        #Dependiendo que recurso es el que se desee variar en cierto rango irá cambiando la capacidad
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

        #Realizar la simulación
        resultados = []
        env.process(
            generate_simulation(env, Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, num_pacientes, interval,
                                resultados))
        env.run()

        #Guardar resultados de todos los pacientes
        for r in resultados:
            if r["recurso"] == recurso_objetivo:
                r["capacidad"] = cap
                resultados_globales.append(r)
    return resultados_globales


def graficar_resultados(resultados, recurso_objetivo):
    df = pd.DataFrame(resultados)
    df_filtrado = df[df["recurso"] == recurso_objetivo]

    #Realizar la gráfica para cada una de las 5 prioridades con el tiempo promedio de espera. Se utilizan los reultados del método anterior
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






import random

import simpy

random.seed(12)

def simulation(env, nombre, RayosX, Laboratorio):
    print(f"El paciente {nombre} ha ingresado a la sala de espera en el tiempo {env.now}")
    arrival_time = env.now
    prioridad = random.randint(1,5) #Prioridad del paciente
    yield env.timeout(10) #Se lleva a cabo el registro
    proceso = random.randint(1,2) #Proceso que se llevará a cabo 1)Rayos X 2)Examen de laboratorio
    print(f"Se ha registrado al paciente {nombre}, con prioridad {prioridad} en el tiempo {env.now}")
    if proceso == 1:
        with RayosX.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera los rayos X")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado al tomógrafo en el tiempo {env.now}")
            yield env.timeout(10)
    else:
        with Laboratorio.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera el Laboratorio")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado al laboratorio en el tiempo {env.now}")
            yield env.timeout(30)

    print(f"El paciente {nombre} (prioridad {prioridad}) ha salido en el tiempo {env.now}, tuvo que esperar {wating_time} para ser atendido")



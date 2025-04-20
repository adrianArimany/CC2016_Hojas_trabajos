import random

import simpy

random.seed(11)

def simulation(env, nombre,Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, resultados):
    print(f"El paciente {nombre} ha ingresado a la sala de espera en el tiempo {env.now}")
    arrival_time = env.now
    prioridad = random.randint(1,5) #Prioridad del paciente
    with Enfermeras.request() as req:
        yield req
        print(f"El paciente {nombre} está siendo registrado por una enfermera")
        yield env.timeout(10)
        reg_time = env.now - arrival_time
        print(f"El paciente {nombre}, prioridad: {prioridad}, ha sido registrado en el tiempo {env.now}")
        proceso = random.randint(1,4)
    if proceso == 1:
        with RayosX.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera los rayos X")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado al tomógrafo en el tiempo {env.now}")
            yield env.timeout(10)
            proceso_nombre = "RayosX"
    elif proceso == 2:
        with Laboratorio.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera el Laboratorio")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado al laboratorio en el tiempo {env.now}")
            yield env.timeout(30)
            proceso_nombre = "Laboratorio"
    elif proceso == 3:
        with Doctores.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera ser atendido por un doctor")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado a consulta con un doctor en el tiempo {env.now}")
            yield env.timeout(30)
            proceso_nombre = "Doctores"
    elif proceso == 4:
        with Operaciones.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera para ser operado")
            yield req
            wating_time = env.now - arrival_time
            print(f"El paciente {nombre} ha ingresado a la sala de operaciones en el tiempo {env.now}")
            op_time = random.randint(120,240)
            yield env.timeout(op_time)
            proceso_nombre = "Operaciones"

    resultados.append({
        "prioridad": prioridad,
        "waiting_time": wating_time,
        "recurso": proceso_nombre
    })

    print(f"El paciente {nombre} (prioridad {prioridad}) ha salido en el tiempo {env.now}, tuvo que esperar {reg_time} para ser registrado y {wating_time} para ser atendido según su condición")

def generate_simulation(env, Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, num_pacientes, interval, resultados):
    for i in range(num_pacientes):
        env.process(simulation(env, str(i), Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, resultados))
        yield env.timeout(random.expovariate(1/interval))

#env = simpy.Environment()
#Enfermeras = simpy.Resource(env, capacity=5)
#RayosX = simpy.PriorityResource(env, capacity=5)
#Laboratorio = simpy.PriorityResource(env, capacity=15)
#Doctores = simpy.PriorityResource(env, capacity=20)
#Operaciones = simpy.PriorityResource(env, capacity=5)

#env.process(generate_simulation(env, Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, 100, 10))
#env.run()
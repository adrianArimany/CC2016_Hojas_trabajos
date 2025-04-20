import random

import simpy

random.seed(11)

def simulation(env, nombre,Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, resultados):
    print(f"El paciente {nombre} ha ingresado a la sala de espera en el tiempo {env.now}") #Informar a que hora ingresó el usuario
    arrival_time = env.now
    prioridad = random.randint(1,5) #Prioridad del paciente
    with Enfermeras.request() as req:
        yield req #Esperar a ser atendido por una enfermera
        print(f"El paciente {nombre} está siendo registrado por una enfermera")
        yield env.timeout(10) #Llevar a cabo registro
        reg_time = env.now - arrival_time #Registrar a que "hora" ya se encontraba registrado el paciente
        print(f"El paciente {nombre}, prioridad: {prioridad}, ha sido registrado en el tiempo {env.now}")
        proceso = random.randint(1,4) #Simular el procedimiento desginado al paciente (1 Rayos X, (2 Examenes de laboratorio, (3 Ser atendido por un doctor, (4 Operación
        resultados.append({
            "prioridad": prioridad,
            "waiting_time": reg_time,  # usamos el tiempo de registro (guardar cuanto tiempo se tardaron en registrar al paciente)
            "recurso": "Enfermeras"
        })
    if proceso == 1:
        with RayosX.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera los rayos X") #Indicar que el paciente se encuentra en la sala de espera, designado a RayosX
            yield req #Esperar espacio en un tomógrafo
            wating_time = env.now - arrival_time #Registrar cuanto tiempo tardó en llegar hasta entrar al tomógrafo
            print(f"El paciente {nombre} ha ingresado al tomógrafo en el tiempo {env.now}")
            yield env.timeout(10) #Simular radiografía
            proceso_nombre = "RayosX"
            resultados.append({ #Guardar resultados de tiempo de espera
                "prioridad": prioridad,
                "waiting_time": wating_time,
                "recurso": proceso_nombre
            })

    elif proceso == 2:
        with Laboratorio.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera el Laboratorio") #Indicar que el paciente se encuentra en la sala de espera, designado a Laboratorio
            yield req #Esperar espacio en el laboratorio
            wating_time = env.now - arrival_time #Registrar cuanto tiempo tardó en llegar hasta que se le realizaran los examenes
            print(f"El paciente {nombre} ha ingresado al laboratorio en el tiempo {env.now}")
            yield env.timeout(30) #Simular examenes de laboratorio
            proceso_nombre = "Laboratorio"
            resultados.append({ #Guardar resultados de tiempo de espera
                "prioridad": prioridad,
                "waiting_time": wating_time,
                "recurso": proceso_nombre
            })

    elif proceso == 3:
        with Doctores.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera ser atendido por un doctor") #Indicar que el paciente se encuentra en la sala de espera, designado a ser atendido por un doctor
            yield req #Esperar disponibilidad de un doctor
            wating_time = env.now - arrival_time #Registrar cuanto tiempo tardó en llegar hasta que se fuera atendido por un doctor
            print(f"El paciente {nombre} ha ingresado a consulta con un doctor en el tiempo {env.now}")
            yield env.timeout(30) #Simular consulta con un doctor
            proceso_nombre = "Doctores"
            resultados.append({ #Guardar resultados de tiempo de espera
                "prioridad": prioridad,
                "waiting_time": wating_time,
                "recurso": proceso_nombre
            })

    elif proceso == 4:
        with Operaciones.request(priority = prioridad) as req:
            print(f"El paciente {nombre} espera para ser operado") #Indicar que el paciente se encuentra en la sala de espera, designado a ser operado
            yield req #Esperar disponibilidad de quirófano
            wating_time = env.now - arrival_time #Registrar cuanto tiempo tardó en llegar hasta que se fuera atendido para operación
            print(f"El paciente {nombre} ha ingresado a la sala de operaciones en el tiempo {env.now}")
            op_time = random.randint(120,240)
            yield env.timeout(op_time) #Simular tiempo de operación, generalmente una operación de emergencia dura entre 2 y 4 horas
            proceso_nombre = "Operaciones"
            resultados.append({ #Guardar resultados de tiempo de espera
                "prioridad": prioridad,
                "waiting_time": wating_time,
                "recurso": proceso_nombre
            })

    print(f"El paciente {nombre} (prioridad {prioridad}) ha salido en el tiempo {env.now}, tuvo que esperar {reg_time} para ser registrado y {wating_time} para ser atendido según su condición")

def generate_simulation(env, Enfermeras, RayosX, Laboratorio, Doctores, Operaciones, num_pacientes, interval, resultados):
    #Generar la simulación para n cantidad de pacientes
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
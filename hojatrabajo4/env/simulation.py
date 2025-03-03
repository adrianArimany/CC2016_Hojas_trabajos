import random

from env import config
from env import process

def process_generator(env, cpu, ram, process_data):
    """
    Generator function taht creates process at random intervals

    Paramaters:
        env (simpy.Enviroment): The simulation enviroment
        cpu (simpy.Resource): THe CPU resource
        ram (simpy.Container): the RAM resource
        process_data (list): A list to store data (timing) for each process
    """
    
    process_id = 0
    running = True
    while running:
        interarrival = random.expovariate(1/config.INTERARRIVAL_TIME)
        yield env.timeout(interarrival)
        process_id += 1
        env.process(process.run_process(env, cpu, ram, process_data, process_id))
        

def run_simulation(env, cpu, ram):
    """
    Function that runs the simulation

    Paramaters:
        env (simpy.Enviroment): The simulation enviroment
        cpu (simpy.Resource): THe CPU resource
        ram (simpy.Container): the RAM resource
    Return:
        List: collected data about process execution
    """
    process_data = []
    env.process(process_generator(env, cpu, ram, process_data))

    env.run(until=config.SIM_DURATION)

    return process_data

def get_process_data(process_data):
    """
    Returns the collected process data.
    Paramete
        process_data (list): A list to store data (timing) for each process
    Return:
        List: collected data about process execution
    """
    return process_data
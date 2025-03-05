import random

from enviroment import config
from enviroment import process

def process_generator(env, cpu, ram, process_data, totalprocesos):
    """
    Generator function taht creates process at random intervals

    Paramaters:
        env (simpy.Enviroment): The simulation enviroment
        cpu (simpy.Resource): THe CPU resource
        ram (simpy.Container): the RAM resource
        process_data (list): A list to store data (timing) for each process
    """

    for i in range(totalprocesos):
        env.process(process.process_behavior(env, i, cpu, ram, process_data))
        interarrival = random.expovariate(1 / config.INTERARRIVAL_TIME)
        yield env.timeout(interarrival)

        

def run_simulation(env, cpu, ram, totalprocesos):
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
    env.process(process_generator(env, cpu, ram, process_data, totalprocesos))
    env.run()

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
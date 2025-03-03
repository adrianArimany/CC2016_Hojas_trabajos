import simpy


from enviroment import config

def initialize_resources(env):
    ram = simpy.Container(env, init=config.RAM_CAPACITY, capacity=config.RAM_CAPACITY)
    cpu = simpy.Resource(env, capacity=config.CPU_COUNT)

    return ram, cpu


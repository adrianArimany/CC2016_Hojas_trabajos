import simpy


from env import config

def initialize_resources(env):
    ram = simpy.Resource(env, init=config.RAM_CAPACITY, capacity=config.RAM_SIZE)
    cpu = simpy.Resource(env, capacity=config.CPU_SIZE)

    return ram, cpu


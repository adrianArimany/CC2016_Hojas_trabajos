import random
import simpy

from env import config

def process_behavior(env, cpu, ram, process_data, process_id):
    """
    Behavior of the process

    Paramaters:
        env (simpy.Enviroment): The simulation enviroment
        cpu (simpy.Resource): THe CPU resource
        ram (simpy.Container): the RAM resource
        process_data (list): A list to store data (timing) for each process
        process_id (int): unique identifier for the process
    """
    arrival_time = env.now
    print(f"Process {process_id} arrived at {arrival_time}")
    
    memory_needed =  random.randint(config.MEMORY_REQUEST_MIN, config.MEMORY_REQUEST_MAX)
    yield ram.get(memory_needed)
    print(f"Process {process_id} requested {memory_needed} bytes of memory")
    
    instructions = random.randint(config.INSTRUCTION_REQUEST_MIN, config.INSTRUCTION_REQUEST_MAX)
    print(f"Process {process_id} requested {instructions} instructions to execute")
    
    while instructions > 0:
        with cpu.request() as req:
            yield req
            yield env.timeout(1)
            
            executed = config.CPU_SPEED
            if instructions < executed:
                executed = instructions
            instructions -= executed
            print(f"Process {process_id} executed {executed} instructions. {instructions} remaining at time {env.now}")
            
            if instructions > 0:
                decision = random.randint(1,2)
                if decision == 1:
                    print("Process {process_id} performing I/O operation at time {env.now}")
                    io_delay = 1
                    yield env.timeout(io_delay)
                else:
                    print(f"Process {process_id} returns to ready state at time {env.now}")

    
    termination_time = env.now
    turnaround_time = termination_time - arrival_time
    print(f"Process {process_id} terminated at {termination_time}. Turnaround time: {turnaround_time}")
    
    yield ram.put(memory_needed)
    print(f"Process {process_id} released {memory_needed} bytes of memory")
    
    process_data.append({
        'process_id': process_id,
        'arrival': arrival_time,
        'termination': termination_time,
        'turnaround': turnaround_time
    })
    
    
# process.py
import random

from enviroment import config

def process_behavior(env, process_id, cpu, ram, process_data):
    """
    Simulate the lifecycle of a process.
    
    Parameters:
        env (simpy.Environment): The simulation environment.
        process_id (int): Unique identifier for the process.
        cpu (simpy.Resource): The CPU resource.
        ram (simpy.Container): The RAM resource.
        config (module): Configuration parameters.
        process_data (list): Shared list to store process metrics.
    """
    # Record the arrival time
    arrival_time = env.now
    print(f"Process {process_id} arrived at time {arrival_time}")
    
    memory_needed = random.randint(config.MEMORY_REQUEST_MIN, config.MEMORY_REQUEST_MAX)
    yield ram.get(memory_needed)
    print(f"Process {process_id} allocated {memory_needed} memory units at time {env.now}")
    
    instructions = random.randint(config.INSTRUCTION_REQUEST_MIN, config.INSTRUCTION_REQUEST_MAX)
    print(f"Process {process_id} has {instructions} instructions to execute")
    
    while instructions > 0:
        with cpu.request() as req:
            yield req  # Wait until the CPU is available
            yield env.timeout(1)
            
            # Process executes a fixed number of instructions per CPU cycle
            executed = config.CPU_SPEED
            if instructions < executed:
                executed = instructions
            instructions -= executed
            print(f"Process {process_id} executed {executed} instructions; {instructions} remaining at time {env.now}")
        
        # If there are still instructions left, decide the next state
        if instructions > 0:
            decision = random.randint(1, 21)
            if decision == 1:
                # Simulate I/O operation delay (process goes to waiting)
                print(f"Process {process_id} performing I/O operations at time {env.now}")
                io_delay = 1  # You can also randomize or adjust the I/O delay as needed
                yield env.timeout(io_delay)
            else:
                # Otherwise, simply continue (return to the ready state)
                print(f"Process {process_id} returns to ready state at time {env.now} (decision value: {decision})")
    
    termination_time = env.now
    print(f"Process {process_id} terminated at time {termination_time}")
    
    yield ram.put(memory_needed)
    print(f"Process {process_id} released {memory_needed} memory units at time {env.now}")
    
    turnaround_time = termination_time - arrival_time
    process_data.append({
        'process_id': process_id,
        'arrival': arrival_time,
        'termination': termination_time,
        'turnaround': turnaround_time
    })

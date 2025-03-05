RANDOM_SEED = 51 # Random seed for reproducibility

RAM_CAPACITY = 100 # The total available memory in the simulation.


CPU_COUNT = 1 #Number of CPUs that are currently running in the simulation

CPU_SPEED = 3 #The number of instructions executed per CPU cycle.


#The minimum and maximum memory request per request 
MEMORY_REQUEST_MIN = 1

MEMORY_REQUEST_MAX = 10

#Define the range for the number of instructions a process will have.
INSTRUCTION_REQUEST_MIN = 1

INSTRUCTION_REQUEST_MAX = 3

#IF the state is waiting, then there is a 1/2 chance  for the process to return to ready.
IO_PROBABILITY = 1/2

#The average time between process arrivals
INTERARRIVAL_TIME = 10

#The duration of the simulation (in case the simulation never terminates)
SIM_DURATION = 25


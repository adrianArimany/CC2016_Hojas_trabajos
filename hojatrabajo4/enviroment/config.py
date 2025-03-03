RANDOM_SEED = 42 # Random seed for reproducibility

RAM_CAPACITY = 100 # Total Avaialable Memory


CPU_COUNT = 2 #Number of CPUs that are currently running in the simulation

CPU_SPEED = 3 #THE instructions to be exectuted


#The minimum and maximum memory request per request 
MEMORY_REQUEST_MIN = 1

MEMORY_REQUEST_MAX = 10

#The minimum and maximum of instructions per process
INSTRUCTION_REQUEST_MIN = 1

INSTRUCTION_REQUEST_MAX = 10

#IF the state is waiting, then there is a 1/21 chance  for the process to return to ready.
IO_PROBABILITY = 1/21

#The average time between process arrivals
INTERARRIVAL_TIME = 10

#The duration of the simulation (in case the simulation never terminates)
SIM_DURATION = 10000


#Other modules:
#Env deals with the actual simulation process
from env import config, resources, simulation, process
#results gets the calculations from simulation and obtains the required graphs.
from results import metrics, plotting

#Packages for the assigment:
import random
import simpy


def main():
    #Initialize random seed and simulation enviroment
    random.seed(config.RANDOM_SEED)
    env = simpy.Enviroment()

    ram, cpu = resources.initialize_resources(env, config)

    simulation.run_simulation(env, cpu, ram, config)

    results = metrics.compute_metrics(simulation.get_process_data())

    print("Expected Turnaround time: ", results['avg_turnaround'])
    print("Standard Deviation: ", results['std_turnaround'])
    
    plotting.plot_results(results)

if __name__ == "__main__":
    main()
    
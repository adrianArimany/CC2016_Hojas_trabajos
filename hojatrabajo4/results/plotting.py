# plotting.py
import matplotlib.pyplot as plt

def plot_results(results):
    """
    Generate plots based on simulation results.
    
    Parameters:
        results (dict): A dictionary containing simulation metrics.
                        Expected keys:
                          - 'turnaround_times': list of turnaround times.
                          - 'avg_turnaround': average turnaround time.
                          - 'std_turnaround': standard deviation of turnaround times.
                          - 'count': total number of processes.
    """
    turnaround_times = results.get('turnaround_times', [])
    avg_turnaround = results.get('avg_turnaround', 0)
    std_turnaround = results.get('std_turnaround', 0)
    
    # Plot 1: Histogram of Turnaround Times
    plt.figure()
    plt.hist(turnaround_times, bins=10, edgecolor='black')
    plt.title("Histogram of Turnaround Times")
    plt.xlabel("Turnaround Time")
    plt.ylabel("Frequency")
    plt.axvline(avg_turnaround, color='red', linestyle='dashed', linewidth=1,
                label=f"Avg: {avg_turnaround:.2f}")
    plt.legend()
    plt.show()

    # Plot 2: Bar Chart for Average Turnaround Time with Standard Deviation
    plt.figure()
    plt.bar(['Average Turnaround'], [avg_turnaround], yerr=[std_turnaround], capsize=10)
    plt.title("Average Turnaround Time with Standard Deviation")
    plt.ylabel("Time")
    plt.show()

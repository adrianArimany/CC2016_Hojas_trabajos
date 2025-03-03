import statistics

def compute_metrics(process_data):
    """
    Computes and retuns the simulation metrics based on the collected process data
    
    parameters:
        process_data (list): A list of dictionaries containing the timing information for each process
    return:
        dict: A dictionary with the following keys:
            avg_turnaround: The average turnaround time of the processes
            std_turnaround: The standard deviation of the turnaround times of the processes
            count: total number of processes
            turnaround_time: the list of individual turnaround times.
    """
    
    turnaround_times = [data['turnaround_time'] for data in process_data]
    
    if turnaround_times:
        avg_turnaround = statistics.mean(turnaround_times)
        std_turnaround = statistics.stdev(turnaround_times)
    else:
        avg_turnaround = 0
        std_turnaround = 0

    return {
        'avg_turnaround': avg_turnaround,
        'std_turnaround': std_turnaround,
        'count': len(process_data),
        'turnaround_time': turnaround_times
    }
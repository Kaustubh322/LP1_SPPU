import java.util.*;

class Process {
    int pid, burstTime, priority, waitingTime, turnAroundTime;
    
    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time and priority for process " + (i + 1) + ": ");
            processes[i] = new Process(i + 1, sc.nextInt(), sc.nextInt());
        }
        
        // Sort processes by priority (higher priority first)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));
        
        int totalWaitingTime = 0;
        processes[0].waitingTime = 0; // First process has zero waiting time
        for (int i = 1; i < n; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            totalWaitingTime += processes[i].waitingTime;
        }
        
        int totalTurnAroundTime = 0;
        for (Process p : processes) {
            p.turnAroundTime = p.waitingTime + p.burstTime;
            totalTurnAroundTime += p.turnAroundTime;
            System.out.println("Process " + p.pid + ": Waiting Time = " + p.waitingTime + 
                               ", Turnaround Time = " + p.turnAroundTime);
        }
        
        System.out.println("Average Waiting Time = " + (double) totalWaitingTime / n);
        System.out.println("Average Turnaround Time = " + (double) totalTurnAroundTime / n);
    }
}

import java.util.*;

class Process {
    int pid, bt, at, ct, wt, tat, rt;
    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = this.rt = bt;
    }
}

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            processes.add(new Process(i + 1, sc.nextInt(), sc.nextInt()));
        }

        int currentTime = 0, completed = 0, prev = -1;
        float totalWT = 0, totalTAT = 0;

        while (completed < n) {
            Process shortest = null;
            for (Process p : processes) {
                if (p.at <= currentTime && p.rt > 0 && (shortest == null || p.rt < shortest.rt)) {
                    shortest = p;
                }
            }

            if (shortest == null) {
                currentTime++;
                continue;
            }

            if (prev != shortest.pid) System.out.println("Time " + currentTime + ": Process " + shortest.pid + " starts.");
            prev = shortest.pid;

            shortest.rt--;
            currentTime++;
            if (shortest.rt == 0) {
                completed++;
                shortest.ct = currentTime;
                shortest.tat = shortest.ct - shortest.at;
                shortest.wt = shortest.tat - shortest.bt;
                totalWT += shortest.wt;
                totalTAT += shortest.tat;
                System.out.println("Time " + currentTime + ": Process " + shortest.pid + " completes.");
            }
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            System.out.println(p.pid + "\t" + p.at + "\t" + p.bt + "\t" + p.ct + "\t" + p.tat + "\t" + p.wt);
        }

        System.out.printf("\nAverage Waiting Time: %.2f", totalWT / n);
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        sc.close();
    }
}

import java.util.Scanner;

public class BestFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blocks = new int[m];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < m; i++) blocks[i] = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int p = sc.nextInt();
        int[] processes = new int[p];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < p; i++) processes[i] = sc.nextInt();

        for (int i = 0; i < p; i++) {
            int bestIndex = -1;
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= processes[i] && (bestIndex == -1 || blocks[j] < blocks[bestIndex])) {
                    bestIndex = j;
                }
            }
            if (bestIndex != -1) {
                System.out.println("Process " + i + " allocated to block " + bestIndex);
                blocks[bestIndex] -= processes[i];
            } else {
                System.out.println("Process " + i + " not allocated");
            }
        }
        sc.close();
    }
}

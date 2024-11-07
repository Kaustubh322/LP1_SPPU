import java.util.*;

public class FirstFit {
    public static void main(String[] args) {
        int[] blocks = {100, 500, 200, 300, 600};  // Memory block sizes
        int[] processes = {212, 417, 112, 426};    // Process sizes

        for (int process : processes) {
            boolean allocated = false;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= process) {
                    System.out.println("Process " + process + "KB allocated to block " + blocks[j] + "KB");
                    blocks[j] -= process;
                    allocated = true;
                    break;
                }
            }
            if (!allocated)
                System.out.println("Process " + process + "KB not allocated");
        }
    }
}

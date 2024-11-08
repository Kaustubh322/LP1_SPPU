class NextFit {
    static void nextFit(int[] memory, int[] processes) {
        int j = 0; // last allocated index
        for (int process : processes) {
            int start = j;
            while (memory[j] < process) {
                j = (j + 1) % memory.length;
                if (j == start) break; // If we loop back, no fit is found
            }
            if (memory[j] >= process) {
                memory[j] -= process;
                System.out.println("Allocated " + process + " to block " + j);
            } else {
                System.out.println("Could not allocate " + process);
            }
        }
    }

    public static void main(String[] args) {
        int[] memory = {100, 500, 200, 300, 600};
        int[] processes = {212, 417, 112, 426};
        nextFit(memory, processes);
    }
}

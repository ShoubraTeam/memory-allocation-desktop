package memalloc;

import java.util.*;

class MemAlloc {
    private static final int MEMORY_SIZE = 1024;
    private final long RANDOM_SEED = System.currentTimeMillis();
    private final Random random = new Random(RANDOM_SEED);

    private List<MemBlock> memoryBlocks = new ArrayList<>();
    private final Queue<Process> startingList = new LinkedList<>();
    private final Queue<Process> runningList = new LinkedList<>();
    static CustomPriorityQueue<Process> waitingList = new CustomPriorityQueue<Process>();

    public static void main(String[] args) {
        MemAlloc memAlloc = new MemAlloc();
        memAlloc.initializeMemory();
        memAlloc.initializeStartingList();
        memAlloc.runSimulation();
    }

    void initializeMemory() {
        memoryBlocks.add(new MemBlock( 0, MEMORY_SIZE - 1));
    }

    void initializeStartingList() {
        System.out.println("Using seed: " + RANDOM_SEED);
        for (int i = 0; i < 10; i++) {
            int size = random.nextInt(512) + 1;
            int time = random.nextInt(10000) + 1;
            startingList.add(new Process(size, time));
        }
    }

    void runSimulation() {
        int timeElapsed = 0;
        while (!startingList.isEmpty() || !runningList.isEmpty() || !waitingList.isEmpty()) {
            System.out.println("-----------------------");
            System.out.println("At time: " + (++timeElapsed));
            dispatchProcess();
            updateRunningProcesses();
//            updateLists();
            displayProcesses();
            System.out.println("-----------------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                System.out.println("interruptedException");
            }
        }
    }

    void dispatchProcess() {
        if (startingList.isEmpty() && waitingList.isEmpty()) return;
        Process process;
        if (!waitingList.isEmpty() && !startingList.isEmpty()){
            process = (startingList.peek().getSize() < waitingList.peek().getSize()) ? startingList.poll() : waitingList.poll();
        } else if (!startingList.isEmpty()){
            process = startingList.poll();
        } else {
            process = waitingList.poll();
        }
        if (process == null) return;
        if (allocateMemory(process)) {
            runningList.add(process);
        } else {
            waitingList.add(process, process.getSize());
        }
    }

    /*
    * Running Processes:
    * Process ID: 8, Size: 400, Time Needed: 17
    * Waiting Processes:
    * Process ID: 7, Size: 405, Time Needed: 6970
    * */

    boolean allocateMemory(Process process) {
        for (MemBlock block : memoryBlocks) {
            System.out.println("Trying to allocate process ID: " + process.getId() + " with size = " + process.getSize() + " and block size = " + block.getSize());
            if (block.getSize() >= process.getSize()) {
                process.setAssignedBlock(new MemBlock(block.getStartAddress(), block.getStartAddress() + process.getSize() - 1));
                block.setStartAddress(block.getStartAddress() + process.getSize());
                block.setSize(block.getSize() - process.getSize());
                return true;
            }
        }
        return false;
    }

    void updateRunningProcesses() {
        Iterator<Process> runningIterator = runningList.iterator();
        while (runningIterator.hasNext()) {
            Process process = runningIterator.next();
            process.decrementBySecond();
            if (process.getTime() <= 0) {
                runningIterator.remove();
                releaseMemory(process);
                System.out.println("\\-----------------------------------------------------------------------------------\\");
                System.out.println("Process " + process.getId() + " ended");
                System.out.println("\\-----------------------------------------------------------------------------------\\");
            }
        }
    }

    void releaseMemory(Process process) {
        System.out.println("Free memory of process ID: " + process.getId() + " with size: " + process.getSize());
        MemBlock releasedBlock = process.getAssignedBlock();
        int position = searchInsert(releasedBlock);
        memoryBlocks.add(position, releasedBlock);
        mergeMemoryBlocks();
    }

    void mergeMemoryBlocks() {
//        Collections.sort(memoryBlocks, Comparator.comparingInt(block -> block.startAddress));
        List<MemBlock> mergedBlocks = new ArrayList<>();
        MemBlock previous = null;
        for (MemBlock block : memoryBlocks) {
            if (previous == null) {
                previous = block;
            } else if (previous.getEndAddress() + 1 == block.getStartAddress()) {
                previous.setSize(previous.getSize() + block.getSize());
                previous.setEndAddress(block.getEndAddress());
            } else {
                mergedBlocks.add(previous);
                previous = block;
            }
        }
        if (previous != null) {
            mergedBlocks.add(previous);
        }
        memoryBlocks = mergedBlocks;
        for (MemBlock memBlock : memoryBlocks){
            System.out.println("[MemBlock] Start address = " + memBlock.getStartAddress() + ", end address = " + memBlock.getEndAddress() + ", and size = " + memBlock.getSize());
        }
    }

//    void updateLists() {
//        if (waitingList.isEmpty())return;
//        Process process =waitingList.peek();
//        if (allocateMemory(process)){
//            waitingList.remove();
//            runningList.add(process);
//        }
//    }

    void displayProcesses() {
        System.out.println("Running Processes:");
        for (Process process : runningList) {
            System.out.println(process);
        }
        System.out.println("--------------");
        System.out.println("Waiting Processes:");
        waitingList.display();
        System.out.println("--------------");
    }

    public int searchInsert(MemBlock memBlockPiece) {
        int start = 0;
        int end = memoryBlocks.size()-1;

        while (start <= end) {
            int mid = start + (end-start)/2;
            if (memoryBlocks.get(mid).getStartAddress() > memBlockPiece.getEndAddress()) end = mid-1;
            else start = mid+1;
        }
        return start;
    }
}

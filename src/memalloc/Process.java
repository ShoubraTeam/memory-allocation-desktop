package memalloc;

/**
 *
 * @author HANIN
 */

class Process {
    private static int idCounter = 0;
    private final int id;
    private final int size;
    private int time;
    private MemBlock assignedBlock;

    public Process(int size, int time) {
        this.id = ++idCounter;
        this.size = size;
        this.time = time;
    }

    public int getSize() {
        return this.size;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public MemBlock getAssignedBlock() {
        return this.assignedBlock;
    }

    public void setAssignedBlock(MemBlock assignedBlock) {
        this.assignedBlock = assignedBlock;
    }

    public int getId() {
        return this.id;
    }

    public void decrementBySecond() {
        this.time -= 1000;
    }

    @Override
    public String toString() {
        return "Process ID: " + id + " with size: " + size + " and time needed: " + time;
    }
}


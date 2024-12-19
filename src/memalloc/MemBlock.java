package memalloc;

/**
 * @author HANIN
 */
class MemBlock {
    private int startAddress;
    private int endAddress;
    private int size;

    public MemBlock(int startAddress, int endAddress) {
        this.size = endAddress - startAddress + 1;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    @Override
    public String toString() {
        return "MemBlock [size=" + size + ", startAddress=" + startAddress + ", endAddress=" + endAddress + "]";
    }

    public int getStartAddress(){
        return startAddress;
    }

    public int getEndAddress(){
        return endAddress;
    }

    public int getSize(){
        return size;
    }

    public void setStartAddress(int s){
        startAddress = s;
    }

    public void setEndAddress(int e){
        endAddress = e;
    }

    public void setSize(int s){
        size = s;
    }
}

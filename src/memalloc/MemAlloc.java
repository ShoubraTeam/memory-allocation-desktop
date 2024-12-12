/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package memalloc;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author HANIN
 */
public class MemAlloc {
    static Queue<Process> StartingList = new LinkedList<Process>();
    static LinkedList<MemBlock> MemoryBlockList = new LinkedList<>();
    static LinkedList<Process> WaitingList = new LinkedList<Process>();
    static LinkedList<Process> RunningList = new LinkedList<Process>();
    static int timeOut;

    public static void main(String[] args) {
        Update();
        Update();

    }

    static void Update() {
        Iterator<Process> RunningIterator = RunningList.iterator();
        while (RunningIterator.hasNext()) {
            Process process = RunningIterator.next();
            process.setTime();
            if (process.getTime() <= 0) {
                RunningIterator.remove();
                freeMemory(process);
                System.out.println("Process " + process.getID() + " ended.");
            }
        }
        Iterator<Process> WaitingIterator = WaitingList.iterator();
        while (WaitingIterator.hasNext()) {
            Process process = WaitingIterator.next();
            MemBlock freeBlock = findFreeBlock(process.getSize()); // Find a suitable block
            if (freeBlock != null) {
                allocateMemory(process, freeBlock); // Allocate memory
                WaitingIterator.remove(); // Remove from waiting list
                RunningList.add(process); // Move to running list
                System.out.println("Process " + process.getID() + " moved to running list.");
            }


        }
    }
    void freeMemory(Process p){
        MemBlock block = p.assignedBlock;
        memoryBlocks.add(block); // Add the freed block back to the memory list
        mergeMemoryBlocks(); // Merge adjacent free blocks (optional)
    }

    private static void initializeMemory() {
        MemoryBlockList.add(new MemBlock(0, 1024)); // كتلة ذاكرة كاملة
    }

}
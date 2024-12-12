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
    

    public static void main(String[] args) {
        initializeMemory();
        initializeStartingList();

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
        if (!StartingList.isEmpty()) {
            Process process = StartingList.poll();
            MemBlock freeBlock = findFreeBlock(process.getSize());
            if (freeBlock != null) {
                allocateMemory(process, freeBlock);
                RunningList.add(process);
                System.out.println("Process " + process.getID() + " started running.");
            } else {
                WaitingList.add(process);
                System.out.println("Process " + process.getID() + " added to waiting list.");
            }

        }
    }
    static void freeMemory(Process p){
        MemBlock block = p.getAssignedBlock();
        MemoryBlockList.add(block); // Add the freed block back to the memory list
        mergeMemoryBlocks(); // Merge adjacent free blocks (optional)
    }

    static void allocateMemory(Process process, MemBlock block) {
        process.setAssignedBlock(new MemBlock(block.getStartAdress(), block.getStartAdress()+ process.getSize() - 1));
        block.setStartAdress(block.getStartAdress() + process.getSize()); // Update the free block's start address
        block.setSize(block.getSize() - process.getSize()); // Update the size of the free block
        /*if (block.getSize()  == 0) {
            memoryBlocks.remove(block);
        }*/
    }

    static void initializeMemory() {
        MemoryBlockList.add(new MemBlock(0, 1024));
    }

    static void initializeStartingList() {
        for (int i = 1; i <= 20; i++) {
            StartingList.add(new Process());
        }
    }
    static void mergeMemoryBlocks(){}
    static MemBlock findFreeBlock(int size){}
}

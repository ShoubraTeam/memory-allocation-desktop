/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memalloc;

/**
 *
 * @author HANIN
 */
import java.util.Random;

public class Process {
    Random r=new Random();
    private static int id = 0;
    private int size = (r.nextInt( 256)) + 1;
    private int time = (r.nextInt( 1000)) + 1;
    private MemBlock assignedBlock;
    
    public Process(){
        ++id;
    }
    
    void assignMemBlock(MemBlock block){
        assignedBlock = block;
    }
    
    @Override
    public String toString(){
        return "Process " + id + " with size: " + size;
    }
}

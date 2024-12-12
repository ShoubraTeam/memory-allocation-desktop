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
    private static int ID = 0;
    private int size = (r.nextInt( 256)) + 1;
    private int time = (r.nextInt( 20000)) + 1;
    private MemBlock assignedBlock;
    
    public Process(){
        ++ID;
    }
    
    void assignMemBlock(MemBlock block){
        assignedBlock = block;
    }
    public int getTime(){
        return time;
    }
    public int getID(){
        return ID;
    }
    public int getSize(){
        return size ;
    }
    public MemBlock getAssignedBlock(){
        return assignedBlock ;
    }
    public void setTime(){
        this.time-=1000;
    }

    @Override
    public String toString(){
        return "Process " + id + " with size: " + size;
    }
}

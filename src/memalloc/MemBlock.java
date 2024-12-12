/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memalloc;

/**
 *
 * @author HANIN
 */
public class MemBlock {
    
    private int startAdress;
    private int endAdress;
    private int size;
    
    public MemBlock(int stAdress, int s){
        startAdress = stAdress;
        size = s;
        endAdress = startAdress + size - 1;
    }
    public int getStartAdress(){
        return startAdress;
    }
    public int getEndAdress(){
        return endAdress;
    }
    public int getSize(){
        return size;
    }

    public void setStartAdress(int s){
        startAdress = s;
    }
    public void setSize(int s){
        size = s;
    }
    
    
}

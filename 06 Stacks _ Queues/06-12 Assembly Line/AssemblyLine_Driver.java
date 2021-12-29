//Name: J1-24    
//Date: 1-21-2021
import java.util.*;

public class AssemblyLine_Driver
{
   static int NDISKS = 50;
   static int MAXRADIUS = 100;
   public static void main(String[] args)
   {
      AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
      a.showInput();
      a.process();
      a.showOutput();
   }
}
   
class AssemblyLine
{
   private Queue<Disk> assemblyLineIn;
   private Queue<Pyramid> assemblyLineOut;
   private Pyramid robotArm;
   	/**
   		* initializes this object so the assemblyLineIn contains 
   		* nDisks with random radii;  assemblyLineOut is initialized to * an empty Queue; robotArm is initialized to an empty Pyramid.
   		**/
   	//Part A
   public AssemblyLine( int nDisks, int maxRadius )
   {	
      assemblyLineOut = new LinkedList<Pyramid>();
      robotArm = new Pyramid();   
      assemblyLineIn = new LinkedList<Disk>();
      for(int x = 0; x < nDisks; x++)
      {
         assemblyLineIn.add(new Disk((int)(Math.random() * maxRadius + 1)));
      }
   }
   
   	/**
   		* "flips over" the pyramid in the robotArm and adds it to the
   		* assemblyLineOut queue.
   		* Precondition:  robotArm is not empty and holds an inverted 
   		*				pyramid of disks
   		**/
   	// Part B
   private void unloadRobot()
   { 
      Pyramid temp = new Pyramid();
      while(!(robotArm.isEmpty()))
         temp.push(robotArm.pop());
      assemblyLineOut.add(temp);
   }
   
   	/**
   		* processes all disks from assemblyLineIn; a disk is processed
   		* as follows:  if robotArm is not empty and the next disk does
   		* not fit on top of robotArm (which must be an inverted 
   		* pyramid) then robotArm is unloaded first; the disk from
   		* assemblyLineIn is added to robotArm; when all the disks
   		* have been retrieved from assemblyLineIn, robotArm is unloaded.
   		*  Precondition:  robotArm is empty;
   		*				assemblyLineOut is empty
   		**/
   	//Part C
   public void process()
   {
      while(!(assemblyLineIn.isEmpty()))
      {
         if(!(robotArm.isEmpty()))
         {
            if(assemblyLineIn.peek().compareTo(robotArm.peek()) >= 0)
            {
               robotArm.push(assemblyLineIn.remove());
            }
            else
            {
               unloadRobot();
            }
         }
         else
         {
            robotArm.push(assemblyLineIn.remove());
         }
         if(assemblyLineIn.isEmpty())
            unloadRobot();
      }
   }
      
   public void showInput()
   {
      System.out.println(assemblyLineIn);
   }
   public void showOutput()
   {
      System.out.println(assemblyLineOut);
   }
}
   //Disk is standard and straightforward.
class Disk implements Comparable<Disk>
{
   private int x;
   public Disk(int size)
   {
      x = size;
   } 
   public int getSize()
   {
      return x;   
   }
   public int compareTo(Disk y)
   {
      return x - y.getSize();
   }
   public String toString()
   {
      return x + "";
   }
}
   //Pyramid is sly.
class Pyramid extends Stack<Disk>
{


}
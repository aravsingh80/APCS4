 // Name: J1-24
 // Date: 3-6-2021

/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key); 
         if( index >= 0)    //found it
            System.out.println(key + " found  at index " + index);
         else
            System.out.println(key + " not found!");
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}

/*********************************************/
interface Hashtable
{
   void add(Object obj);          
   int indexOf(Object obj);
}
/***************************************************/ 

class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
  
   public HashtableLinearProbe(int size)//constructor
   {
      array = new Object[size];                   
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int linearProbe(int index)
   {      
      while(array[index] != null)
      {
         if(index + 1 >= array.length)
            break;
         index++;
      }
      return index;
   }
   
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a linear probe manner
         {
            while(!(array[index].equals(obj)))    
            {        
               index++;
               System.out.println("Looking at index " + index);
               if(index >= array.length)
                  return -1;
               else if(array[index] == null)
                  return -1;
            }
            return index;
         }
      }
      //not found
      return -1;
   }
}

/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant;  
   
   public HashtableRehash(int size) //constructor
   {
      array = new Object[size];
      constant = size;                       
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int rehash(int index)
   {
      int address = 2;
      while(address < constant - 1)
      {
         int hashaddress = address;
         int c = constant;
         while (hashaddress != 0 && c != 0)
         {
            if (c < hashaddress)
               hashaddress = hashaddress % c;
            else
               c = c % hashaddress;
         }
         if(Math.max(hashaddress, c) == 1)
            break;
         else
            address++;
      }
      while(array[index] != null)
      {
         if(array[(index + address) % constant] != null)
            index = (index + address) % constant;
         else
         {
            index = (index + address) % constant;
            break;
         }
      }
      return index;
   }
   
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            int address = 2;
            while(address < constant - 1)
            {
               int hashaddress = address;
               int c = constant;
               while (hashaddress != 0 && c != 0)
               {
                  if (c < hashaddress)
                     hashaddress = hashaddress % c;
                  else
                     c = c % hashaddress;
               }
               if(Math.max(hashaddress, c) == 1)
                  break;
               else
                  address++;
            }
            if(array[(index + address) % constant] != null)
               index = (index + address) % constant;
            else
            {
               index = (index + address) % constant;
               break;
            }
            System.out.println("Looking at index " + index);
         }
      }
      //not found
      return -1;
   }
}

/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      //instantiate the array
      //instantiate the LinkedLists
      array = new LinkedList[size];   
      for(int x = 0; x < size; x++)
         array[x] = new LinkedList<Object>();                  
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if(!array[index].isEmpty())
      {
         if(array[index].getFirst().equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            LinkedList<Object> temp = array[index];
            if(!(temp.contains(obj)))
               return -1;
            while(temp != null)
            {
               if(temp.getFirst().equals(obj))
                  return index;
               else
                  temp.removeFirst();
            }
         }
      }
      //not found
      return -1;
   }
}
// Name: J1-24
// Date: 11-30-2020

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode p = new ListNode("A", null);
      p.setNext(p);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner x = new Scanner(f);
      ListNode p = new ListNode(x.next(), null);
      p.setNext(p);
      for(int y = 0; y < n-1; y++)
      {
         p = insert(p, x.next());
      }
      return p;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      if(p == null)
      {
         p = new ListNode(obj, p);
         p.setNext(p);
         return p;
      }
      else
      {
         p = p.getNext();
         ListNode temp = p;
         while(temp.getNext() != p)
         {
            temp = temp.getNext();
         }
         temp.setNext(new ListNode(obj, p));
         return temp.getNext();
      }
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      print(p);
      while(p.getNext() != p)
      {
         p = remove(p, count);
         print(p);
      }
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode temp2 = p;
      int count2 = 0;
      while(temp2.getNext() != p)
      {
         count2++;
         temp2 = temp2.getNext();
      }
      for(int x = 0; x < count+1; x++)
      {
         p = p.getNext();
      }
      ListNode temp = null;
      for(int x = 0; x < count2; x++)
      {
         temp = insert(temp, p.getValue());
         p = p.getNext();
      }
      return temp;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode temp = p;
      temp = temp.getNext();
      while(temp != p)
      {
         System.out.print(temp.getValue() + " ");
         temp = temp.getNext();
      }
      System.out.print(temp.getValue() + " ");
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode temp = p;
      for(int x = 0; x < pos; x++)
      {
         temp = temp.getNext();
      }
      temp.setValue(obj);
   }
}
/**********************************************************
     ----jGRASP exec: java Josephus_Teacher
 A B C D
 How many names (2-20)? 5
 How many names to count off each time? 2
 1 2 3 4 5
 3 4 5 1
 5 1 3
 3 5
 3
 3 wins!
 
  ****  Now start all over. **** 
 
 Where should Josephus stand?  3
 Michael Hannah Josephus Ruth Matthew
 Josephus Ruth Matthew Michael
 Matthew Michael Josephus
 Josephus Matthew
 Josephus
 Josephus wins!
 
  ----jGRASP: operation complete.
  
  **************************************************/


//Updated on 12.14.2020 v2

//Name: J1-24   Date: 1-18-2021
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> customer = new LinkedList<Customer>();
      thisCustomersTime = 0;
      for(int x = 0; x < TIME; x++)
      {
         displayTimeAndQueue(customer, x);
         if(customer.size() >= longestQueue)
            longestQueue = customer.size();
         if(!(customer.isEmpty()))
         {
            of.println("       " + customer.peek() + " is now being served for " + thisCustomersTime + " minutes.");
            thisCustomersTime--;
         }
         if(Math.random() <= CHANCE_OF_CUSTOMER)
         {
            if(!(x + 1 == TIME))
            {
               customers++;
               customer.add(new Customer(x+1, timeToOrderAndBeServed()));
               if(customer.size() == 1)
                  thisCustomersTime = customer.peek().getOrderTime();
            }
         }
         if(!(customer.isEmpty()))
         {
            if(thisCustomersTime == 0)
            {
               int oneMin = x - customer.peek().getArrive() + 1;
               totalMinutes = totalMinutes + oneMin;
               if(oneMin >= longestWaitTime)
                  longestWaitTime = oneMin;
               customer.remove();
               if(!(customer.isEmpty()))
                  thisCustomersTime = customer.peek().getOrderTime();
            }
         }
      }
      while(!(customer.isEmpty()))
      {
         int oneMin = TIME - customer.peek().getArrive() + 1;
         totalMinutes = totalMinutes + oneMin;
         if(oneMin >= longestWaitTime)
            longestWaitTime = oneMin;
         if(thisCustomersTime == 0)
            thisCustomersTime = customer.peek().getOrderTime();
         while(thisCustomersTime > 0)
         {
            of.println("       " + customer.peek() + " is now being served for " + thisCustomersTime + " minutes.");
            thisCustomersTime--;
         }
         customer.remove();
      }
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      public Customer(int arr, int time)
      {
         arrivedAt = arr;
         orderAndBeServed = time;
      }
      public int getArrive()
      {
         return arrivedAt;
      }
      public int getOrderTime()
      {
         return orderAndBeServed;
      }
      public String toString()
      {
         return "" + arrivedAt;
      }
   }
}
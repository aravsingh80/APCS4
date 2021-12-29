import java.util.*;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      addToLList("manager", head);
      addToLList("boy", head);
      addToLList("girl", head);
      addToLList("anyone", head);
      addToLList("place", head);
      addToLList("vector", head);
   
      print(head);
   }
   public static void addToLList(Comparable obj, ListNode front){
      front = addHelper(front, obj);
   }
   private static ListNode addHelper(ListNode list, Comparable obj){
      if (list == null || obj.compareTo(list.getValue()) == 0){
         list = new ListNode(obj, list);
         return list;
      }
      else{
         list.setNext(addHelper(list.getNext(), obj));
         return list;
      }
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
}
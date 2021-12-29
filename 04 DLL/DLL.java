// Name: J1-24
// Date: 12-1-2020

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else
      {
         DLNode temp = head;
         for(int x = 0; x < index; x++)
            temp = temp.getNext();
         DLNode temp2 = temp.getNext();
         DLNode temp3 = new DLNode(obj, temp, temp2);
         temp.setNext(temp3);
         temp2.setPrev(temp3);
         size++;
      }      
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else
      {
         DLNode temp = head.getNext();
         for(int x = 0; x < index; x++)
            temp = temp.getNext();
         return temp.getValue();
      }
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else
      {
         DLNode temp = head.getNext();
         for(int x = 0; x < index; x++)
            temp = temp.getNext();
         Object x = temp.getValue();
         temp.setValue(obj);
         return x;
      }
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else
      {
         DLNode temp = head;
         for(int x = 0; x < index; x++)
            temp = temp.getNext();
         Object x = temp.getNext().getValue();
         DLNode temp2 = temp.getNext().getNext();
         temp.setNext(temp2);
         temp2.setPrev(temp);
         size--;
         return x;
      }
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      DLNode temp = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(temp);
      head.setNext(temp);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode temp = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(temp);
      head.setPrev(temp);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext() == null)
         return null;
      else
      {
         Object x = head.getNext().getValue();
         head.setNext(head.getNext().getNext());
         head.getNext().setPrev(head);
         size--;
         return x;
      }
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getNext() == null)
         return null;
      else
      {
         Object x = head.getPrev().getValue();
         head.setPrev(head.getPrev().getPrev());
         head.getPrev().setNext(head);
         size--;
         return x;
      }
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String list = "[";
      DLNode temp = head.getNext();
      for(int x = 0; x < size; x++)
      {
         if(x == (size - 1))
            list = list + "" + temp.getValue() + "]";
         else
            list = list + "" + temp.getValue() + ", ";
         temp = temp.getNext();
      } 
      return list;
   }
}
// Name: J1-24
// Date: 1-7-2021

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 5 + *");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Queue<String> post = new LinkedList<String>();
      while(!(postfixParts.isEmpty()))
      {
         post.add(postfixParts.get(0));
         postfixParts.remove(0);
      }
      Stack<String> post2 = new Stack<String>();
      while(!(post.isEmpty()))
      {
         String ch = post.peek();
         if(isOperator(ch))
         {
            if(ch.equals("!"))
            {
               post.remove();
               double a = Double.parseDouble(post2.pop());
               double b = 1;
               for(int y = 1; y <= a; y++)
               {
                  b = b * y;
               }
               post2.push("" + b);
            }
            else
            {
               post.remove();
               double a = Double.parseDouble(post2.pop());
               double b = Double.parseDouble(post2.pop());
               post2.push("" + eval(b, a, ch));
            }
         }
         else
         {
            post.remove();
            post2.push(ch);
         }
      }
      return Double.parseDouble(post2.peek());
   }
   
   public static double eval(double a, double b, String ch)
   {
      double x = 0;
      switch(ch)
      {
         case("*"): 
            x = a * b;
            break;
         case("+"): 
            x = a + b;
            break;
         case("-"): 
            x = a - b;
            break;
         case("/"): 
            x = a / b;
            break;
         case("%"): 
            x = a % b;
            break;
         case("^"): 
            x = Math.pow(a, b);
            break;
      }
      return x;
   }
   
   public static boolean isOperator(String op)
   {
      if(operators.contains(op))
         return true;
      else
         return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/
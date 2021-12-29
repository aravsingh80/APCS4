// Name: J1-24
// Date: 1-12-2021
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("3 * 4 + 5 / 2 - 5");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
         
         
         
         
         
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      String result = "";
      Stack<String> res = new Stack<String>();
      while(!(nums.isEmpty()))
      {
         if(LEFT.contains(nums.get(0)))
            res.push(nums.remove(0));
         else if(operators.contains(nums.get(0)))
         {
            if(res.isEmpty() || isLower(res.peek().charAt(0), nums.get(0).charAt(0)) || LEFT.contains(res.peek()))
               res.push(nums.remove(0));
            else
            {
               while(!(res.isEmpty() || isLower(res.peek().charAt(0), nums.get(0).charAt(0)) ||  LEFT.contains(res.peek())))
               {
                  if(!(LEFT.contains(res.peek())))
                     result = result + res.pop() + " ";
               }
            }
         }
         else if(RIGHT.contains(nums.get(0)))
         {
            while(!(LEFT.contains(res.peek())))
               result = result + res.pop() + " ";
            res.pop();
            nums.remove(0);
         }
         else
            result = result + nums.remove(0) + " ";
      }
      while(!(res.isEmpty()))
      {
         result = result + res.pop() + " ";
      }
      return result.substring(0, result.length() - 1);
   }
   
   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if((c1 == '+' || c1 == '-') && (c2 == '*' || c2 == '/' || c2 == '%'))
         return true;
      else if((c1 == '+' || c1 == '-') && (c2 == '^' || c2 == '!'))
         return true;
      else if((c1 == '*' || c1 == '/' || c1 == '%') && (c2 == '^' || c2 == '!'))
         return true;
      else
         return false;
   }
}


/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * 4 + 5			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0
     
***********************************************/

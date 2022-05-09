package src.stringnumtest;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * @author Dreihunde
 *
 */
public class EvalRPN {
	//method 1 栈  O(n) O(C)
	 public int evalRPN(String[] tokens) {
	        ArrayDeque<Integer> stack = new ArrayDeque<>();
	        Set<String> set = new HashSet<>();
	        set.add("+");
	        set.add("-");
	        set.add("*");
	        set.add("/");
	        for (String token : tokens) {
	            if (set.contains(token)) {
	                int temp = compute(stack.pop(), stack.pop(), token);
	                stack.push(temp);
	            } else {
	                stack.push(Integer.parseInt(token));
	            }
	        }
	        return stack.pop();
	    }

	 private int compute(int num1, int num2, String token) {
	        if (token.equals("+")) {
	            return num2 + num1;
	        } else if (token.equals("-")){
	            return num2 - num1;
	        } else if (token.equals("*")){
	            return  num2 * num1;
	        } else {
	            return num2 / num1;
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tokens = new String[] {"4","13","5","/","+"};
		EvalRPN er = new EvalRPN();
		System.out.println(er.evalRPN(tokens));

	}

}

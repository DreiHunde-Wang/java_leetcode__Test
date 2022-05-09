package src.stringnumtest;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * ���� �沨����ʾ������ú�׺���ʽ�ļ�������
 * ��Ч��������� +��-��*��/ ��ÿ��������������������Ҳ��������һ���沨�����ʽ��
 * @author Dreihunde
 *
 */
public class EvalRPN {
	//method 1 ջ  O(n) O(C)
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

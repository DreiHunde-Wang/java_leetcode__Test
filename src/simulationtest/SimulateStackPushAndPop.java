package src.simulationtest;

import java.util.ArrayDeque;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列
 * @author Dreihunde
 *
 */
public class SimulateStackPushAndPop {
	
	//method 1 模拟出入栈
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();

    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] pushed = new int[] {1, 2, 3, 4, 5};
		int[] poped = new int[] {4, 5, 3, 2, 1};
		SimulateStackPushAndPop ss = new SimulateStackPushAndPop();
		System.out.println(ss.validateStackSequences(pushed, poped));
	}

}

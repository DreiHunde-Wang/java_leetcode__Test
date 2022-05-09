package src.simulationtest;

import java.util.ArrayDeque;

/**
 * ���������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳�򡣼���ѹ��ջ���������־�����ȡ�
 * ���磬���� {1,2,3,4,5} ��ĳջ��ѹջ���У����� {4,5,3,2,1} �Ǹ�ѹջ���ж�Ӧ��һ���������У�
 * �� {4,3,5,1,2} �Ͳ������Ǹ�ѹջ���еĵ�������
 * @author Dreihunde
 *
 */
public class SimulateStackPushAndPop {
	
	//method 1 ģ�����ջ
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

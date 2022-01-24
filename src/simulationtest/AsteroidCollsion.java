package simulationtest;

import java.util.ArrayDeque;

/**
 * ����һ���������� asteroids����ʾ��ͬһ�е�С���ǡ�
 * ���������е�ÿһ��Ԫ�أ������ֵ��ʾС���ǵĴ�С��������ʾС���ǵ��ƶ���������ʾ�����ƶ�������ʾ�����ƶ�����
 * ÿһ��С��������ͬ���ٶ��ƶ���
 * �ҳ���ײ��ʣ�µ�����С���ǡ���ײ�������������໥��ײ����С�����ǻᱬը������������Ǵ�С��ͬ�����������Ƕ��ᱬը��
 * �����ƶ�������ͬ�����ǣ���Զ���ᷢ����ײ��
 * @author Dreihunde
 *
 */
public class AsteroidCollsion {
	//method 1 ջ O(n) O(n)
    public int[] asteroidCollision1(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int asteroid: asteroids) {
            while (!stack.isEmpty() && !isSafe(stack.peek(), asteroid) && Math.abs(asteroid) > stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty() || isSafe(stack.peek(), asteroid)) {
                stack.push(asteroid);
            } 
            if (stack.peek() + asteroid == 0)  {
                stack.pop();
            } 

        }
        int[] ans = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[index--] = stack.pop();
        }
        return ans;
    }

    private boolean isSafe(int a, int b) {
        return a * b > 0 || (a < 0 && b > 0);
    }
    //method 2 ջ + �Ż��߼� O(n) O(n)
    public int[] asteroidCollision2(int[] asteroids) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean isDestory = false;
        for (int ast : asteroids) {
            while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                if (stack.peek() < -ast) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -ast){
                    stack.pop();
                }
                isDestory = true;
                break;
            }
            if (!isDestory) {
                stack.push(ast);
            } else {
                isDestory = false;
            }
                
        }

        int[] rev = new int[stack.size()];
        for (int i = rev.length - 1; i >= 0; i--) {
            rev[i] = stack.pop();
        }
        return rev;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

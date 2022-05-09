package src.numsorttest;

import java.util.ArrayDeque;

/**
 * ����һ���������� asteroids����ʾ��ͬһ�е����ǡ�
 * ���������е�ÿһ��Ԫ�أ������ֵ��ʾ���ǵĴ�С��������ʾ���ǵ��ƶ���������ʾ�����ƶ�������ʾ�����ƶ�����ÿһ����������ͬ���ٶ��ƶ���
 * �ҳ���ײ��ʣ�µ��������ǡ���ײ�������������໥��ײ����С�����ǻᱬը��
 * ����������Ǵ�С��ͬ�����������Ƕ��ᱬը�������ƶ�������ͬ�����ǣ���Զ���ᷢ����ײ��
 * @author Dreihunde
 *
 */
public class PlantContrast {
	//method 1 ����ջƥ��ǰ��Ԫ��
	public int[] asteroidCollision(int[] asteroids) {

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
		int[] asteroids = new int[] {-2, 1, -1, -2};
		PlantContrast pc = new PlantContrast();
		
		long startTime = System.nanoTime();
		CommonTest.printNum(pc.asteroidCollision(asteroids));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

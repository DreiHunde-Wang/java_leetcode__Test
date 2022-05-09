package src.numsorttest;

import java.util.ArrayDeque;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * @author Dreihunde
 *
 */
public class PlantContrast {
	//method 1 出入栈匹配前后元素
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

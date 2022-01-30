package numsorttest;

import java.util.ArrayDeque;

/**
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，
 * 要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * https://leetcode-cn.com/problems/iIQa4I/
 * @author Dreihunde
 *
 */
public class DaliyTemperatures {
	//method 1 栈 O(n) O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        //栈储存 new int[]{temperatures[i], i}
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{temperatures[0], 0});
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int index = stack.pop()[1];
                ans[index] = i - index;
            }
            stack.push(new int[]{temperatures[i], i});
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

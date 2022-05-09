package src.dptest;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的�?少数目，其中，每个斐波那契数字都可以被使用多次�??
 * 斐波那契数字定义为：
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 �? 其中 n > 2 �?
 * 数据保证对于给定�? k ，一定能找到可行解�??
 * 链接：https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k
 * @author Dreihunde
 *
 */
public class FindMinFibonacciNumbers {
	//method 1 每次选取小于k的斐波那契数中最大的�?�? O(logn) O(logn)
    public int findMinFibonacciNumbers(int k) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int a = 1, b = 1;
        while (a + b <= k) {
            int c = a + b;
            list.add(c);
            a = b;
            b = c;
        }
        int count = 0;
        int n = list.size();
        for (int i = n - 1; i >= 0; i--) {
            int t = list.get(i);
            if (t <= k) {
                count++;
                k -= t;
                if (k == 0) {
                    break;
                }
            }

        }
        return count;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

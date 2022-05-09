package src.mathtest;

/**
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 * @author Dreihunde
 *
 */
public class CountNumbersWithUniqueDigits {

	int count;
    //method 1 dfs (10^n) O(1)
    public int countNumbersWithUniqueDigits(int n) {   
        int[] mask = new int[10];
        dfs(mask, 0, n);
        return count;
    }

    private void dfs(int[] mask, int cur, int n) {
        if (cur < n) {
            count++;
        }else if (cur == n) {
            count++;
            return;
        }
        for (int i = 0; i < mask.length; i++) {
            if (cur == 0 && i == 0) {
                continue;
            }
            if (mask[i] == 0) {
                mask[i] = 1;
                dfs(mask, cur + 1, n);
                mask[i] = 0;
            }
        }
    }

    //method 2 math O(n) O(1)
    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }
}

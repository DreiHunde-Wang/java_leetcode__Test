package simulationtest;

import java.util.Arrays;

/**
 * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i​​​​​​​​​​​​ 位客户在第 j 家银行托管的资产数量。
 * 返回最富有客户所拥有的 资产总量 。
 * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
 * 链接：https://leetcode-cn.com/problems/richest-customer-wealth
 * @author Dreihunde
 *
 */
public class MaximumWealth {
	//method 1 模拟 O(mn) O(1)
    public int maximumWealth1(int[][] accounts) {
        int m = accounts.length;
        int n = accounts[0].length;
        int wealth = 0;
        int maxWealth = 0;
        for (int i = 0; i < m; i++) {
            wealth = 0;
            for (int j = 0; j < n; j++) {
                wealth += accounts[i][j];
            }
            maxWealth = Math.max(wealth, maxWealth);
        }

        return maxWealth;
    }

    //method 2 stream O(mn) O(1)
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        for (int[] account : accounts) {
            maxWealth = Math.max(maxWealth, Arrays.stream(account).sum());
        }

        return maxWealth;
    }

}

package src.dptest;

import java.util.Arrays;

/**
 * 给你�?个整数数�? coins 表示不同面额的硬币，另给�?个整�? amount 表示总金额�??
 * 请你计算并返回可以凑成�?�金额的硬币组合数�?�如果任何硬币组合都无法凑出总金额，返回 0 �?
 * 假设每一种面额的硬币有无限个。�?
 * 题目数据保证结果符合 32 位带符号整数�?
 * @author Dreihunde
 *
 */
public class ChangeCoins {
	//method 1 递归(超时) O(amount * n) O(amount)
    public int change1(int amount, int[] coins) {
        Arrays.sort(coins);
        return dfs(amount, coins, 0);
    }

    private int dfs(int cur, int[] coins, int pre) {
        if (cur == 0)
            return 1;
        int sum = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > cur)
                break;
            if (coins[i] < pre)
                continue;
            sum += dfs(cur - coins[i], coins, coins[i]);
        }
        return sum;
    }
    //method 2 dp O(amount * n) O(amount)
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int amount = 100;
		int[] coins = new int[] {99, 1};
		ChangeCoins cc = new ChangeCoins();
		
		long startTime=System.nanoTime(); 
		System.out.println(cc.change1(amount, coins));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(cc.change2(amount, coins));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

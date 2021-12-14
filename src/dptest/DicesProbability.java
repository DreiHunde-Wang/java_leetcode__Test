package dptest;

import java.util.Arrays;

import numsorttest.CommonTest;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * @author Dreihunde
 *
 */
public class DicesProbability {
	//method 1 dp O(n) O(n)
    public double[] dicesProbability(int n) {
        //第一个骰子只有6个状态
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        //从第二个骰子开始
        for (int i = 2; i <= n; i++) {
            //新的取值范围
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            //更新基准
            dp = temp;
        }
        return dp;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		DicesProbability dp = new DicesProbability();
		CommonTest.printNum(dp.dicesProbability(n));

	}

}

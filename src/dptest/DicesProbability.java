package dptest;

import java.util.Arrays;

import numsorttest.CommonTest;

/**
 * ��n���������ڵ��ϣ��������ӳ���һ��ĵ���֮��Ϊs������n����ӡ��s�����п��ܵ�ֵ���ֵĸ��ʡ�
 * ����Ҫ��һ�����������鷵�ش𰸣����е� i ��Ԫ�ش����� n ���������������ĵ��������е� i С���Ǹ��ĸ��ʡ�
 * @author Dreihunde
 *
 */
public class DicesProbability {
	//method 1 dp O(n) O(n)
    public double[] dicesProbability(int n) {
        //��һ������ֻ��6��״̬
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        //�ӵڶ������ӿ�ʼ
        for (int i = 2; i <= n; i++) {
            //�µ�ȡֵ��Χ
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            //���»�׼
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

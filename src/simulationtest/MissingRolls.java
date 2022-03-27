package simulationtest;

import java.util.Arrays;

/**
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。
 * 幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。
 * 如果不存在答案，返回一个空数组。
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 * 链接：https://leetcode-cn.com/problems/find-missing-observations
 * @author Dreihunde
 *
 */
public class MissingRolls {//method 1 模拟 O(n) O(1)
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        //总共的观测值
        int total = mean * (n + m);
        //n次丢失的观测值总和
        for (int roll : rolls) {
            total -= roll;
        }
        //观测值起码是1
        total -= n;
        //如果全填1都超了或者全填6都满足不了，返回空
        if (total < 0 || total > n * 5) {
            return new int[0];
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        if (total == 0) {
            return ans;
        }
        //优先填6，没有不填
        for (int i = 0; i < n; i++) {
            if(total >= 5) {
                ans[i] += 5;
                total -= 5;
            } else {
                ans[i] += total;
                break;
            }
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package dptest;

/**
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。
 * 如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * 链接：https://leetcode-cn.com/problems/the-number-of-good-subsets
 * @author Dreihunde
 *
 */
public class NumberOfGoodSubSets {
	//method 1 状态压缩dp O(n + C*2^(P)) O(2^(P)) C为数字范围 P为质数个数
    int MOD = (int)1e9+7;
    int[] p = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    int[] cnts = new int[35];
    public int numberOfGoodSubsets(int[] nums) {
        int n = nums.length;
        for (int i : nums) cnts[i]++;
        int mask = 1 << 10;
        //f[5]  5 二进制位 0000000101, 表示子集所有元素乘积为 f[0] * f[2] = 2 * 5 的个数
        // 动态转移方式为在已有的情况下再增加其他质数  (f[0] * f[2]) * f[2] = (2 * 5) * 3
        // 注意这里 dp 用 int 会溢出
        long[] f = new long[mask];
        f[0] = 1;
        for (int i = 2; i <= 30; i++) {
            if (cnts[i] == 0) continue;
            // 对 i 进行试除
            int cur = 0, x = i;
            boolean ok = true;
            for (int j = 0; j < 10; j++) {
                int t = p[j], c = 0;
                while (x % t == 0) {
                    cur |= (1 << j);
                    x /= t; c++;
                }
                // 如果 i 能够被同一质数试除多次，说明 i 不能加到子集，跳过
                if (c > 1) { 
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;
            // 枚举前一状态 prev
            //（确保考虑一个新数值 i 时，依赖的子集 prev 存储的为尚未考虑 i 的方案数）
            for (int prev = mask - 1; prev >= 0; prev--) {
                // 只有当前选择数与前一状态不冲突，则能够进行转移，将方案数进行累加
                if ((prev & cur) != 0) continue;
                f[prev | cur] = (f[prev | cur] + f[prev] * cnts[i]) % MOD;
            }
        }
        long ans = 0;
        // 统计所有非空集的方案数
        for (int i = 1; i < mask; i++) ans = (ans + f[i]) % MOD;
        // 在此基础上，考虑每个 1 选择与否对答案的影响
        for (int i = 0; i < cnts[1]; i++) ans = ans * 2 % MOD;
        return (int) ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

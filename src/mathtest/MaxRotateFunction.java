package src.mathtest;

/**
 * 给定�?个长度为 n 的整数数组 nums �?
 * 假设 arrk 是数组 nums 顺时针旋�? k 个位置后的数组，我们定义 nums �? 旋转函数  F 为：
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的�?大�?� �??
 * 生成的测试用例让答案符合 32 �? 整数�?
 * 链接：https://leetcode-cn.com/problems/rotate-function
 * @author Dreihunde
 *
 */
public class MaxRotateFunction {
	//method 1 遍历 O(n^2) O(1) 超时
    public int maxRotateFunction1(int[] nums) {
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int count = getCount(nums, i);
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    public int getCount(int[] nums, int i) {
        int n = nums.length;
        int count = 0;
        for (int j = 0; j < n; j++) {
            count += j * nums[(j + i) % n];
        }
        return count;
    }

    //method 2 递推公式 O(n) O(1) F(k)=F(k�?1)+numSum−n×nums[n−k]
    public int maxRotateFunction(int[] nums) {
        // int numSum = Arrays.stream(nums).sum();
        int numSum = 0;
        int f = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
            numSum += nums[i];
        }
        int res = f;
        for (int i = 1; i < n; i++) {
            f =f + numSum - n * nums[n - i];
            res = Math.max(res, f);
        }
        return res;

    }

}

package binaryandtest;

/**
 * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 * 计算置位位数 就是二进制表示中 1 的个数。
 * 例如， 21 的二进制表示 10101 有 3 个计算置位。
 * 链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation
 * @author Dreihunde
 *
 */
public class CountPrimeSetBits {
	//method 1 暴力  O(nlog(right)) O(C)
    public int countPrimeSetBits(int left, int right) {
        int[] pairs = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23};
        int[] mask = new int[23];
        for (int pair : pairs) {
            mask[pair - 1] = 1;
        }
        int count = 0;
        for (int i = left; i <= right; i++) {
            // if (isPair(i, mask)) {
            //     count++;
            // }
            if (mask[Integer.bitCount(i) - 1] == 1) {
                count++;
            }
        }
        return count;
    }

    boolean isPair(int i, int[] mask) {
        int x = i;
        int count = 0;
        while (x > 0) {
            if ((x & 1) == 1) {
                count++;
            }
            x >>= 1;
        }
        return mask[count - 1] == 1;
    }

    //method 2 优化  O(n) O(1)
    public int countPrimeSetBits2(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; ++x) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }

}

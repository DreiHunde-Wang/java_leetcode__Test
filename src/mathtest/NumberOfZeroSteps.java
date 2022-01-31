package mathtest;

/**
 * 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
 * https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * @author Dreihunde
 *
 */
public class NumberOfZeroSteps {
	//method 1 模拟 O(logn) O(1)
    public int numberOfSteps1(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                num = num - 1;
            } else {
                num = num / 2;
            }
            count++;
        }
        return count;
    }

    //method 2 math 总操作次数=num二进制1的个数+1的最高位 O(logW) W是位数 O(1)
    public int numberOfSteps(int num) {
        return num == 0 ? 0 : length(num) - 1 + count(num);
    }

    public int length(int num) {
        int clz = 0;
        if ((num >> 16) == 0) {
            clz += 16;
            num <<= 16;
        }
        if ((num >> 24) == 0) {
            clz += 8;
            num <<= 8;
        }
        if ((num >> 28) == 0) {
            clz += 4;
            num <<= 4;
        }
        if ((num >> 30) == 0) {
            clz += 2;
            num <<= 2;
        }
        if ((num >> 31) == 0) {
            clz += 1;
        }
        return 32 - clz;
    }

    public int count(int num) { 
        num = (num & 0x55555555) + ((num >> 1) & 0x55555555);
        num = (num & 0x33333333) + ((num >> 2) & 0x33333333);
        num = (num & 0x0F0F0F0F) + ((num >> 4) & 0x0F0F0F0F);
        num = (num & 0x00FF00FF) + ((num >> 8) & 0x00FF00FF);
        num = (num & 0x0000FFFF) + ((num >> 16) & 0x0000FFFF);
        return num;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

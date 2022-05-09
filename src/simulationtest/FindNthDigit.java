package src.simulationtest;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * @author Dreihunde
 *
 */
public class FindNthDigit {
	//method 1 模拟 O(logn) O(logn)
    public int findNthDigit(int n) {
        int digit = 1; //数位
        long start = 1; //起始值
        long count = 9;//数位数量

        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * start * digit;
        }

        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package src.mathtest;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * @author Dreihunde
 *
 */
public class FindKthDigit {
	
	//method 1 数学
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }
        long start = 1;
        int digit = 1;
        long count = 9;
        
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * digit * start;
        }

        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 19;
		FindKthDigit dk = new FindKthDigit();
		System.out.println(dk.findNthDigit(n));
	}

}

package mathtest;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * 返回这个结果。
 * https://leetcode-cn.com/problems/add-digits/
 * @author Dreihunde
 *
 */
public class AddDigits {
	//method 1 模拟 O(logn) O(1)
    public int addDigits1(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    //method 2 math O(1) O(1)
    //https://leetcode-cn.com/problems/add-digits/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-7/
    public int addDigits2(int num) {
        
        return (num - 1) % 9 + 1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

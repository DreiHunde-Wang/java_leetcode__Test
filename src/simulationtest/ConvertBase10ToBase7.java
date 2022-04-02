package simulationtest;

/**
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * https://leetcode-cn.com/problems/base-7/
 * @author Dreihunde
 *
 */
public class ConvertBase10ToBase7 {
	//method 1 正序 O(lognum) O(lognum)
    public String convertToBase7(int num) {
        StringBuffer sb = new StringBuffer();
        if (num < 0) {
            num = -num;
            sb.append("-");
        }
        if (num == 0) {
            return "0";
        }
        int div = (int) Math.pow(7, 10);
        boolean isFirst = true;
        while (num > 0) {
            if (num < div) {
                if (!isFirst) {
                    sb.append("0");
                } 
                div /= 7;
                continue;
            }
            isFirst = false;
            int n = num / div;
            num -= n * div;
            div /= 7;
            sb.append(Integer.toString(n));
        }
        while (div > 0) {
            sb.append("0");
            div /= 7;
        }
        return sb.toString();
    }

    //method 2 逆序 O(lognum) O(lognum)
    public String convertToBase72(int num) {
        StringBuffer sb = new StringBuffer();
        boolean negative = false;
        if (num < 0) {
            num = -num;
            negative = true;
        }
        if (num == 0) {
            return "0";
        }
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        
        if (negative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

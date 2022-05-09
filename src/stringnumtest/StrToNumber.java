package src.stringnumtest;

/**
 * 写一个函�? StrToInt，实现把字符串转换成整数这个功能。不能使�? atoi 或�?�其他类似的库函数�??
 * 首先，该函数会根据需要丢弃无用的�?头空格字符，直到寻找到第�?个非空格的字符为止�??
 * 当我们寻找到的第�?个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数�??
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响�?
 * 注意：假如该字符串中的第�?个非空格字符不是�?个有效整数字符�?�字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换�??
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0�?
 * 说明�?
 * 假设我们的环境只能存�? 32 位大小的有符号整数，那么其数值范围为 [�?231,  231 �? 1]�?
 * 如果数�?�超过这个范围，请返�?  INT_MAX (231 �? 1) 或 INT_MIN (�?231) �?
 * @author Dreihunde
 *
 */
public class StrToNumber {
	//method 1 遍历 O(n) O(1)
    public int strToInt(String str) {
        // str = str.trim();
        if (str.length() == 0 || str == null)
            return 0;
        boolean isPositive = true;
        int index = 0;
        while (str.charAt(index) == ' ') {
            index++;
            if (index == str.length())
                return 0;
        }
        if (str.charAt(index) == '-') {
            isPositive = false;
            index++;
        } else if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
            return constructInt(str, index, isPositive);
        } else {
            return 0;
        }

        return constructInt(str, index, isPositive);

    }

    private int constructInt(String str, int index, boolean isPositive) {
        long ans = 0;
        while (index < str.length()) {
            if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                    ans = ans * 10 + str.charAt(index) - '0';
                    index++;
                    if (ans > Integer.MAX_VALUE)
                        break;
            } else {
                break;
            }
        }

        if (isPositive) {
            return ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) ans;
        } else {
            ans = 0 - ans;
            return ans < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) ans;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String s = "9223372036854775808";
		String s = "-2147483648";
		StrToNumber st = new StrToNumber();
		
		System.out.println(st.strToInt(s));

	}

}

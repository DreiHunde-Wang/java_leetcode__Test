package stringnumtest;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 数值（按顺序）可以分成以下几个部分：
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * @author Dreihunde
 *
 */
public class StrIsNumber {
	//method 1 条件判断
    public boolean isNumber1(String s) {
        s = s.trim();
        boolean isNum = false;
        boolean ise = false;
        boolean isDot = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                isNum = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')
                    return false;
                isNum = false;
            } else if (s.charAt(i) == '.') {
                if (isDot || ise)
                    return false;
                isDot = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (ise || !isNum)
                    return false;
                ise = true;
                isNum = false;
            } else {
                return false;
            }
        }
        return isNum;
    }
    
    //method 2 差分判断
    public boolean isNumber2(String s) {
        s = s.trim();
        if (s.length() == 0)
            return false;
        int index = 0;
        if (index < s.length() && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
        	index++;
        }
        int start = index;
        index = scanUnsignedInteger(s, index);
        boolean isNum = index > start;

        if (index < s.length() && s.charAt(index) == '.') {
            index++;
            start = index;
            index = scanUnsignedInteger(s, index);
            isNum = (index > start)|| isNum;
        } 
        if (index < s.length() && (s.charAt(index) == 'e' || s.charAt(index) == 'E')) {
            index++;
            if (index < s.length() && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            	index++;
            }
            start = index;
            index = scanUnsignedInteger(s, index);
            isNum = isNum && (index > start);
        }

        return isNum && index == s.length();

    }

    private int scanUnsignedInteger(String s, int index) {
        while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            index++;
        }
        return index;
    }
    
    //method 3 面向答案编程
    public boolean isNumber3(String s) {
    	if (s.length() == 0 || s == null) {
    		return false;
    	}
    	s = s.trim();
    	try {
    		double a = Double.parseDouble(s);
    				
    	} catch (Exception e) {
			// TODO: handle exception
    		return false;
		}
    	char c = s.charAt(s.length() - 1);
    	return (c >= '0' && c <= '9') || c == '.';
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "1.";
		
		StrIsNumber si = new StrIsNumber();
		
		long startTime = System.nanoTime();
		System.out.println(si.isNumber1(s));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(si.isNumber2(s));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(si.isNumber3(s));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		

	}

}

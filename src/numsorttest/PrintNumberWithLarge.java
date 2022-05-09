package src.numsorttest;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * @author Dreihunde
 *
 */
public class PrintNumberWithLarge {
	//method 1 遍历(未考虑大数越界问题) O(10^n) O(1)
    public int[] printNumbers1(int n) {
        int length = (int) Math.pow(10, n) - 1;
        int[] rev = new int[length];
        for (int i = 1; i <= rev.length; i++) {
            rev[i - 1] = i;
        }

        return rev;
    }

    //method 2 dfs 考虑大数边界问题
    StringBuffer sb;
    char[] num;
    char[] partern = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int n;
    public int[] printNumbers2(int n) {
        this.n = n;
        sb = new StringBuffer();
        num = new char[n];
        dfs(0);
        sb.deleteCharAt(sb.length() - 1);
        String[] strs = sb.toString().split(",");
        int[] rev = new int[(int) Math.pow(10,  n) - 1];
        for (int i = 0; i < rev.length; i++) {
        	rev[i] = Integer.valueOf(strs[i]);
        }
        return rev;
    }

    private void dfs(int x) {
        if (x == n) {
        	String temp = deletePreZero(String.valueOf(num));
        	if (!temp.equals(""))
        		sb.append(temp + ",");
            return;
        }
        for (char p : partern) {
            num[x] = p;
            dfs(x + 1);
        }
    }
    
    private String deletePreZero(String s) {
    	int i = 0;
    	while (i < s.length() && s.charAt(i) == '0') {
    		i++;
    	}
    	return s.substring(i);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintNumberWithLarge pn = new PrintNumberWithLarge();
		int n = 3;
		CommonTest.printNum(pn.printNumbers1(n));
		CommonTest.printNum(pn.printNumbers2(n));

	}

}

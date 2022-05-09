package src.numsorttest;

/**
 * �������� n����˳���ӡ���� 1 ������ n λʮ���������������� 3�����ӡ�� 1��2��3 һֱ������ 3 λ�� 999��
 * @author Dreihunde
 *
 */
public class PrintNumberWithLarge {
	//method 1 ����(δ���Ǵ���Խ������) O(10^n) O(1)
    public int[] printNumbers1(int n) {
        int length = (int) Math.pow(10, n) - 1;
        int[] rev = new int[length];
        for (int i = 1; i <= rev.length; i++) {
            rev[i - 1] = i;
        }

        return rev;
    }

    //method 2 dfs ���Ǵ����߽�����
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

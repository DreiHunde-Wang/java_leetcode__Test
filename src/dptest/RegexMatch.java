package src.dptest;

/**
 * ��ʵ��һ����������ƥ�����'. '��'*'��������ʽ��
 * ģʽ�е��ַ�'.'��ʾ����һ���ַ�����'*'��ʾ��ǰ����ַ����Գ�������Σ���0�Σ���
 * �ڱ����У�ƥ����ָ�ַ����������ַ�ƥ������ģʽ��
 * ���磬�ַ���"aaa"��ģʽ"a.a"��"ab*ac*a"ƥ�䣬����"aa.a"��"ab*a"����ƥ�䡣
 * @author Dreihunde
 *
 */
public class RegexMatch {
	//method 1 dp O(mn) O(mn)
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //ǰm���ַ���ǰn���ַ��Ƿ�ƥ��
        boolean[][] f = new boolean[m + 1][n + 1];
        //����s��p��Ϊ���ַ�ʱ��״̬
        f[0][0] = true;
        //i s�ĵ�i����ĸ j p�ĵ�j����ĸ
        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                //���p�ĵ�j����ĸΪ*
                if (p.charAt(j - 1) == '*') {
                    //����������ĸ(*����ǰ�����ĸ����Ϊ0��)
                    f[i][j] = f[i][j - 2];
                    //���s�ĵ�i����ĸ��p�ĵ�j-1����ĸ��ͬ����ôѡ��һ�ֿ��Ե�ƥ��ģʽ
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                //���򰴶�Ӧ�ַ�ƥ��
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        //ƥ����s��m����ĸ��p��n����ĸ
        return f[m][n];

    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.')
            return true;

        return s.charAt(i - 1) == p.charAt(j - 1);

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "mississippi";
		String p = "mis*is*p*.";
		
		RegexMatch rm = new RegexMatch();
		System.out.println(rm.isMatch(s, p));
	}

}

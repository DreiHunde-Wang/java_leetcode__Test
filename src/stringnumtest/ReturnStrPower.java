package src.stringnumtest;

import src.numsorttest.CommonTest;

/**
 * ����һ���ַ��� s ���ַ����ġ�����������Ϊ��ֻ����һ���ַ�����ǿ����ַ����ĳ��ȡ�
 * ���㷵���ַ�����������
 * @author Dreihunde
 *
 */
public class ReturnStrPower {
	//method 1 ���� O(n) O(1)
    public int maxPower1(String s) {
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while (right < s.length()) {
            while (right < s.length() && s.charAt(right) == s.charAt(left)) {
                right++;
            }
            maxLen = Math.max(maxLen, right - left);
            left = right;
        }

        return maxLen;

    }

    //method 2 ��ǰ��ֹ
    public int maxPower2(String s) {
        char[] arr = s.toCharArray();
        int maxLen = 1;
        int cnt = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                cnt++;
                maxLen = Math.max(maxLen, cnt);
            } else {
                if (maxLen > arr.length - i)
                    return maxLen;
                else
                	cnt = 1;
            }
            
        }

        return maxLen;

    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abbcccddddeeeeedcba";
		ReturnStrPower rs = new ReturnStrPower();
		long startTime = System.nanoTime();
		System.out.println(rs.maxPower1(s));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(rs.maxPower2(s));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

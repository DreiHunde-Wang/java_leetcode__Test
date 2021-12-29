package stringnumtest;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * @author Dreihunde
 *
 */
public class CheckInclusion {
	//method 1 遍历 o(mn) O(C)
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] mask = new int[26];
        for (int i = 0; i < n; i++) {
            mask[s1.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < m - n + 1; i++) {
            int[] temp = mask.clone();
            if (isContians(temp, s2, i, n)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContians(int[] mask, String s, int start, int n) {
        for (int i = start; i < start + n; i++) {
            char c = s.charAt(i);
            if (mask[c - 'a'] > 0) {
                mask[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

    //method 2 滑动窗口 O(m + n) O(C)
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] mask1 = new int[26];
        int[] mask2 = new int[26];
        for (int i = 0; i < n; i++) {
            mask1[s1.charAt(i) - 'a']++;
            mask2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(mask1, mask2)) {
            return true;
        }

        for (int i = n; i < m; i++) {
            mask2[s2.charAt(i) - 'a']++;
            mask2[s2.charAt(i - n) - 'a']--;
            if (Arrays.equals(mask1, mask2)) {
                return true;
            }
        }

        return false;
    }

    //method 3 双指针 O(m + n) O(C)
    public boolean checkInclusion3(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] mask = new int[26];
        for (int i = 0; i < n; i++) {
            mask[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int i = 0; i < m; i++) {
            int idx = s2.charAt(i) - 'a';
            mask[idx]++;
            while (mask[idx] > 0) {
                mask[s2.charAt(left) - 'a']--;
                left++;
            }
            if (i - left + 1 == n) {
                return true;
            }
        }

        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "adc";
		String s2 = "dcda";
		CheckInclusion ci = new CheckInclusion();
		long startTime = System.nanoTime();
		System.out.println(ci.checkInclusion1(s1, s2));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(ci.checkInclusion2(s1, s2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(ci.checkInclusion3(s1, s2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		

	}

}

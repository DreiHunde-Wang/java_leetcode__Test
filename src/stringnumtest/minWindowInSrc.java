package stringnumtest;

/**
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * @author Dreihunde
 *
 */
public class minWindowInSrc {
	//method 1 滑动窗口 O(m + n) O(C)
    public String minWindow1(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return "";
        }

        int[] mask = new int[60];
        for (int i = 0; i < m; i++) {
            mask[s.charAt(i) - 'A']--;
            mask[t.charAt(i) - 'A']++;
        }
        if (isAllLSZero(mask)) {
            return s.substring(0, m);
        }
        int minLen = n + 1;
        String ans = "";
        int left = 0;
        for (int i = m; i < n; i++) {
            mask[s.charAt(i) - 'A']--;
            while (isAllLSZero(mask)) {
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                    ans = s.substring(left, i + 1);
                }
                if (minLen == m) {
                    return ans;
                }
                mask[s.charAt(left) - 'A']++;
                left++;
            }
        }
        return ans;
    }

    private boolean isAllLSZero(int[] mask) {
        for (int t : mask) {
            if (t > 0) {
                return false;
            }
        }
        return true;
    }

    //method 2 双指针 O(m + n) O(C)
    public String minWindow2(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return "";
        }

        int[] mask = new int[60];
        int k = 0;
        for (int i = 0; i < m; i++) {
            mask[t.charAt(i) - 'A']++;
            if (mask[t.charAt(i) - 'A'] == 1) {
                k++;
            }
        }
        int left = 0;
        int right = 0;
        int l = 0;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'A';
            mask[idx]--;
            if (mask[idx] == 0) {
                k--;
            }
           if (k == 0) {
               while (mask[s.charAt(l) - 'A'] < 0) {
                   mask[s.charAt(l++) - 'A']++;
               }
               if (left == right || i - l + 1 < right - left) {
                   left = l;
                   right = i + 1;
               }
               if (right - left == m) {
                   return s.substring(left, right);
               }
           }
        }
        return left == right ? "" : s.substring(left, right);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ADOBECODEBANC";
		String t = "ABC";
		
		minWindowInSrc mw = new minWindowInSrc();
		System.out.println(mw.minWindow1(s, t));
		System.out.println(mw.minWindow2(s, t));

	}

}

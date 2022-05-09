package src.stringnumtest;

/**
 * 实现 strStr() 函数�?
 * 给你两个字符串 haystack �? needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标�? 0 �?始）�?
 * 如果不存在，则返回�? -1 �?
 * @author Dreihunde
 *
 */
public class StrStr {

    //method 1 KMP (n + m) O(m)
    private int KMP(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int n = haystack.length();
        int m = needle.length();

        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } 
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    //method 2 RK O(n + m) O(1)
    private int RK(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }

        //取余数，防止溢出
        int Mod = 1000000009;
        //26个字母，增大减少重复
        int base = 31;
        long power = 1;
        //得到�?高位要乘的�??
        for (int i = 0; i < m; i++) {
            power = (power * base) % Mod;
        }
        //needle的hash�?
        long needleHash = 0;
        for (int i = 0; i < m; i++) {
            needleHash = (needleHash * base + (needle.charAt(i) - 'a')) % Mod;
        }
        long curHash = 0;
        for (int i = 0; i < n; i++) {
            curHash = (curHash * base + (haystack.charAt(i) - 'a')) % Mod;
            if (i < m - 1) {
                continue;
            } 
            if (i > m - 1) {
                curHash = (curHash - (haystack.charAt(i - m) - 'a') * power) % Mod;
                if (curHash < 0) {
                    curHash += Mod;
                }
            }
            
            if (curHash == needleHash) {
                if (haystack.substring(i - m + 1, i + 1).equals(needle)) 
                    return i - m + 1;
            }
        }
        
        return -1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String haystack = "ababcaababcaabc";
		String needle = "ababcaabc";
		
		StrStr ss = new StrStr();
		long startTime=System.nanoTime(); 
		System.out.println(ss.KMP(haystack, needle));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(ss.RK(haystack, needle));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

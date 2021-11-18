package slidewindowtest;

import java.util.HashMap;
import java.util.HashSet;

public class LongSubWoRepeat {
	
	//method 1 滑动窗口
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0)
            return 0;

        int rb = 0;
        int ans = 0;

        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (i != 0)
                set.remove(s.charAt(i - 1));
            while (rb < s.length() && !set.contains(s.charAt(rb))) {
                set.add(s.charAt(rb));
                rb++;
            }
            ans = Math.max(ans, rb - i);
        }

        return ans;
        
    }
    /**
    1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
     此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；

    2、如果当前字符 ch 包含在 map中，此时有2类情况：
     1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
     那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
     2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
     而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
     随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
     应该不变，left始终为2，子段变成 ba才对。

     为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
     另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
     因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
     */
    //method 2 map优化，跳跃窗口
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0)
            return 0;

        int left = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
            	left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
        
    }
    
    public static void main(String[] args) {
    	String s = "abcdeftbreweqeqggasg3eg";
    	long startTime = System.nanoTime();
		System.out.println(lengthOfLongestSubstring1(s));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(lengthOfLongestSubstring2(s));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

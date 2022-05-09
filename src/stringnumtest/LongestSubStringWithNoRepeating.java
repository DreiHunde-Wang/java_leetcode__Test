package src.stringnumtest;

import java.util.HashMap;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * @author Dreihunde
 *
 */
public class LongestSubStringWithNoRepeating {
	//method 1 HaspMap O(n) O(n)
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        //不重复的第一个元素
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //当当前字符已经被记录，且上一次出现的位置大于等于start时，更新start位置
            if (map.containsKey(c)) {
                int t = map.get(c);
                start = t >= start ? t + 1 : start;
            } 
            //更新每个字符对应的位置
            map.put(c, i);
            //更新最大长度
            maxLen = Math.max(i - start + 1, maxLen);
            
        }

        return maxLen;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabcbb";
		LongestSubStringWithNoRepeating ls = new LongestSubStringWithNoRepeating();
		System.out.println(ls.lengthOfLongestSubstring(s));

	}

}

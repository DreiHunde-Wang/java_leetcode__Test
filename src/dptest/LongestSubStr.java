package dptest;

import java.util.HashMap;
import java.util.Map;

import numsorttest.NumMass;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * @author Dreihunde
 *
 */
public class LongestSubStr {
	//method 1 hash表+滑动窗口法
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int left = -1;
        int maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    //method 2 动态规划
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLen = 0;
        //动态规划保存临时变量
        int temp = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
        	//上一个相同字符的位置j
            int j = map.getOrDefault(arr[i], -1);
            map.put(arr[i], i);
            //如果当前字符与其重复字符的位置j的距离d > t[i] t[i] = t[i - 1] + 1，否则距离t[i] = d
            temp = temp < i - j? temp + 1: i - j;
            maxLen = Math.max(maxLen, temp);
        }
        return maxLen;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcabcbb";
		
		LongestSubStr ls = new LongestSubStr();
		long startTime = System.nanoTime();
		System.out.println(ls.lengthOfLongestSubstring1(str));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(ls.lengthOfLongestSubstring2(str));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

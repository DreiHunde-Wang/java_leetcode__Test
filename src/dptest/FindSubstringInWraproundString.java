package src.dptest;

import java.util.Arrays;

/**
 * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 
 * 链接：https://leetcode.cn/problems/unique-substrings-in-wraparound-string
 * @author Dreihunde
 *
 */
public class FindSubstringInWraproundString {
	//method 1 dp O(n) O(C) C = 26
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (i > 0  && (c - p.charAt(i - 1) + 26) % 26 == 1) {//当前字符与前一个字符的差值只能为1或者-25
                k++;
            } else {
                k = 1;
            }
            dp[c - 'a'] = Math.max(k, dp[c - 'a']);
        }
        return Arrays.stream(dp).sum();
    }

}

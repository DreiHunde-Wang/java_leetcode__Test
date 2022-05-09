package src.dptest;

import java.util.ArrayDeque;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * @author Dreihunde
 *
 */
public class LongestValidParentheses {
	//method 1 stack记录 O(n) O(n)
    public int longestValidParentheses1(String s) {
        int n = s.length();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxLen = 0;
        stack.push(-1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
    
    //method 2 两次遍历 O(n) O(1)
    public int longestValidParentheses2(String s) {
        int left = 0;
        int right = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, right * 2);
            } else if (left < right) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, right * 2);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLen;
    }

    //method 3 dp O(n) O(n)
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxLen = 0;
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}

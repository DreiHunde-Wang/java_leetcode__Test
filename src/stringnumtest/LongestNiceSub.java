package stringnumtest;

/**
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。
 * 比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。
 * 然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。
 * 如果不存在美好子字符串，请你返回一个空字符串。
 * https://leetcode-cn.com/problems/longest-nice-substring/
 * @author Dreihunde
 *
 */
public class LongestNiceSub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Solution1 {
    //method 枚举 O(n^2) O(1)
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int maxPos = 0;
        for (int i = 0; i < n; i++) {
            int lowerChar = 0;
            int upperChar = 0;
            for (int j = i; j < n ; j++) {
                char c = s.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    lowerChar |= 1 << (c - 'a');
                } else {
                    upperChar |= 1 << (c - 'A');
                }
                if (lowerChar == upperChar && j - i + 1 > maxLen) {
                    maxPos = i;
                    maxLen = j - i + 1;
                }
            }   
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

}

//method 2 分治 O(nC) O(C)
class Solution2 {
    private int maxPos;
    private int maxLen;

    public String longestNiceSubstring(String s) {
        this.maxPos = 0;
        this.maxLen = 0;
        //dfs整个字符串
        dfs(s, 0, s.length() - 1);
        return s.substring(maxPos, maxPos + maxLen);
    }

    public void dfs(String s, int start, int end) {
        if (start >= end) {
            return;
        }
        //对小写字母和大写字母进行统计
        int lower = 0, upper = 0;
        for (int i = start; i <= end; ++i) {
            if (Character.isLowerCase(s.charAt(i))) {
                lower |= 1 << (s.charAt(i) - 'a');
            } else {
                upper |= 1 << (s.charAt(i) - 'A');
            }
        }
        //如果该字符区间符合要求，就更新最大长度
        if (lower == upper) {
            if (end - start + 1 > maxLen) {
                maxPos = start;
                maxLen = end - start + 1;
            }
            return;
        }
        //如果不符合要求，就相与找出两者共同的字符 
        int valid = lower & upper;
        //从起始点开始搜素
        int pos = start;
        while (pos <= end) {
            start = pos;
            //查询到第一个不符合要求的字符
            while (pos <= end && (valid & (1 << Character.toLowerCase(s.charAt(pos)) - 'a')) != 0) {
                ++pos;
            }
            //以此为分界点，分治
            dfs(s, start, pos - 1);
            ++pos;
        }
    }
}

//method 3 滑动窗口 O(nC) O(C)
class Solution {
    private int maxPos;
    private int maxLen;

    public String longestNiceSubstring(String s) {
        this.maxPos = 0;
        this.maxLen = 0;
        
        int types = 0;
        for (int i = 0; i < s.length(); ++i) {
            types |= 1 << (Character.toLowerCase(s.charAt(i)) - 'a');
        }
        types = Integer.bitCount(types);
        for (int i = 1; i <= types; ++i) {
            check(s, i);
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    public void check(String s, int typeNum) {
        int[] lowerCnt = new int[26];
        int[] upperCnt = new int[26]; 
        int cnt = 0;
        for (int l = 0, r = 0, total = 0; r < s.length(); ++r) {
            int idx = Character.toLowerCase(s.charAt(r)) - 'a';
            if (Character.isLowerCase(s.charAt(r))) {
                ++lowerCnt[idx];
                if (lowerCnt[idx] == 1 && upperCnt[idx] > 0) {
                    ++cnt;
                }
            } else {
                ++upperCnt[idx];
                if (upperCnt[idx] == 1 && lowerCnt[idx] > 0) {
                    ++cnt;
                }
            }
            total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
            while (total > typeNum) {
                idx = Character.toLowerCase(s.charAt(l)) - 'a';
                total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
                if (Character.isLowerCase(s.charAt(l))) {
                    --lowerCnt[idx];
                    if (lowerCnt[idx] == 0 && upperCnt[idx] > 0) {
                        --cnt;
                    }
                } else {
                    --upperCnt[idx];
                    if (upperCnt[idx] == 0 && lowerCnt[idx] > 0) {
                        --cnt;
                    }
                }
                ++l;
            }
            if (cnt == typeNum && r - l + 1 > maxLen) {
                maxPos = l;
                maxLen = r - l + 1;
            }
        }
    }
}

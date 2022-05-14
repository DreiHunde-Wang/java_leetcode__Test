package src.dfsandbfs;

import java.util.Arrays;

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。
 * 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 * 链接：https://leetcode.cn/problems/stickers-to-spell-word
 * @author Dreihunde
 *
 */
public class MinStickers {
	//method 1 dfs + 回溯 O(2^mnC) O(mn) (超时)
    int minUse;
    int n, m;
    public int minStickers1(String[] stickers, String target) {
        minUse = Integer.MAX_VALUE;
        n = stickers.length;
        int[][] masks = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                masks[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        m = target.length();
        int[] tmask = new int[26];
        for (int i = 0; i < m; i++) {
            tmask[target.charAt(i) - 'a']++;
        }
        dfs(masks, tmask, 0);
        return minUse == Integer.MAX_VALUE ? -1 : minUse;
    }

    public void dfs(int[][] masks, int[] tmask, int count) {
        if (count > minUse || count > m) {
            return;
        }
        if (isCover(tmask)) {
            minUse = Math.min(minUse, count);
            return;
        }
        for (int i = 0; i < masks.length; i++) {
            int[] t = masks[i];
            if (!isHave(t, tmask)) {
                continue;
            }
            del(tmask, t);
            dfs(masks, tmask, count + 1);
            add(tmask, t);
        }

    }
    
    public void add(int[] src, int[] t) {
        for (int i = 0; i < 26; i++) {
                src[i] += t[i];
        }
    }

    public void del(int[] src, int[] t) {
        for (int i = 0; i < 26; i++) {
                src[i] -= t[i];
        }
    }

    public boolean isCover(int[] tmask) {
        for (int t : tmask) {
            if (t > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isHave(int[] mask, int[] target) {
        for (int i = 0; i < 26; i++) {
            if (mask[i] > 0 && target[i] > 0) {
                return true;
            }
        }
        return false;
    }

    //method 2 dfs+记忆化搜索+状态压缩 O((2^m)n(m + c)) O(2^m)
    public int minStickers(String[] stickers, String target) {
        int m = target.length();
        int[] memo = new int[1 << m];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        int res = dp(stickers, target, memo, (1 << m) - 1);
        return res <= m ? res : -1;
    }

    public int dp(String[] stickers, String target, int[] memo, int mask) {
        int m = target.length();
        if (memo[mask] < 0) {
            int res = m + 1;
            for (String sticker : stickers) {
                int left = mask;
                int[] cnt = new int[26];
                for (int i = 0; i < sticker.length(); i++) {
                    cnt[sticker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < target.length(); i++) {
                    char c = target.charAt(i);
                    if (((mask >> i) & 1) == 1 && cnt[c - 'a'] > 0) {
                        cnt[c - 'a']--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask) {
                    res = Math.min(res, dp(stickers, target, memo, left) + 1);
                }
            }
            memo[mask] = res;
        }
        return memo[mask];
    }

}

package src.dptest;

/**
 * 给你�?个长桌子，桌子上盘子和蜡烛排成一列�?�给你一个下标从 0 �?始的字符串 s ，它只包含字符�?'*' 和�?'|' ，其中�?'*' 表示�?�? 盘子 �?'|' 表示�?支 蜡烛 �??
 * 同时给你�?个下标从 0 �?始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）�?�对于每个查询，你需要找�? 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有�?支蜡烛，那么这个盘子满足�? 两支蜡烛之间 �?
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串�?"*||**|" �?
 * 子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 �? 至少有一支蜡烛�??
 * 请你返回�?个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案
 * 链接：https://leetcode-cn.com/problems/plates-between-candles
 * @author Dreihunde
 *
 */
public class PlatesBetweenCandles {
	//method 1 前缀�? O(n + q) O(n) q为询问数�?
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] preSum = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            preSum[i] = sum;
        }
        int[] left = new int[n];
        for (int i = 0, l = -1; i < n; i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        int[] right = new int[n];
        for (int i = n - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = right[query[0]], y = left[query[1]];
            ans[i] = x == -1 || y == -1 || x >= y ? 0 : preSum[y] - preSum[x];
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package simulationtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 * https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * @author Dreihunde
 *
 */
public class NearestPalindromic {
	//method 1 模拟 O(logn) O(logn)
	/**
	 * 分别计算出以下每一种可能的方案对应的回文整数，在其中找到与原数最近且不等于原数的数即为答案。
	 * 用原数的前半部分替换后半部分得到的回文整数。
	 * 用原数的前半部分加一后的结果替换后半部分得到的回文整数。
	 * 用原数的前半部分减一后的结果替换后半部分得到的回文整数。
	 * 为防止位数变化导致构造的回文整数错误，因此直接构造999…999 和100…001 作为备选答案。
	 */
    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n);
        long ans = -1;
        List<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                    Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                    Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            
            // len & 1 奇数为1 偶数为0
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            candidates.add(Long.parseLong(candidate));
        }
        return candidates;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

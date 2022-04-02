package numsearch;

import java.util.Arrays;

/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
 * @author Dreihunde
 *
 */
public class MaxNumberOfBallons {
	//method 1 统计 O(n + C) O(C) C = 5;
    public int maxNumberOfBalloons(String text) {
        int[] charCount = new int[5];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == 'a') {
                charCount[0]++;
            } else if (c == 'b') {
                charCount[1]++;
            } else if (c == 'l') {
                charCount[2]++;
            } else if (c == 'o') {
                charCount[3]++;
            } else if (c == 'n') {
                charCount[4]++;
            }
        }
        // int count = minCount(charCount);
        // return count;

        charCount[3] /= 2;
        charCount[2] /= 2;
        return Arrays.stream(charCount).min().getAsInt();
    }

    private int minCount(int[] charCount) {
        int min = charCount[0];
        min = Math.min(charCount[1], min);
        min = Math.min(charCount[2] / 2, min);
        min = Math.min(charCount[3] / 2, min);
        min = Math.min(charCount[4], min);
        return min;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

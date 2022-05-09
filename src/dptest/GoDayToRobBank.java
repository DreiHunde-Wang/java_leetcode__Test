package src.dptest;

import java.util.ArrayList;
import java.util.List;

/**
 * 你和�?群强盗准备打劫银行�?�给你一个下标从 0 �?始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0 �?始编号�?�同时给你一个整数 time �?
 * 如果�? i 天满足以下所有条件，我们称它为一个�?�合打劫银行的日子：
 * �? i 天前和后都分别至少有 time 天�??
 * �? i 天前连续 time 天警卫数目都是非递增的�??
 * �? i 天后连续 time 天警卫数目都是非递减的�??
 * 更正式的，第 i 天是�?个合适打劫银行的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * 请你返回�?个数组，包含 �?�? 适合打劫银行的日子（下标�? 0 �?始）。返回的日子可以 任意 顺序排列�?
 * 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank
 * @author Dreihunde
 *
 */
public class GoDayToRobBank {
	//method 1 前后�?dp O(n) O(n)
    public List<Integer> goodDaysToRobBank1(int[] security, int time) {
        int n = security.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            if (security[n - i - 1] <= security[n - i]) {
                right[n - i - 1] = right[n - i] + 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = time; i < n - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);    
            }
        }
        return ans;
    }
    //method 1 前后�?dp+优化 O(n) O(1)
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        List<Integer> list = new ArrayList<>();
        if (time == 0) {
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
            return list;
        }
        int left = 0;
        int right = 0;
        for (int i = 1; i < n - time; i++) {
            if (security[i - 1] >= security[i]) {
                left++;
            } else {
                left = 0;
            }
            if (security[i + time - 1] <= security[i + time]) {
                right++;
            } else {
                right = 0;
            }
            if (left >= time && right >= time) {
                list.add(i);
            }
        }
        return list;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

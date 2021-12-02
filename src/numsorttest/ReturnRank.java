package numsorttest;

import java.net.Socket;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import printtreenode.TreeNode;

/**
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。
 * 运动员的名次决定了他们的获奖情况：
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * @author Dreihunde
 *
 */
public class ReturnRank {
	//method 1 HashMap O(nlogn) O(n)
    public String[] findRelativeRanks1(int[] score) {
        int[] ans = Arrays.copyOf(score, score.length);
        Arrays.sort(ans);
        String[] rev = new String[score.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = ans.length - 1; i >= 0; i--) {
            map.put(ans[i], ans.length - 1 - i);
        }

        for (int i = 0; i < score.length; i++) {
            if (map.get(score[i]) == 0) {
                rev[i] = "Gold Medal";
            } else if (map.get(score[i]) == 1) {
                rev[i] = "Silver Medal";
            } else if (map.get(score[i]) == 2) {
                rev[i] = "Bronze Medal";
            } else {
                rev[i] = String.valueOf(map.get(score[i]) + 1);
            }  
        }

        return rev;

    }
    
    //method 2 HashMap O(nlogn) O(n)
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        //预设
        String[] ss = new String[] {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
        	arr[i][0] = score[i];
        	arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> (b[0] - a[0]));
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            if (i < 3) {
            	ans[arr[i][1]] = ss[i]; 
            } else {
            	ans[arr[i][1]] = String.valueOf(i + 1);
            }
        }

        return ans;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReturnRank rr = new ReturnRank();
		int[] score = new int[] {5,4,3,2,1};
		
		long startTime = System.nanoTime();
		CommonTest.printNum(rr.findRelativeRanks1(score));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(rr.findRelativeRanks2(score));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

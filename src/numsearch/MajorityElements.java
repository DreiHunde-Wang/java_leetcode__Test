package numsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊n/3⌋ 次的元素。
 * https://leetcode-cn.com/problems/majority-element-ii/
 * @author Dreihunde
 *
 */
public class MajorityElements {
	//两次筛选 O(n) O(1)
    public List<Integer> majorityElement1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        int[][] rets = new int[2][2];
        //第一次筛选出频率最高的两个数
        for (int i = 0; i < n; i++) {
            if (isAdd(rets, nums[i])) {
                continue;
            }
            if (isReplace(rets, nums[i])) {
                continue;
            }
            for (int j = 0; j < 2; j++) {
                rets[j][1]--;
            } 
        }
        //第二次判断筛选出的两个数是否频率大于n/3
        for (int[] ret : rets) {
            int count = 0;
            for (int num : nums) {
                if (num == ret[0]) {
                    count++;
                }
            }
            if (count > n / 3 && ret[1] > 0) {
                ans.add(ret[0]);
            }
        }
        return ans;
        
    }

    public boolean isAdd(int[][] rets, int num) {
        boolean ret = false;
        for (int j = 0; j < 2; j++) {
            if (num == rets[j][0]) {
                ret = true;
                rets[j][1]++;
                break;
            } 
        }
        return ret;

    }

    public boolean isReplace(int[][] rets, int num) {
        boolean ret = false;
        for (int j = 0; j < 2; j++) {
            if (rets[j][1] == 0) {
                rets[j][0] = num;
                rets[j][1] = 1;
                ret = true;
                break;
            } 
        }
        return ret;

    }

    //method 2 哈希统计 O(n) O(n)
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (cnt.containsKey(nums[i])) {
                cnt.put(nums[i], cnt.get(nums[i]) + 1);
            } else {
                cnt.put(nums[i], 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : cnt.keySet()) {
            if (cnt.get(x) > nums.length / 3) {
                ans.add(x);
            }
        }
   
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

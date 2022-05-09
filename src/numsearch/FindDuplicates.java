package src.numsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你�?个长度为 n 的整数数�? nums ，其�? nums 的所有整数都在范�? [1, n] 内，且每个整数出�? �?�? �? 两次 �?
 * 请你找出�?有出�? 两次 的整数，并以数组形式返回�?
 * 你必须设计并实现�?个时间复杂度�? O(n) 且仅使用常量额外空间的算法解决此问题�?
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * @author Dreihunde
 *
 */
public class FindDuplicates {
	//method 1 位置排序 O(n) O(1)
    public List<Integer> findDuplicates1(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                ans.add(nums[i]);
            }
        }

        return ans;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    //method 2 正负号标�? O(n) O(1)
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = nums[i] > 0 ? nums[i] - 1 : -nums[i] - 1;
            if (nums[index] > 0)
                nums[index] = -nums[index];
            else 
                ans.add(index + 1);
        }
        return ans;
    }

}

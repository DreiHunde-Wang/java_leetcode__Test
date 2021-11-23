package numsearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import enumtest.BuddyString;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * @author Dreihunde
 *
 */
public class HaveRepeatNumber {
	//method 1 hashMap
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        //记录元素值和位置
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                //如果重复元素位置在k范围内，返回true
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    //更新元素位置
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }

    //method 2 hashSet
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        //只维护一个k长度的set
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))  
                return true;
            set.add(nums[i]);
            if (set.size() > k)
                set.remove(nums[i - k]);
        }
        return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,3,1};
		int k = 3;
		HaveRepeatNumber hr = new HaveRepeatNumber();
		
		long startTime = System.nanoTime();
		System.out.println(hr.containsNearbyDuplicate1(nums, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(hr.containsNearbyDuplicate2(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

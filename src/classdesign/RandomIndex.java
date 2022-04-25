package classdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
 * 实现 Solution 类：
 * Solution(int[] nums) 用数组 nums 初始化对象。
 * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * @author Dreihunde
 *
 */
public class RandomIndex {
	//method 1 map映射相同元素，list统计索引 O(n) O(n)
    Map<Integer, ArrayList<Integer>> map;
    Random rand;
    public RandomIndex(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                ArrayList<Integer> temp = new ArrayList<>();
                map.put(nums[i], temp);
            } 
            map.get(nums[i]).add(i);
        }
    }
    
    public int pick1(int target) {
        int size = map.get(target).size();
        int ret = rand.nextInt(size);
        return map.get(target).get(ret);
    }

}

class RandomIndex2 {
    //method 2 流水线计数法 O(1) O(1)
    int[] nums;
    Random rand;
    public RandomIndex2(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
        
    }
    
    public int pick(int target) {
        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                cnt++;
                if (rand.nextInt(cnt) == 0) {
                    ans = i;
                }
            }
            
        }
        return ans;
    }

}

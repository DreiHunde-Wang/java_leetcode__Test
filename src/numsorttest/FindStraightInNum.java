package src.numsorttest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，
 * A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * @author Dreihunde
 *
 */
public class FindStraightInNum {
	//method 1 排序后比较是否重复
    public boolean isStraight1(int[] nums) {
        Arrays.sort(nums);
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                min++;
                continue;  
            }
                
            if (i > 0 && nums[i] == nums[i - 1])
                return false;
        }
        return nums[4] - nums[min] < 5;
    }

    //method 2 Set
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int min = 14;
        int max = 0;
        for (int num : nums) {
            if (num == 0)
                continue;
            if (!set.add(num))
                return false;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

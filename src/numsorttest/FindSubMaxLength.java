package src.numsorttest;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * @author Dreihunde
 *
 */
public class FindSubMaxLength {
	//method 1 前缀和 O(n) O(n)
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        //0的个数-1的个数
        int counter = 0;
        map.put(counter, -1);
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 0) {
                counter++;
            } else {
                counter--;
            }

            if (map.containsKey(counter)) {
                int preIndex = map.get(counter);
                maxLen = Math.max(maxLen, i - preIndex);
            } else {
                map.put(counter, i);
            }
        }

        return maxLen;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 0, 1, 1, 0, 0};
		
		FindSubMaxLength fs = new FindSubMaxLength();
		System.out.println(fs.findMaxLength(nums));
	}

}

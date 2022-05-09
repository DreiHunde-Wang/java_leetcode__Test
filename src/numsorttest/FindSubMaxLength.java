package src.numsorttest;

import java.util.Arrays;
import java.util.HashMap;

/**
 * ����һ������������ nums , �ҵ�������ͬ������ 0 �� 1 ������������飬�����ظ�������ĳ��ȡ�
 * @author Dreihunde
 *
 */
public class FindSubMaxLength {
	//method 1 ǰ׺�� O(n) O(n)
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        //0�ĸ���-1�ĸ���
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

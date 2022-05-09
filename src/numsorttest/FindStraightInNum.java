package src.numsorttest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * �����ɸ��˿���������� 5 ���ƣ��ж��ǲ���һ��˳�ӣ�����5�����ǲ��������ġ�2��10Ϊ���ֱ���
 * AΪ1��JΪ11��QΪ12��KΪ13������С��Ϊ 0 �����Կ����������֡�A ������Ϊ 14��
 * @author Dreihunde
 *
 */
public class FindStraightInNum {
	//method 1 �����Ƚ��Ƿ��ظ�
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

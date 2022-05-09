package src.numsorttest;

import java.util.Arrays;

/**
 * ����һ�� �±�� 0 ��ʼ ���������� nums ������ nums[i] ��ʾ�� i ��ѧ���ķ����������һ������ k ��
 * ��������ѡ������ k ��ѧ���ķ�����ʹ�� k �������� ��߷� �� ��ͷ� �� ��ֵ �ﵽ ��С�� ��
 * ���ؿ��ܵ� ��С��ֵ ��
 * ���ӣ�https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * @author Dreihunde
 *
 */
public class MinmumDiff {
	//method 1 ���� O(nlogn) O(logn) ���������ջ�ռ�
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = k - 1;
        int diff = nums[right] - nums[left];
        while (right < nums.length - 1) {
            right++;
            left++;
            diff = Math.min(nums[right] - nums[left], diff);
        }
        return diff;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

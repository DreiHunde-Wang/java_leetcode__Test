package src.numsorttest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ����һ���������� nums ��������ΨһԪ������Щֻ���� ǡ��һ�� ��Ԫ�ء�
 * ���㷵�� nums ��ΨһԪ�ص� �� ��
 * https://leetcode-cn.com/problems/sum-of-unique-elements/
 * @author Dreihunde
 *
 */
public class SumOfUnique {
	//method 1 map O(n) O(n)
    public int sumOfUnique1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int k : map.keySet()) {
            if (map.get(k) == 1) {
                ans += k;
            }
        }
        
        return ans;
    }

    //method 2 Ͱ���� O(n + C) O(C) C = 100
    public int sumOfUnique2(int[] nums) {
        int[] count = new int[100];
        int ans = 0;
        for (int num: nums) {
            count[num - 1]++;
        }
        for (int i = 0; i < 100; i++) {
            ans = count[i] == 1 ? ans + i + 1 : ans;
        }
        return ans;
    }

    //method 3 һ�α��� O(n) O(n)
    public int sumOfUnique3(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> state = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!state.containsKey(num)) {
                ans += num;
                state.put(num, 1);
            } else if (state.get(num) == 1) {
                ans -= num;
                state.put(num, 2);
            }
        }
        return ans;
    }

    //method 4 �������ٱȽ� O(nlogn) O(logn)
    public int sumOfUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; ) {
            int j = i;
            while (j < nums.length && nums[j] == nums[i]) {
                j++;
            }
            if (j - i == 1) {
                ans += nums[i];
            }
            i = j;
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

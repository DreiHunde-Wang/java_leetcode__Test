package numsearch;

import java.util.HashMap;
import java.util.Map;

import numsorttest.CommonTest;

/**
 * ����һ�� �±�� 0 ��ʼ ���������� nums �������������������� ��ͬ ��Ԫ�� (a, b, c, d) �� ��Ŀ ��
 * nums[a] + nums[b] + nums[c] == nums[d] ����
 * a < b < c < d
 * @author Dreihunde
 *
 */
public class CountQuadruplets {
	//method 1 ���� O(n^4) Oz(1)
    public int countQuadruplets1(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    //method 2 hashmap�洢 O(n^3) O(min(n, C))
    public int countQuadruplets2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        //map���� nums[d]
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int c = n - 2; c >= 2; --c) {
            cnt.put(nums[c + 1], cnt.getOrDefault(nums[c + 1], 0) + 1);
            for (int a = 0; a < c; ++a) {
                for (int b = a + 1; b < c; ++b) {
                    ans += cnt.getOrDefault(nums[a] + nums[b] + nums[c], 0);
                }
            }
        }
        return ans;
    }

    //method 3 hashmap�洢 O(n^2) O(min(n, C)^2)
    public int countQuadruplets3(int[] nums) {
        int n = nums.length;
        int ans = 0;
        //map����nms[d] - nums[c]
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int b = n - 3; b >= 1; --b) {
            for (int d = b + 2; d < n; ++d) {
                cnt.put(nums[d] - nums[b + 1], cnt.getOrDefault(nums[d] - nums[b + 1], 0) + 1);
            }
            for (int a = 0; a < b; ++a) {
                ans += cnt.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {28,8,49,85,37,90,20,8};
		CountQuadruplets cq = new CountQuadruplets();
		
		long startTime=System.nanoTime(); 
		System.out.println(cq.countQuadruplets1(nums));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(cq.countQuadruplets2(nums));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(cq.countQuadruplets3(nums));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

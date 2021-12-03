package numsorttest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * @author Dreihunde
 *
 */
public class ReturnKRevSum {
	//method 1 遍历 map记频 O(n + C) O(C)
    public int largestSumAfterKNegations1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Arrays.stream(nums).sum();

        for (int i = -100; i < 0; i++) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                //负数变正数，相当于加了双倍
                ans += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0)     break;
            }
            
        }
        if (k > 0 && k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; i++) {
                if (freq.containsKey(i))  {
                    ans -= i * 2;
                    break;
                }
            }
        }

        return ans;
    }

    //method 2 桶排序 O(n + C) O(C)
    public int largestSumAfterKNegations2(int[] nums, int k) {
        int[] bucket = new int[201];
        for (int n : nums) {
            bucket[n + 100]++;
        }
        int i = 0;
        while (k > 0) {
            while(bucket[i] == 0) {
                i++;
            }
            bucket[i]--;
            bucket[200 - i]++;
            if (i > 100) {
                i = 200 - i;
            }
            k--;
        }
        int sum = 0;
        for (int j = i; j < bucket.length; j++) {
            sum += (j - 100) * bucket[j];
        }

        return sum;
    }

    //method 3 优先队列 O(nlogn) O(n)
    public int largestSumAfterKNegations3(int[] nums, int k) {
        //递增排序
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (nums[a] - nums[b]));
        //是否有0
        boolean zero = false;
        int n = nums.length;
        //绝对值最小值的坐标
        int minIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0)    heap.offer(i);
            if (nums[i] == 0)   zero = true;
            if (Math.abs(nums[i]) < Math.abs(nums[minIdx]))  minIdx = i;
        }

        if (k <= heap.size()) {
            while (k > 0) {
                nums[heap.peek()] = -nums[heap.poll()];
                k--;
            }
        } else {
            while (!heap.isEmpty() && k > 0) {
                nums[heap.peek()] = -nums[heap.poll()];
                k--;
            }
            if (!zero && (k & 1) == 1) {
                nums[minIdx] = -nums[minIdx];
            }
        }
        return Arrays.stream(nums).sum();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {2,-3,-1,5,-4};
		int k = 2;
		ReturnKRevSum rk = new ReturnKRevSum();
		
		long startTime = System.nanoTime();
		System.out.println(rk.largestSumAfterKNegations1(nums, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(rk.largestSumAfterKNegations2(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(rk.largestSumAfterKNegations3(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

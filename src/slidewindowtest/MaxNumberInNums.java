package src.slidewindowtest;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

import src.numsorttest.CommonTest;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * @author Dreihunde
 *
 */
public class MaxNumberInNums {
	//method 1 遍历比较 O(kn) O(1)
    public int[] maxSlidingWindow1(int[] nums, int k) {
        //边界判断
        if (nums.length == 0)
            return new int[0];
        //返回值
        int[] rev = new int[nums.length - k + 1];
        //从左往右遍历
        for (int i = 0; i < nums.length - k + 1; i++) {
            //返回每个滑动窗口的最大值
            rev[i] = maxInNum(nums, i, i + k);
        }
        return rev;
    }
    
    private int maxInNum(int[] nums, int start, int end) {
        int max = nums[start];

        for (int i = start + 1; i < end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    //method 2 遍历比较 + 优化 O(n) O(1)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        //边界判断
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //返回值
        int[] rev = new int[n - k + 1];
        rev[0] = maxInNum(nums, 0, k);
        //从左往右遍历
        for (int i = 1; i < n - k + 1; i++) {
            //如果新的值比当前的最大值大，则为新的最大值
            if (nums[i + k - 1] >= rev[i - 1]) {
                rev[i] = nums[i + k - 1];
            } else {
                //如果窗口最左端为上一个最大值，那么重新寻找窗口的最大值，否则窗口最大值不变
                if (nums[i - 1] == rev[i - 1]) {
                    rev[i] = maxInNum(nums, i, i + k);
                } else {
                    rev[i] = rev[i - 1];
                }
            }
        }
        return rev;
    }

    //method 3 优先队列 O(nlogn) O(n)
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //构建大根堆(堆顶存放值最大，索引值最大的节点)
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		//若两个值不相等则按值排序，相等则按索引排序
        		return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
        	};
		});
        //堆的容量为k
        for (int i = 0; i < k; i++) {
            heap.offer(new int[]{nums[i], i});
        }
        int[] rev = new int[n - k + 1];
        //前k个值的最大值为堆顶
        rev[0] = heap.peek()[0];
        for (int i = k; i < n; i++) {
            //添加新值入堆
            heap.offer(new int[]{nums[i], i});
            //删除堆内索引i - k以及之前的节点
            while (heap.peek()[1] <= i - k) {
                heap.poll();
            }
            //添加堆顶值到答案中
            rev[i - k + 1] = heap.peek()[0];
        }

        return rev;
    }

    //method 4 将窗口梳理为递减数列 O(n) O(k)
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //构建双端队列
        Deque<Integer> deque = new LinkedList<Integer>();
        //保存前k个数的最大值的对应索引（队首），整个队列为递减队列
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        //前k个数的答案为队首(目前的最大值)
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            //将队列变为递减队列
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            //当索引值小于等于i - k，即离开窗口，进行删除
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            //队首是当前的最大值
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    //method 5 分块 + 预处理 O(n) O(n)
    public int[] maxSlidingWindow5(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
		int k = 3;
		MaxNumberInNums mn = new MaxNumberInNums();
		
		long startTime = System.nanoTime();
		CommonTest.printNum(mn.maxSlidingWindow1(nums, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		CommonTest.printNum(mn.maxSlidingWindow2(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(mn.maxSlidingWindow3(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(mn.maxSlidingWindow4(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(mn.maxSlidingWindow5(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

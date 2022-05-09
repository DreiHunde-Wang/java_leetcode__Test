package src.slidewindowtest;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

import src.numsorttest.CommonTest;

/**
 * ����һ������ nums �ͻ������ڵĴ�С k�����ҳ����л�������������ֵ��
 * @author Dreihunde
 *
 */
public class MaxNumberInNums {
	//method 1 �����Ƚ� O(kn) O(1)
    public int[] maxSlidingWindow1(int[] nums, int k) {
        //�߽��ж�
        if (nums.length == 0)
            return new int[0];
        //����ֵ
        int[] rev = new int[nums.length - k + 1];
        //�������ұ���
        for (int i = 0; i < nums.length - k + 1; i++) {
            //����ÿ���������ڵ����ֵ
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
    //method 2 �����Ƚ� + �Ż� O(n) O(1)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        //�߽��ж�
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //����ֵ
        int[] rev = new int[n - k + 1];
        rev[0] = maxInNum(nums, 0, k);
        //�������ұ���
        for (int i = 1; i < n - k + 1; i++) {
            //����µ�ֵ�ȵ�ǰ�����ֵ����Ϊ�µ����ֵ
            if (nums[i + k - 1] >= rev[i - 1]) {
                rev[i] = nums[i + k - 1];
            } else {
                //������������Ϊ��һ�����ֵ����ô����Ѱ�Ҵ��ڵ����ֵ�����򴰿����ֵ����
                if (nums[i - 1] == rev[i - 1]) {
                    rev[i] = maxInNum(nums, i, i + k);
                } else {
                    rev[i] = rev[i - 1];
                }
            }
        }
        return rev;
    }

    //method 3 ���ȶ��� O(nlogn) O(n)
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //���������(�Ѷ����ֵ�������ֵ���Ľڵ�)
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		//������ֵ�������ֵ�����������������
        		return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
        	};
		});
        //�ѵ�����Ϊk
        for (int i = 0; i < k; i++) {
            heap.offer(new int[]{nums[i], i});
        }
        int[] rev = new int[n - k + 1];
        //ǰk��ֵ�����ֵΪ�Ѷ�
        rev[0] = heap.peek()[0];
        for (int i = k; i < n; i++) {
            //�����ֵ���
            heap.offer(new int[]{nums[i], i});
            //ɾ����������i - k�Լ�֮ǰ�Ľڵ�
            while (heap.peek()[1] <= i - k) {
                heap.poll();
            }
            //��ӶѶ�ֵ������
            rev[i - k + 1] = heap.peek()[0];
        }

        return rev;
    }

    //method 4 ����������Ϊ�ݼ����� O(n) O(k)
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];
        //����˫�˶���
        Deque<Integer> deque = new LinkedList<Integer>();
        //����ǰk���������ֵ�Ķ�Ӧ���������ף�����������Ϊ�ݼ�����
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        //ǰk�����Ĵ�Ϊ����(Ŀǰ�����ֵ)
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            //�����б�Ϊ�ݼ�����
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            //������ֵС�ڵ���i - k�����뿪���ڣ�����ɾ��
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            //�����ǵ�ǰ�����ֵ
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    //method 5 �ֿ� + Ԥ���� O(n) O(n)
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

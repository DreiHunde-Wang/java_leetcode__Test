package numsearch;

import java.util.Arrays;
import java.util.PriorityQueue;

import numsorttest.CommonTest;

public class FindKMinNumber {
	//method 1 ÅÅÐò+Êä³ö O(nlogn) O(logn)
    public int[] getLeastNumbers1(int[] arr, int k) {
        Arrays.sort(arr);
        int[] rev = new int[k];
        for (int i = 0; i < k; i++) {
            rev[i] = arr[i];
        }
        return rev;
    }

    //method 2 heap
    public int[] getLeastNumbers2(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || k == 0)
            return new int[0];

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < k; i++) {
            heap.offer(arr[i]);
        }

        for (int j = k; j < n; j++) {
            if (heap.peek() > arr[j]) {
                heap.poll();
                heap.offer(arr[j]);
            }
        }
        int[] rev = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            rev[index] = heap.poll();
            index++;
        }

        return rev;
    }
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {2, 5, 6, 1, 3, 5, 7};
		int k = 5;
		FindKMinNumber fk = new FindKMinNumber();
		long startTime = System.nanoTime();
		CommonTest.printNum(fk.getLeastNumbers1(nums, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(fk.getLeastNumbers2(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

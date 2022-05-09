package src.numsearch;

import java.util.PriorityQueue;

/**
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 * @author Dreihunde
 *
 */
public class FindNumberInDictOrder {
	//method 1 优先队列 O(nlogk) O(k) 超时 
    public int findKthNumber1(int n, int k) {
        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> (b.compareTo(a)));
        for (int i = 1; i <= n; i++) {
            if (i <= k) {
                heap.offer(Integer.toString(i));
            } else {
                if (heap.peek().compareTo(Integer.toString(i)) > 0) {
                    heap.poll();
                    heap.offer(Integer.toString(i));
                }
            }
        }
        return Integer.parseInt(heap.peek());
    }

    //method 2 字典树 O((logn) ^ 2) O(1)  
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

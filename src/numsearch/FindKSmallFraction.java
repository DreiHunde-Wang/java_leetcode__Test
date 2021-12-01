package numsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import printtreenode.ComnTest;
import printtreenode.TreeNode;

/**
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * 对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 * @author Dreihunde
 *
 */
public class FindKSmallFraction {
	//method 1 线性查找 + 二分阈值
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        int n = arr.length;
        double left = 0.0;
        double right = 1.0;
        while (true) {
            int count = 0;
            int x = 0;
            int y = 1;
            int i = 0;
            double mid = (left + right) / 2;
            for (int j = 1; j < n; j++) {
                while ((double) arr[i] / arr[j] < mid) {
                    if (arr[i] * y > x * arr[j]) {
                        x = arr[i];
                        y = arr[j];
                    }
                    i++;
                }
                count += i;
            }

            if (count == k) {
                return new int[]{x, y};
            } else if (count < k) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }

    //method 2 api
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                list.add(new int[]{arr[i], arr[j]});
            }
        }

        Collections.sort(list, (x, y)->(x[0] * y[1] - y[0] * x[1]));
        return list.get(k - 1);
    }

    //method 3 heap
    public int[] kthSmallestPrimeFraction3(int[] arr, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> (arr[x[0]] * arr[y[1]] - arr[x[1]] * arr[y[0]]));

        for (int j = 1; j < arr.length; j++) {
            heap.offer(new int[]{0, j});
        }

        for (int i = 1; i < k; i++) {
            int[] temp = heap.poll();
            int x = temp[0];
            int y = temp[1];
            if (x + 1 < arr.length) {
                heap.offer(new int[]{x + 1, y});
            }
        }

        return new int[]{arr[heap.peek()[0]], arr[heap.peek()[1]]};
    }
    
	private static void printNum(int[] arr) {
		for (int a : arr) {
			System.out.print(a + " ");
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		FindKSmallFraction fk = new FindKSmallFraction();
		int[] arr = new int[] {1,2,3,5};
		int k = 3;
		
		long startTime = System.nanoTime();
		printNum(fk.kthSmallestPrimeFraction1(arr, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		printNum(fk.kthSmallestPrimeFraction2(arr, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		printNum(fk.kthSmallestPrimeFraction3(arr, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

package classdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * @author Dreihunde
 *
 */
//method 1 链表二分插入  插入:O(logn) 取值:O(1) 空间： O(n)
class MedianFinder {
    int size;
    List<Integer> list;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.size = 0;
        this.list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        if (list.isEmpty()) {
            list.add(num);
            size++;
        } else {
            Integer target = Integer.valueOf(num);
            int left = 0;
            int right = size - 1;
            int index = binarySearch(left, right, target);
            list.add(index, target);
            size++;
        }
    }
    
    public double findMedian() {
        if ((size & 1) == 1) {
            return (double) list.get(size / 2);
        } else {
            return (double) (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
        }
    }

    private int binarySearch(int left, int right, Integer target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).equals(target)) {
                return mid;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(6);
		mf.addNum(10);
		System.out.println(mf.findMedian());
		mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(6);
		System.out.println(mf.findMedian());
	}
}
//method 2 小顶堆和大顶堆  插入:O(logn) 取值:O(1) 空间：O(n)
class MedianFinder2 {
    Queue<Integer> smallHeap;
    Queue<Integer> bigHeap;
    int size;

    /** initialize your data structure here. */
    public MedianFinder2() {
        this.smallHeap = new PriorityQueue<>();//保存较大的
        this.bigHeap = new PriorityQueue<>((a, b) -> (b - a));//保存较小的
        this.size = 0;
    }
    
    public void addNum(int num) {
        if (bigHeap.size() != smallHeap.size()) {
            smallHeap.offer(num);
            bigHeap.offer(smallHeap.poll());
        } else {
            bigHeap.offer(num);
            smallHeap.offer(bigHeap.poll());
        }
        this.size++;
    }
    
    public double findMedian() {
        if ((size & 1) == 1) {
            return (double) smallHeap.peek();
        } else {
            return (double) (smallHeap.peek() + bigHeap.peek()) / 2;
        }
    }
}

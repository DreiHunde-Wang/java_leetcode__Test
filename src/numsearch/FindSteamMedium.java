package numsearch;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * @author Dreihunde
 *
 */
public class FindSteamMedium {
	Queue<Integer> small, large;
	public FindSteamMedium() {
        //大顶堆保存小数字
        small = new PriorityQueue<Integer>((a, b) -> (b - a));
        //小顶堆保存大数字
        large = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (small.size() != large.size()) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());  
        }
    }
    
    public double findMedian() {
        if (small.size() != large.size()) {
            return (double) large.peek();
        } else {
            return (double) (small.peek() + large.peek()) / 2;
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindSteamMedium fs = new FindSteamMedium();
		fs.addNum(2);
		fs.addNum(3);
		fs.addNum(1);
		System.out.println(fs.findMedian());

	}

}



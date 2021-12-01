package numsearch;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * ��εõ�һ���������е���λ����������������ж�����������ֵ����ô��λ������������ֵ����֮��λ���м����ֵ��
 * ������������ж���ż������ֵ����ô��λ������������ֵ����֮���м���������ƽ��ֵ��
 * @author Dreihunde
 *
 */
public class FindSteamMedium {
	Queue<Integer> small, large;
	public FindSteamMedium() {
        //�󶥶ѱ���С����
        small = new PriorityQueue<Integer>((a, b) -> (b - a));
        //С���ѱ��������
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



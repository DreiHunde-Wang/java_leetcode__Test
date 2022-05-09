package src.dptest;

import java.util.PriorityQueue;

/**
 * ��һ�������ƻ������һ�� n �죬ÿ�춼���Գ������ɸ�ƻ�����ڵ� i �죬���ϻ᳤�� apples[i] ��ƻ����
 * ��Щƻ�������� days[i] ���Ҳ����˵���� i + days[i] ��ʱ�����ã�����޷�ʳ�á�Ҳ��������ô���죬
 * ���ϲ��᳤���µ�ƻ������ʱ�� apples[i] == 0 �� days[i] == 0 ��ʾ��
 * �����ÿ�� ��� ��һ��ƻ������֤Ӫ�����⡣ע�⣬��������� n ��֮�������ƻ����
 * ������������Ϊ n ���������� days �� apples ����������ԳԵ���ƻ���������Ŀ��
 * @author Dreihunde
 *
 */
public class MaxEatenApple {
	//method 1 ���ȶ��� O(nlogn) O(n)
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        //����С���ѣ�int[0]��ʾ�������ڣ�int[1]��ʾƻ������
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int ans = 0;
        int i = 0;
        while (i < n || !heap.isEmpty()) {
            //�����ǰ������ƻ������
            if (i < n && apples[i] > 0)
                heap.offer(new int[]{i + days[i], apples[i]});
            //��ƻ�����û��߳Թ���Ƴ�����
            while(!heap.isEmpty() && (heap.peek()[0] <= i || heap.peek()[1] <= 0)) {
                heap.poll();
            }
            //��ƻ��
            if (!heap.isEmpty()) {
                heap.peek()[1]--;
                ans++;
            }
            i++;
        }
        return ans;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] apples = new int[] {1,2,3,5,2};
		int[] days = new int[] {3,2,1,4,2};
		
		MaxEatenApple me = new MaxEatenApple();
		System.out.println(me.eatenApples(apples, days));

	}

}

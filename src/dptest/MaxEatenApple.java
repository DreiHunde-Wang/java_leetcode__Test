package src.dptest;

import java.util.PriorityQueue;

/**
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
 * 这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，
 * 树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * @author Dreihunde
 *
 */
public class MaxEatenApple {
	//method 1 优先队列 O(nlogn) O(n)
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        //构建小根堆，int[0]表示腐烂日期，int[1]表示苹果数量
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int ans = 0;
        int i = 0;
        while (i < n || !heap.isEmpty()) {
            //如果当前天数有苹果生成
            if (i < n && apples[i] > 0)
                heap.offer(new int[]{i + days[i], apples[i]});
            //当苹果腐烂或者吃光后，移出队列
            while(!heap.isEmpty() && (heap.peek()[0] <= i || heap.peek()[1] <= 0)) {
                heap.poll();
            }
            //吃苹果
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

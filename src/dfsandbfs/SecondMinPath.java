package src.dfsandbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 城市用一�? 双向连�?? 图表示，图中�? n 个节点，�? 1 �? n 编号（包�? 1 �? n）�?�图中的边用�?个二维整数数�? edges 表示，其中每�? edges[i] = [ui, vi] 表示�?条节点 ui 和节点 vi 之间的双向连通边。每组节点对�? �?多一�? 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟�?
 * 每个节点都有�?个交通信号灯，每 change 分钟改变�?次，从绿色变成红色，再由红色变成绿色，循环往复�?�所有信号灯都 同�? 改变。你可以�? 任何时�?? 进入某个节点，但�? 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是�? 绿色 ，你 不能 在节点等待，必须离开�?
 * 第二小的�? 是 严格大�? �?小�?�的�?有�?�中�?小的值�??
 * 例如，[2, 3, 4] 中第二小的�?�是 3 ，�?? [2, 2, 4] 中第二小的�?�是 4 �?
 * 给你 n、edges、time �? change ，返回从节点 1 到节�? n �?要的 第二短时�? �?
 * 注意�?
 * 你可�? 任意�? 穿过任意顶点，包�? 1 �? n �?
 * 你可以假设在 启程�? ，所有信号灯刚刚变成 绿色 �?
 * https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/
 * @author Dreihunde
 *
 */
public class SecondMinPath {
	//method 1 广度优先 O(n + e) 其中 n是图的节点数，e是图的边数�?? O(n + e)
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        //节点1-n,0为空节点
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        //保存每个节点能到达的节点
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // path[i][0] 表示�? 1 �? i 的最短路长度，path[i][1] 表示�? 1 �? i 的严格次短路长度
        int[][] path = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            for (int next : graph[cur]) {
                if (len + 1 < path[next][0]) {
                    path[next][0] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ret % (2 * change) >= change) {
                ret = ret + (2 * change - ret % (2 * change));
            }
            ret = ret + time;
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

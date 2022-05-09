package src.dfsandbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你�?个有 n 个服务器的计算机网络，服务器编号为�?0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息�?�再给你�?个长度为 n 且下标从 0 �?始的整数数组 patience �?
 * 题目保证�?有服务器都是 相�?� 的，也就是说一个信息从任意服务器出发，都可以�?�过这些信息线路直接或间接地到达任何其他服务器�??
 * 编号�? 0 的服务器�? 主 服务器，其他服务器�? 数据 服务器�?�每个数据服务器都要向主服务器发送信息，并等待回复�??
 * 信息在服务器之间�? �?优 线路传输，也就是说每个信息都会�? �?少时间 到达主服务器�?�主服务器会处理 �?有 新到达的信息并 立即 按照每条信息来时的路�? 反方�? 发�?�回复信息�??
 * �? 0 秒的�?始，�?有数据服务器都会发�?�各自需要处理的信息�?
 * 从第 1 秒开始，每 一秒最 �?始 时，每个数据服务器都会�?查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）�?
 * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息�??
 * 数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，数据服务器 i 在上�?次发送信息给主服务器后的 patience[i] �? 后 会重发�?条信息给主服务器�?
 * 否则，该数据服务器 不会重发 信息�??
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变�? 空闲 状�?��??
 * 请返回计算机网络变为 空闲 状�?�的 �?早秒数 �??
 * 链接：https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle
 * @author Dreihunde
 *
 */
public class NetworkBecomesIdle {
	//method 1 bfs (n + m) O(n + m) n 为节点的数目，m为edge数组的大�?
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            lists[edge[0]].add(edge[1]);
            lists[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int dist = 1;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int v : lists[cur]) {
                    if(visited[v]) {
                        continue;
                    }
                    queue.offer(v);
                    int time = patience[v] * ((2 * dist - 1) / patience[v]) + 2 * dist + 1;
                    ans = Math.max(ans, time);
                    visited[v] = true;
                }
            }
            dist++;
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

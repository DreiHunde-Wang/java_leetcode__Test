package src.numsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 你有 k 个服务器，编号为 0 �? k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过�?个请求 �?�请求分配到服务器的规则如下�?
 * 第 i （序号从 0 �?始）个请求到达�??
 * 如果�?有服务器都已被占据，那么该请求被舍弃（完全不处理）�??
 * 如果第�?(i % k) 个服务器空闲，那么对应服务器会处理该请求�?
 * 否则，将请求安排给下�?个空闲的服务器（服务器构成一个环，必要的话可能从�? 0 个服务器�?始继续找下一个空闲的服务器）�?
 * 比方说，如果�? i 个服务器在忙，那么会查看�? (i+1) 个服务器，第 (i+2) 个服务器等等�?
 * 给你�?�? 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所�?要的时间）�??
 * 你的任务是找�? �?繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的�?
 * 请你返回包含�?有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表�??
 * 链接：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests
 * @author Dreihunde
 *
 */
public class BusiestServer {
	//method 1 模拟 O(n^2 + k) O(k)
    public List<Integer> busiestServers1(int k, int[] arrival, int[] load) {
        int[][] res = new int[k][2];

        int n = arrival.length;
        for (int i = 0; i < n; i++) {
            int cur = i % k;
            int count = 0;
            boolean isContinue = false;
            while (arrival[i] < res[cur][0]) {
                cur++;
                count++;
                if (cur == k) {
                    cur = 0;
                }
                if (count == k + 1) {
                    isContinue = true;
                    break;
                }
            }
            if (isContinue) {
                continue;
            }
            res[cur][0] = arrival[i] + load[i];
            res[cur][1]++;
        }

        List<Integer> list = new ArrayList<>();
        int maxCount = -1;
        for (int[] r : res) {
            maxCount = Math.max(r[1], maxCount);
        }
        for (int i = 0; i < k; i++) {
            if (res[i][1] == maxCount) {
                list.add(i);
            }
        }
        return list;
    }

    //method 2 模拟+ 有序集合+ 优先队列 O((k + n)logk) O(k)
    public List<Integer> busiestServers2(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<Integer>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            Integer p = available.ceiling(i % k);
            if (p == null) {
                p = available.first();
            }
            requests[p]++;
            busy.offer(new int[]{arrival[i] + load[i], p});
            available.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }

    //method 3 模拟+ 有序集合+ 优先队列 O((k + n)logk) O(k)
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<Integer> available = new PriorityQueue<Integer>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            available.offer(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int id = busy.peek()[1];
                busy.poll();
                available.offer(i + ((id - i) % k + k) % k); // 保证得到的是�?个不小于 i 的且�? id 同余的数
            }
            if (available.isEmpty()) {
                continue;
            }
            int server = available.poll() % k;
            requests[server]++;
            busy.offer(new int[]{arrival[i] + load[i], server});
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }


}

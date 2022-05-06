package simulationtest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * 请你实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
 * @author Dreihunde
 *
 */
public class RecentCounter {
	List<Integer> list;
    int lt;
    Deque<Integer> queue;
    public RecentCounter() {
        list = new ArrayList<>();
        lt = 0;
        queue = new ArrayDeque<>();
    }

    //method 1 模拟 O(n) O(n)
    public int ping1(int t) {
        int count = 1;
        list.add(t);
        int n = list.size();
        int cur = list.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (list.get(i) >= cur - 3000) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    //method 2 双指针 O(1) O(n)
    public int ping2(int t) {
        list.add(t);
        int n = list.size();
        int rt = n - 1;
        while (lt < rt && list.get(lt) < list.get(rt) - 3000) {
            lt++;
        }
        return rt - lt + 1;
    }

    //method 3 队列 O(1) O(L) L = 3000
    public int ping(int t) {
        queue.offer(t);
        while (!queue.isEmpty() && queue.peekFirst() < queue.peekLast() - 3000) {
            queue.poll();
        }
        return queue.size();
    }

}

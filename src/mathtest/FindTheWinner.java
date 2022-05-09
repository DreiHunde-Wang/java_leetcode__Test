package src.mathtest;

import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

/**
 * 共有 n 名小伙伴�?起做游戏。小伙伴们围成一圈，�? 顺时针顺�? �? 1 �? n 编号�?
 * 确切地说，从�? i 名小伙伴顺时针移动一位会到达�? (i+1) 名小伙伴的位置，其中 1 <= i < n ，从�? n 名小伙伴顺时针移动一位会回到�? 1 名小伙伴的位置�??
 * 游戏遵循如下规则�?
 * 从第 1 名小伙伴�?在位�? �?�? �?
 * 沿着顺时针方向数 k 名小伙伴，计数时�?�? 包含 起始时的那位小伙伴�?��?�个绕圈进行计数，一些小伙伴可能会被数过不止�?次�??
 * 你数到的�?后一名小伙伴�?要离�?圈子，并视作输掉游戏�?
 * 如果圈子中仍然有不止�?名小伙伴，从刚刚输掉的小伙伴�? 顺时针下�?�? 小伙�? �?始，回到步骤 2 继续执行�?
 * 否则，圈子中�?后一名小伙伴赢得游戏�?
 * 给你参与游戏的小伙伴总数 n ，和�?个整�? k ，返回游戏的获胜者�??
 * 链接：https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game
 * @author Dreihunde
 *
 */
public class FindTheWinner {
	
	//method 1 模拟+遍历 O(n^2) O(n)
    public int findTheWinner1(int n, int k) {
        boolean[] visited = new boolean[n];
        int count = 0;
        int res = n;
        int index = 0;
        while (res > 1) {
            if (index == n) {
                index = 0;
            }
            if (visited[index]) {
                index++;
                continue;
            } else {
                count++;
                if (count == k) {
                    while (visited[index]) {
                        index++;
                        if (index == n) {
                            index = 0;
                        }
                    }
                visited[index] = true;
                res--;
                count = 0;
                }
            }
            index++;
        } 
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return i + 1;
            }
        }
        return index;
    }

    //method 2 模拟+队列 O(nk) O(n) 
    public int findTheWinner2(int n, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek().intValue();
    }

    //method 3 math+递归 O(n) O(n) f(n, k) = (k + f(n - 1, k) - 1) mod n + 1
    public int findTheWinner3(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (k + findTheWinner(n - 1, k) - 1) % n + 1;
    }

    //method 4 math+迭代 O(n) O(1)
    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        int dp = 1;
        for (int i = 2; i <= n; i++) {
            dp = (k + dp - 1) % i + 1;
        }
        return dp;
    }
	
	@Test
	public void test() {
		findTheWinner(5, 2);
	}

}

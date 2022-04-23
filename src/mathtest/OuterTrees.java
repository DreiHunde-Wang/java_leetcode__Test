package mathtest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * ��һ����ά�Ļ�԰�У���һЩ�� (x, y) �����ʾ���������ڰ�װ����ʮ�ְ������������������̵�����Χ�����е�����ֻ�е����е����������Ӱ�Χʱ����԰����Χ��դ����
 * ����Ҫ�ҵ�����λ��դ���߽��ϵ��������ꡣ
 * ���ӣ�https://leetcode-cn.com/problems/erect-the-fence
 * @author Dreihunde
 *
 */
public class OuterTrees {
	//method 1 Jarvis�㷨 O(n^2) O(1)
    //Jarvis �㷨������뷨�ǳ��򵥡����ȱ���Ҫ��͹���ϵ�ĳһ�㿪ʼ������Ӹ����㼯������ߵĵ㿪ʼ�����������һ��A1��
    //Ȼ��ѡ��A2��ʹ�����е㶼������A1,A2���󷽻����ҷ�������ÿ��ѡ���󷽣���Ҫ�Ƚ����е���A1Ϊԭ��ļ�����Ƕȡ�
    public int[][] outerTrees1(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int leftMost = 0;
        for (int i = 0; i < n; i++) {
            if (trees[i][0] < trees[leftMost][0]) {
                leftMost = i;
            }
        }

        List<int[]> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int p = leftMost;
        do {
            int q = (p + 1) % n;
            for (int r = 0; r < n; r++) {
                /* ��� r �� pq ���Ҳ࣬�� q = r */
                if (cross(trees[p], trees[q], trees[r]) < 0) {
                    q = r;
                }
            }
            /* �Ƿ���ڵ� i, ʹ�� p ��q ��i ��ͬһ��ֱ���� */
            for (int i = 0; i < n; i++) {
                if (visited[i] || i == p || i == q) {
                    continue;
                }
                if (cross(trees[p], trees[q], trees[i]) == 0) {
                    res.add(trees[i]);
                    visited[i] = true;
                }
            }
            if (!visited[q]) {
                res.add(trees[q]);
                visited[q] = true;
            }
            p = q;
        } while (p != leftMost);
        return res.toArray(new int[][]{});
    }


    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }

    //method 2 Graham �㷨 O(nlogn) O(n) 
    //��������ľ���ʵ��Ϊ������ѡ��һ��͹���ϵĳ�ʼ��bottom������ѡ�� y ������С�ĵ�Ϊ��ʼ�㣬���ǿ��Կ϶�bottom һ����͹���ϡ� 
    //�������㼯������Ե��� bottom Ϊԭ��ļ��Ǵ�С��������
    public int[][] outerTrees2(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int bottom = 0;
        /* �ҵ� y ��С�ĵ� bottom*/
        for (int i = 0; i < n; i++) {
            if (trees[i][1] < trees[bottom][1]) {
                bottom = i;
            }
        }
        swap(trees, bottom, 0);
        /* �� bottom ԭ�㣬���ռ�����ĽǶȴ�С�������� */
        Arrays.sort(trees, 1, n, (a, b) -> {
            int diff = cross(trees[0], a, b) - cross(trees[0], b, a);
            if (diff == 0) {
                return distance(trees[0], a) - distance(trees[0], b);
            } else {
                return -diff;
            }
        });
        /* ����͹���������ͬһ��ֱ�ߵ�Ԫ�ذ��վ����С����������� */
        int r = n - 1;
        while (r >= 0 && cross(trees[0], trees[n - 1], trees[r]) == 0) {
            r--;
        }
        for (int l = r + 1, h = n - 1; l < h; l++, h--) {
            swap(trees, l, h);
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        stack.push(1);
        for (int i = 2; i < n; i++) {
            int top = stack.pop();
            /* �����ǰԪ����ջ��������Ԫ�ع��ɵ�����˳ʱ����ת���򵯳�ջ��Ԫ�� */
            while (!stack.isEmpty() && cross(trees[stack.peek()], trees[top], trees[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(i);
        }

        int size = stack.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i] = trees[stack.pop()];
        }
        return res;
    }

    public int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    public void swap(int[][] trees, int i, int j) {
        int temp0 = trees[i][0], temp1 = trees[i][1];
        trees[i][0] = trees[j][0];
        trees[i][1] = trees[j][1];
        trees[j][0] = temp0;
        trees[j][1] = temp1;
    }

    //method 3 Andrew �㷨 O(nlogn) O(n)
    //�Ȱ������Ҵӵ͵�������Ȼ��ֱ����͹�����ϰ벿�ֺ��°벿��
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        /* ���� x ��С����������� x ��ͬ������ y �Ĵ�С�������� */
        Arrays.sort(trees, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<Integer> hull = new ArrayList<Integer>();
        boolean[] used = new boolean[n];
        /* hull[0] ��Ҫ��ջ���Σ������б�� */
        hull.add(0);
        /* ���͹�����°벿�� */
        for (int i = 1; i < n; i++) {
            while (hull.size() > 1 && cross(trees[hull.get(hull.size() - 2)], trees[hull.get(hull.size() - 1)], trees[i]) < 0) {
                used[hull.get(hull.size() - 1)] = false;
                hull.remove(hull.size() - 1);
            }
            used[i] = true;
            hull.add(i);
        }
        int m = hull.size();
        /* ���͹�����ϰ벿�� */
        for (int i = n - 2; i >= 0; i--) {
            if (!used[i]) {
                while (hull.size() > m && cross(trees[hull.get(hull.size() - 2)], trees[hull.get(hull.size() - 1)], trees[i]) < 0) {
                    used[hull.get(hull.size() - 1)] = false;
                    hull.remove(hull.size() - 1);
                }
                used[i] = true;
                hull.add(i);
            }
        }
        /* hull[0] ͬʱ����͹�����ϰ벿�ּ�⣬�����ȥ���ظ��� hull[0] */
        hull.remove(hull.size() - 1);
        int size = hull.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i] = trees[hull.get(i)];
        }
        return res;
    }

}

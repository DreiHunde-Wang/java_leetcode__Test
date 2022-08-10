package src.numsubsum;

import java.util.ArrayDeque;

/**
 * 给定非负整数数组heights，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为1。
 * https://leetcode.cn/problems/0ynMMM/
 */
public class MaxRegion {
    //method 1 单调栈 O(n) O(n)
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int ans = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //当栈顶元素大于当前元素，则说明当前元素为栈顶元素的右边界，开始向左寻找左边界
            while (!queue.isEmpty() && heights[queue.peekLast()] > heights[i]) {
                int top = queue.pollLast();
                int h = heights[top];
                //右边界i - 左边界queue.peekLast() - 1
                int w = queue.isEmpty() ? i : i - queue.peekLast() - 1;
                ans = Math.max(ans, w*h);
            }
            queue.offerLast(i);
        }
        //剩余的元素右边界都为n
        while(!queue.isEmpty()) {
            int top = queue.pollLast();
            int h = heights[top];
            int w = queue.isEmpty() ? n : n - queue.peekLast() - 1;
            ans = Math.max(ans, w*h);
        }
        return ans;

    }

    //method 2 数组模拟单调栈 O(n) O(n)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int ans = 0;
        int top = -1;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            //当栈顶元素大于当前元素，则说明当前元素为栈顶元素的右边界，开始向左寻找左边界
            while (top != -1 && heights[stack[top]] > heights[i]) {
                int h = heights[stack[top]];
                top--;
                //右边界i - 左边界queue.peekLast() - 1
                int w = top == -1 ? i : i - stack[top] - 1;
                ans = Math.max(ans, w*h);
            }
            stack[++top] = i;
        }
        //剩余的元素右边界都为n
        while(top != -1) {
            int h = heights[stack[top]];
            top--;
            int w = top == -1 ? n : n - stack[top] - 1;
            ans = Math.max(ans, w*h);
        }
        return ans;
    }
}

package src.numsearch;

/**
 * 给定�?个长度为 n 的整数数组 height 。有 n 条垂线，�? i 条线的两个端点是 (i, 0) 和�?(i, height[i]) �?
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳�?多的水�??
 * 返回容器可以储存的最大水量�??
 * 说明：你不能倾斜容器�?
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * @author Dreihunde
 *
 */
public class MaxArea {
	// method 1 暴力 O(n^2) O(1)
    public int maxArea1(int[] height) {
        int n = height.length;
        int maxSize = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                maxSize = Math.max(maxSize, (j - i) * Math.min(height[j], height[i]));
            }
        }
        return maxSize;
    }

    // method 2 双指�? O(n) O(1)
    public int maxArea(int[] height) {
        int n = height.length;
        int maxSize = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            maxSize = Math.max(Math.min(height[left], height[right]) * (right - left), maxSize);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }

        }
        return maxSize;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

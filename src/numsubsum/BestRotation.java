package numsubsum;

/**
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，
 * 这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。
 * 这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、
 * 2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
 * @author Dreihunde
 *
 */
public class BestRotation {
	//method 1 暴力 O(n^2) O(1)
    public int bestRotation1(int[] nums) {
        int n = nums.length;
        int score = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            int tScore = getRotationScore(nums, i);
            if (tScore > score) {
                score = tScore;
                index = i;
            }
        }
        return index;
    }

    private int getRotationScore(int[] nums, int k) {
        int n = nums.length;
        int lt = 0;
        int rt = k;
        int score = 0;
        while (lt < n) {
            if (rt == n) {
                rt = 0;
            }
            if (nums[rt] <= lt) {
                score++;
            }
            rt++;
            lt++;
        }
        return score;
    }

    //method 2 差分 O(n) O(n)
    //i−(n−1)⩽k⩽i−nums[i]
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

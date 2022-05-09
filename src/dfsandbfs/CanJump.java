package src.dfsandbfs;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * @author Dreihunde
 *
 */
public class CanJump {
	//method 1 dfs O(n^2) O(n)
    boolean canReach;
    public boolean canJump1(int[] nums) {
        canReach = false;
        int n = nums.length;
        boolean[] visited = new boolean[n];
        dfs(nums, 0, visited);
        return canReach;
    }

    public void dfs(int[] nums, int cur, boolean[] visited) {
        if (cur >= nums.length - 1) {
            visited[nums.length - 1] = true;
            canReach = true;
            return;
        }
        if (visited[cur] || nums[cur] == 0 || canReach) {
            return;
        }
        
        visited[cur] = true;
        for (int i =  nums[cur]; i >= 1; i--) {
            dfs(nums, cur + i, visited);
        }
    }

    //method 2 贪心 O(n) O(1)
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightMost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}

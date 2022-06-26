package src.dfsandbfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * @author Dreihunde
 *
 */
public class SubSet {
	//method 1 dfs O(n*2^n) O(n)
    List<List<Integer>> ans;
    public List<List<Integer>> subsets1(int[] nums) {
        ans = new ArrayList<>();
        ans.add(new ArrayList());
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.add(nums[i]);
            dfs(nums, i, tempList);
        }
        return ans;
    }

    public void dfs(int[] nums, int cur, ArrayList<Integer> tempList) {
        int n = nums.length;
        if (cur == n) {
            return;
        }
        ans.add(new ArrayList<>(tempList));
        for (int i = cur + 1; i < n; i++) {
            tempList.add(nums[i]);
            dfs(nums, i, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    //method 2 dfs O(n*2^n) O(n)
    List<Integer> t = new ArrayList<Integer>();
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        dfs2(0, nums);
        return ans;
    }

    public void dfs2(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        //选择当前位置
        t.add(nums[cur]);
        dfs2(cur + 1, nums);
        t.remove(t.size() - 1);
        //不选择当前位置
        dfs2(cur + 1, nums);
    }
    

}

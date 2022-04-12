package dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * @author Dreihunde
 *
 */
public class CombinationSum {
	//method 1 排序+dfs+回溯+剪枝 O(nlogn + S) O(target) S为所有可行解的长度和
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        Arrays.sort(candidates);
        ArrayList<Integer> list = new ArrayList<>();
        dfs(candidates, target, 0, list);
        return ans;
    }

    private void dfs(int[] candidates, int target, int cur, ArrayList<Integer> list) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(list));
            return;
        } else if (target < 0) {
            return;
        }
        for (int i = cur; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate > target) {
                return;
            }
            list.add(candidate);
            dfs(candidates, target - candidate, i, list);
            list.remove(list.size() - 1);
        }
    }

}

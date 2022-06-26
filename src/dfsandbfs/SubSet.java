package src.dfsandbfs;

import java.util.ArrayList;
import java.util.List;

/**
 * ����һ���������� nums �������е�Ԫ�� ������ͬ �����ظ��������п��ܵ��Ӽ����ݼ�����
 * �⼯ ���� �����ظ����Ӽ�������԰� ����˳�� ���ؽ⼯��
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
        //ѡ��ǰλ��
        t.add(nums[cur]);
        dfs2(cur + 1, nums);
        t.remove(t.size() - 1);
        //��ѡ��ǰλ��
        dfs2(cur + 1, nums);
    }
    

}

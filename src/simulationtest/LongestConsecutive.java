package src.simulationtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
 * @author Dreihunde
 *
 */
public class LongestConsecutive {
	//method 1 set去重+定位 O(n) O(n)
    public int longestConsecutive1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxLen = 0;
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int cur = num;
            int len = 1;

            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    //method 2 并查集 O(n) O(n)
    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        UF uf = new UF(nums.length);

        for (int i = 0; i < nums.length; i++) {
            // 存在重复元素，跳过
            if (map.containsKey(nums[i])) continue;

            if (map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }
            if (map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }
            map.put(nums[i], i);        
        }
        return uf.getMaxConnectSize();
    }

}
class UF {
    private int[] parent;
    private int[] size;

    public UF(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        // 注意 别写反了
        size[rootQ] += size[rootP];
    }
    // get root id
    private int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    public int getMaxConnectSize() {
        int maxSize = 0;
        for (int i = 0; i < parent.length; i++) {
            if (i == parent[i]) {
                maxSize = Math.max(maxSize, size[i]);
            }
        }
        return maxSize;
    }
}

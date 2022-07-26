package src.dptest;

import src.printtreenode.TreeNode;


/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 链接：https://leetcode.cn/problems/house-robber-iii
 * @author Dreihunde
 *
 */
public class Rob3 {
	//method 1 dfs + dp O(n) O(n)
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] ans = postOrder(root);
        return Math.max(ans[0], ans[1]);
    }


    public int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] l = postOrder(node.left);
        int[] r = postOrder(node.right);

        //不选当前节点
        int val1 = l[1] + r[1];
        //选当前节点
        int val2 = l[0] + r[0] + node.val;
        val2 = Math.max(val1, val2);
        return new int[] {val1, val2};
    }

}

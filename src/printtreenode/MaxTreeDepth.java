package printtreenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数(层数)。
 * 说明: 叶子节点是指没有子节点的节点。
 * @author Dreihunde
 *
 */
public class MaxTreeDepth {
	//method 1 递归
    public static int maxDepth1(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
    }

    //method 2 迭代
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                if (t.left != null)
                    queue.offer(t.left);
                if (t.right != null)
                    queue.offer(t.right);
            }
            level++;
        }

        return level;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

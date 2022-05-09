package src.printtreenode;

import java.util.ArrayDeque;

/**
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 * @author Dreihunde
 *
 */
public class IsEvenOddTree {
	//method 1 层序遍历 O(n) O(n)
    public boolean isEvenOddTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                int value = temp.val;
                if (value % 2 == level % 2) {
                    return false;
                }
                if ( (level % 2 == 0 && pre >= value) || (level % 2 == 1 && pre <= value)) {
                    return false;
                }
                pre = value;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            level++;
        }
        return true;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(10);
		root.right = new TreeNode(4);
		root.left.left = new TreeNode(3);
		root.right.left = new TreeNode(7);
		root.right.left = new TreeNode(9);
		
		IsEvenOddTree ie = new IsEvenOddTree();
		System.out.println(ie.isEvenOddTree(root));

	}

}

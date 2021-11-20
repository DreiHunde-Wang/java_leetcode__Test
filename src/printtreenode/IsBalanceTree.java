package printtreenode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * @author Dreihunde
 *
 */
public class IsBalanceTree {
	 //method 1 自顶而下递归 O(n2)
    public boolean isBalanced1(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //method 2 自底而上递归 O(n)
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Height(root) >= 0;
    }

    private int Height(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = Height(root.left);
        if (leftHeight == -1)
            return -1;

        int rightHeight = Height(root.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
            
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

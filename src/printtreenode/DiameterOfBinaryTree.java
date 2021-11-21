package printtreenode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示
 * @author Dreihunde
 *
 */
public class DiameterOfBinaryTree {
	static int maxLen;
    public static int diameterOfBinaryTree(TreeNode root) {
        maxLen = 1;
        maxdepth(root);
        return maxLen - 1;
    }

    private static int maxdepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = maxdepth(root.left);
        int rightHeight = maxdepth(root.right);
        maxLen = Math.max(maxLen, rightHeight + leftHeight + 1);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[1,2,2,3,4,4,3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		long startTime = System.nanoTime();
		System.out.println(diameterOfBinaryTree(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

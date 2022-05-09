package src.printtreenode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 
 * 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 
 * 如果节点不存在，则返回 NULL。
 * @author Dreihunde
 *
 */
public class SearchTreeNode {
	//method 1 dfs递归
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val) 
            return root;
        TreeNode left = searchBST1(root.left, val);
        TreeNode right = searchBST1(root.right, val);
        return left == null? right : left;
    }

    //method 2 bfs遍历
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null)
            return null;
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t.val == val)
                return t;
            if (t.left != null)
                queue.add(t.left);
            if (t.right != null)
                queue.add(t.right);
        }
        return null;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		int val = 2;
		SearchTreeNode st = new SearchTreeNode();
		long startTime = System.nanoTime();
		TreeNode.printTree(st.searchBST1(root, val));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		TreeNode.printTree(st.searchBST2(root, val));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

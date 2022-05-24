package src.printtreenode;

import java.util.ArrayDeque;

/**
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * https://leetcode.cn/problems/univalued-binary-tree/submissions/
 * @author Dreihunde
 *
 */
public class IsUnivalTree {
	//method 1 dfs1 O(n) O(n)
    static int pivot;
    public static boolean isUnivalTree1(TreeNode root) {
        if (root == null)
            return true;
        pivot = root.val;

        return CurUnivalTree(root);
    }

    private static boolean CurUnivalTree(TreeNode root) {
        if (root == null)
            return true;
        return root.val == pivot && CurUnivalTree(root.left) && CurUnivalTree(root.right);
    }

  //method 2 层序遍历 O(n) O(n)
    public static boolean isUnivalTree2(TreeNode root) {
        if (root == null)
            return true;
        int p = root.val;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.val != p)
                return false;
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return true;
        
    }
    
    //method 3 dfs O(n) O(n)
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.left.val != root.val || !isUnivalTree(root.left)) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.val != root.val || !isUnivalTree(root.right)) {
                return false;
            }
        }
        return true;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(2);
		
		long startTime = System.nanoTime();
		System.out.println(isUnivalTree1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(isUnivalTree2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

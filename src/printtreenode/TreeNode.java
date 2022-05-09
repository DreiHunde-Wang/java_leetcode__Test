package src.printtreenode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode() {
		
	}
	
	public TreeNode(int x) {
		this.val = x;
	}
	
	public static void printTree(TreeNode root) {
		if (root == null) {
			System.out.println("root is null");
		}
		
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		Set<TreeNode> set = new HashSet<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode t = queue.poll();
			if (!set.contains(t)) {
				set.add(t);
			} else {
				break;
			}
			System.out.print(t.val + " ");
			if (t.left != null) 
				queue.offer(t.left);
			if (t.right != null)
				queue.offer(t.right);
		}
		
		System.out.println();
	}


}

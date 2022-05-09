package src.printtreenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下打印二叉树，每一层子节点从左到右打印
 * @author Dreihunde
 *
 */
public class PrintTreeUpToDown {
	public static int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        int[] rev = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rev[i] = list.get(i).intValue();
        }
        
        return rev;

    }
	
	public static void main(String[] args) {
		//[3,9,20,null,null,15,7]
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		long startTime = System.nanoTime();
		int[] rev = levelOrder(root);
		for (int n : rev)
			System.out.print(n + " ");
		long endTime = System.nanoTime();
		System.out.println();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

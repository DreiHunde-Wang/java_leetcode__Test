package printtreenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * @author Dreihunde
 *
 */
public class PrintTreeUpToDownWithLevel {
	public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                ans.get(level).add(temp.val);
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
            level++;
        }

        return ans;


    }
	
	public static void main(String[] args) {
		//[3,9,20,null,null,15,7]
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		long startTime = System.nanoTime();
		System.out.println(levelOrder(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

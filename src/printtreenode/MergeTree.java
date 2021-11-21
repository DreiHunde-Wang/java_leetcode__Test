package printtreenode;

import java.util.ArrayDeque;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。
 * 合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * @author Dreihunde
 *
 */
public class MergeTree {
	//method 1 递归和并
    public static TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        if (root1 != null && root2 != null)
            root1.val += root2.val;
        
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);

        return root1;
    }

    //method 2 迭代和并
    public static TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root1);
        queue.add(root2);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            t1.val += t2.val;

            if (t1.left != null && t2.left != null) {
                queue.offer(t1.left);
                queue.offer(t2.left);
            }
            else if (t1.left == null) {
                t1.left = t2.left;
            } 

            if (t1.right != null && t2.right != null) {
                queue.offer(t1.right);
                queue.offer(t2.right);
            }
            else if (t1.right == null) {
                t1.right = t2.right;
            } 

        }

        return root1;
    }
    
    private static void printTree(TreeNode node) {
    	ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    	
    	queue.offer(node);
    	
    	while (!queue.isEmpty()) {
    		TreeNode temp = queue.poll();
    		System.out.print(temp.val + " ");
    		if (temp.left != null) {
    			queue.offer(temp.left);
    		}
    		if (temp.right != null) {
    			queue.offer(temp.right);
    		}		
    	}

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
		
		TreeNode sub = new TreeNode(2);
		sub.left = new TreeNode(3);
		
		long startTime = System.nanoTime();
		printTree(mergeTrees1(root, sub));
		System.out.println();
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		printTree(mergeTrees2(root, sub));
		System.out.println();
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

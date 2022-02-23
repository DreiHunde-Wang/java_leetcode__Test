package printtreenode;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 * @author Dreihunde
 *
 */
public class SymmetricTree {
	//method 1 递归 O(n) O(n)
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null)
            return true;
        return recur(root.left, root.right);
        
    }

    private static boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null || left.val != right.val)
            return false;
        else 
            return recur(left.left, right.right) && recur(left.right, right.left);
    }

    //method 2 迭代 O(n) O(n)
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetricTree(root.left, root.right);
        
    }

    private static boolean isSymmetricTree(TreeNode L, TreeNode R) {
    	//ArrayDeque不支持元素为null，所以用linkedList实现queue
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(L);
        queue.offer(R);

        while (!queue.isEmpty()) {
            L = queue.poll();
            R = queue.poll();

            if (L == null && R == null)
                continue;
            else if (L == null || R == null || L.val != R.val)
                return false;
            else {
                queue.offer(L.left);
                queue.offer(R.right);
                queue.offer(L.right);
                queue.offer(R.left);
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
		//[1,2,2,3,4,4,3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		long startTime = System.nanoTime();
		System.out.println(isSymmetric1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(isSymmetric2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

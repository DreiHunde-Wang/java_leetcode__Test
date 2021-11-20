package printtreenode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * �����һ������������һ�����������ú���������ľ���
 * ���룺root = [4,2,7,1,3,6,9]
 * �����[4,7,2,9,6,3,1]
 * @author Dreihunde
 *
 */
public class PrintMirrorTree {
	//method 1 �ݹ�
	public static TreeNode mirrorTree1(TreeNode root) {
		if (root == null)
			return null;
		TreeNode left = mirrorTree1(root.left);
		TreeNode right = mirrorTree1(root.right);
		root.left = right;
		root.right = left;
		return root;
	        
	}
	 
	 //method 2 ����
	public static TreeNode mirrorTree2(TreeNode root) {
		if (root == null)
			return null;
		Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            
            if (t.left != null)
                stack.push(t.left);
            if (t.right != null)
                stack.push(t.right);
            
            TreeNode temp = t.left;
		    t.left = t.right;
		    t.right = temp;
        }
	    return root;
	        
	}
	
	
	private static void preOrderPrintTree(TreeNode root) {
		if (root == null) {
//			System.out.print("null" + " ");
			return;
		}
			
		System.out.print(root.val + " ");
		printTree(root.left);
		printTree(root.right);
	}
	
	private static void printTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode t = queue.poll();
			System.out.print(t.val + " ");
			if (t.left != null)
				queue.add(t.left);
			if (t.right != null)
				queue.add(t.right);
		}					
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[4,2,7,1,3,6,9]
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		long startTime = System.nanoTime();
		printTree(mirrorTree1(root));
//		System.out.println(mirrorTree1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		printTree(mirrorTree2(root));
//		System.out.println(mirrorTree2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

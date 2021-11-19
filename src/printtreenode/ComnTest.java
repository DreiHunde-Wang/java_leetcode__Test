package printtreenode;

import printtreenode.PrintTreeUpToDown;

public class ComnTest {
	public static void main(String[] args) {
		//[3,9,20,null,null,15,7]
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

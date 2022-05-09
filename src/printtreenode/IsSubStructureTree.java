package src.printtreenode;

/**
 * ÅÐ¶ÏÊ÷2ÊÇ·ñÎªÊ÷1µÄ×ÓÊ÷
 * @author Dreihunde
 *
 */
public class IsSubStructureTree {
	//method 1 µÝ¹é O(n) O(n)
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if ((A == null) || (B == null))
            return false;
        if (isSameTree(A, B))
            return true;
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    private static boolean isSameTree(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null || A.val != B.val)
            return false;

        return isSameTree(A.left, B.left) && isSameTree(A.right, B.right);
    }
    
    //method 2 µü´ú O(n) O(n)
    
    public static void main(String[] args) {
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
		System.out.println(isSubStructure(root, sub));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
//		System.out.println(isSymmetric2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}
}

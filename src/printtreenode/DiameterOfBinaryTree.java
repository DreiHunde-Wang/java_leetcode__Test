package printtreenode;

/**
 * ����һ�ö�����������Ҫ��������ֱ�����ȡ�һ�ö�������ֱ�������������������·�������е����ֵ������·�����ܴ���Ҳ���ܲ���������㡣
 * ע�⣺�����֮���·��������������֮��ߵ���Ŀ��ʾ
 * @author Dreihunde
 *
 */
public class DiameterOfBinaryTree {
	static int maxLen;
    public static int diameterOfBinaryTree(TreeNode root) {
        maxLen = 1;
        maxdepth(root);
        return maxLen - 1;
    }

    private static int maxdepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = maxdepth(root.left);
        int rightHeight = maxdepth(root.right);
        maxLen = Math.max(maxLen, rightHeight + leftHeight + 1);
        return 1 + Math.max(leftHeight, rightHeight);
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
		
		long startTime = System.nanoTime();
		System.out.println(diameterOfBinaryTree(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

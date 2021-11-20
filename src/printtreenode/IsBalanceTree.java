package printtreenode;

/**
 * ����һ�����������ж����Ƿ��Ǹ߶�ƽ��Ķ�������
 * �����У�һ�ø߶�ƽ�����������Ϊ��
 * һ��������ÿ���ڵ� ���������������ĸ߶Ȳ�ľ���ֵ������ 1 ��
 * @author Dreihunde
 *
 */
public class IsBalanceTree {
	 //method 1 �Զ����µݹ� O(n2)
    public boolean isBalanced1(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //method 2 �Ե׶��ϵݹ� O(n)
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Height(root) >= 0;
    }

    private int Height(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = Height(root.left);
        if (leftHeight == -1)
            return -1;

        int rightHeight = Height(root.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
            
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package src.printtreenode;

import java.util.ArrayDeque;

/**
 * ���������ÿ���ڵ㶼������ͬ��ֵ����ô�ö��������ǵ�ֵ��������
 * ֻ�и��������ǵ�ֵ������ʱ���ŷ��� true�����򷵻� false��
 * @author Dreihunde
 *
 */
public class IsUnivalTree {
	//method 1 �ݹ�
    static int pivot;
    public static boolean isUnivalTree1(TreeNode root) {
        if (root == null)
            return true;
        pivot = root.val;

        return CurUnivalTree(root);
    }

    private static boolean CurUnivalTree(TreeNode root) {
        if (root == null)
            return true;
        return root.val == pivot && CurUnivalTree(root.left) && CurUnivalTree(root.right);
    }

    //method 2 ����
    public static boolean isUnivalTree2(TreeNode root) {
        if (root == null)
            return true;
        int p = root.val;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.val != p)
                return false;
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return true;
        
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(2);
		
		long startTime = System.nanoTime();
		System.out.println(isUnivalTree1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(isUnivalTree2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

package printtreenode;

/**
 * ����һ�����������ҳ�����С��ȡ�
 * ��С����ǴӸ��ڵ㵽���Ҷ�ӽڵ�����·���ϵĽڵ�������
 * ˵����Ҷ�ӽڵ���ָû���ӽڵ�Ľڵ㡣
 * @author Dreihunde
 *
 */
public class MinTreeDepth {
	//method 1 dfs
    public int minDepth1(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int min = Integer.MAX_VALUE;
        if (root.left != null)
            min = Math.min(min, minDepth1(root.left));
        if (root.right != null)
            min = Math.min(min, minDepth1(root.right));
        return 1 + min;
    }

    //method 2 �Ż�
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        int m1 = minDepth2(root.left);
        int m2 = minDepth2(root.right);
        //1.������Ӻ��Һ�����Ϊ�յ������ֱ�ӷ���m1+m2+1
        //2.�������Ϊ�գ����ؽ�С���+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1,m2) + 1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(2);
		
		MinTreeDepth mt = new MinTreeDepth();
				
		long startTime = System.nanoTime();
		System.out.println(mt.minDepth1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(mt.minDepth2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

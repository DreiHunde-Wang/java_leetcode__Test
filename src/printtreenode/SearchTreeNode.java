package src.printtreenode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * ����������������BST���ĸ��ڵ��һ��ֵ�� 
 * ����Ҫ��BST���ҵ��ڵ�ֵ���ڸ���ֵ�Ľڵ㡣 �����Ըýڵ�Ϊ���������� 
 * ����ڵ㲻���ڣ��򷵻� NULL��
 * @author Dreihunde
 *
 */
public class SearchTreeNode {
	//method 1 dfs�ݹ�
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val) 
            return root;
        TreeNode left = searchBST1(root.left, val);
        TreeNode right = searchBST1(root.right, val);
        return left == null? right : left;
    }

    //method 2 bfs����
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null)
            return null;
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t.val == val)
                return t;
            if (t.left != null)
                queue.add(t.left);
            if (t.right != null)
                queue.add(t.right);
        }
        return null;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		int val = 2;
		SearchTreeNode st = new SearchTreeNode();
		long startTime = System.nanoTime();
		TreeNode.printTree(st.searchBST1(root, val));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		TreeNode.printTree(st.searchBST2(root, val));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

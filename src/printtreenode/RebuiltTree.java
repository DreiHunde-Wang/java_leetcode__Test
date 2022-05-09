package src.printtreenode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * ����ĳ��������ǰ���������������Ľ�����빹���ö���������������ڵ㡣
 * ���������ǰ���������������Ľ���ж������ظ������֡�
 * @author Dreihunde
 *
 */
public class RebuiltTree {
	//method 1 �ݹ� O(n) O(n)
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        // ǰ������еĵ�һ���ڵ���Ǹ��ڵ�
        int preorderRoot = preorderLeft;
        int inorderRoot = map.get(preorder[preorderRoot]);

        TreeNode root = new TreeNode(preorder[preorderRoot]);
        //�������ڵ���Ŀ
        int subLeft = inorderRoot - inorderLeft;
        // ��������С��� ��߽�+1 ��ʼ�� size_left_subtree����Ԫ�ؾͶ�Ӧ����������С��� ��߽� ��ʼ�� ���ڵ㶨λ-1����Ԫ��
        root.left = myBuildTree(preorder, inorder, preorderLeft + 1, preorderLeft + subLeft, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree(preorder, inorder, preorderLeft + subLeft + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;

    }

    //method 2 ���� O(n) O(n)
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] preorder = new int[]{3,9,20,15,7};
		int[] inorder = new int[] {9,3,15,20,7};
		
		RebuiltTree rb = new RebuiltTree();
		long startTime = System.nanoTime();
		ComnTest.printTree(rb.buildTree1(preorder, inorder));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		ComnTest.printTree(rb.buildTree2(preorder, inorder));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

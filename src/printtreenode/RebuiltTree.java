package src.printtreenode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * @author Dreihunde
 *
 */
public class RebuiltTree {
	//method 1 递归 O(n) O(n)
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
        // 前序遍历中的第一个节点就是根节点
        int preorderRoot = preorderLeft;
        int inorderRoot = map.get(preorder[preorderRoot]);

        TreeNode root = new TreeNode(preorder[preorderRoot]);
        //左子树节点数目
        int subLeft = inorderRoot - inorderLeft;
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorderLeft + 1, preorderLeft + subLeft, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree(preorder, inorder, preorderLeft + subLeft + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;

    }

    //method 2 迭代 O(n) O(n)
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

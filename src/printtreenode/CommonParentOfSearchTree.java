package src.printtreenode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * @author Dreihunde
 *
 */
public class CommonParentOfSearchTree {
	//method 1 利用二叉搜索树特性，左节点小于根节点小于右节点 O(n) O(n)
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;
        List<TreeNode> pathP = getNode(root, p);
        List<TreeNode> pathQ = getNode(root, q);
        TreeNode cur = null;
        for (int i = 0; i < Math.min(pathP.size(), pathQ.size()); i++) {
            if (pathP.get(i) == pathQ.get(i)) {
                cur = pathP.get(i);
            } else {
                break;
            }
        }
        return cur;
    }

    private List<TreeNode> getNode(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        
        while (root.val != target.val) {
            path.add(root);
            if (root.val > target.val) {
                root = root.left;
            } else if (root.val < target.val) {
                root = root.right;
            }
        }
        path.add(root);
        return path;
    }

    //method 2 一次遍历 O(n) O(1)
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                break;
            }
        }

        return root;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//root = [6,2,8,0,4,7,9,null,null,3,5]
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right = new TreeNode(5);
		
		CommonParentOfSearchTree cp = new CommonParentOfSearchTree();
		long startTime = System.nanoTime();
		TreeNode.printTree(cp.lowestCommonAncestor1(root, root.left, root.left.right));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		TreeNode.printTree(cp.lowestCommonAncestor2(root, root.left, root.left.right));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

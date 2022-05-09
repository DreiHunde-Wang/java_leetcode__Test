package src.printtreenode;

import java.util.ArrayList;
import java.util.List;

/**
 * ����һ������������, �ҵ�����������ָ���ڵ������������ȡ�
 * �ٶȰٿ�������������ȵĶ���Ϊ���������и��� T ��������� p��q��
 * ����������ȱ�ʾΪһ����� x������ x �� p��q �������� x ����Ⱦ����ܴ�һ���ڵ�Ҳ���������Լ������ȣ�����
 * @author Dreihunde
 *
 */
public class CommonParentOfSearchTree {
	//method 1 ���ö������������ԣ���ڵ�С�ڸ��ڵ�С���ҽڵ� O(n) O(n)
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

    //method 2 һ�α��� O(n) O(1)
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

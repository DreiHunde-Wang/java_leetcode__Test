package src.printtreenode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ����һ��������, �ҵ�����������ָ���ڵ������������ȡ�
 * �ٶȰٿ�������������ȵĶ���Ϊ���������и��� T ��������� p��q��
 * ����������ȱ�ʾΪһ����� x������ x �� p��q �������� x ����Ⱦ����ܴ�һ���ڵ�Ҳ���������Լ������ȣ�����
 * @author Dreihunde
 *
 */
public class CommonParentOfTree {
//  method 1 ���������� O(n) O(n)
  TreeNode note = null;
  int k;
  public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
      k = 2;
      dfs(root, p, q);
      return note;

  }

  private void dfs(TreeNode cur, TreeNode p, TreeNode q) {
      if (cur == null || k == 0)
          return;
      int kOld = k;
      if (cur == p || cur == q) {
          k--;
      }

      dfs(cur.left, p, q);
      dfs(cur.right, p, q);
      if (kOld == 2 && k == 0 && note == null) {
          note = cur;
      }
  }

  //method 2 �����б� O(n)
   public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
       if (root == null || root == p || root == q)
          return root;
      TreeNode left = lowestCommonAncestor2(root.left, p, q);
      TreeNode right = lowestCommonAncestor2(root.right, p, q);
      if (left == null)   return right;
      if (right == null)  return left;
      return root;

  }

  //method 3 ����ڵ㷨 O(n) O(n)
  Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
  Set<Integer> visited = new HashSet<Integer>();

  public void dfs(TreeNode root) {
      if (root.left != null) {
          parent.put(root.left.val, root);
          dfs(root.left);
      }
      if (root.right != null) {
          parent.put(root.right.val, root);
          dfs(root.right);
      }
  }

  public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
      dfs(root);
      while (p != null) {
          visited.add(p.val);
          p = parent.get(p.val);
      }
      while (q != null) {
          if (visited.contains(q.val)) {
              return q;
          }
          q = parent.get(q.val);
      }
      return null;
  }


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonParentOfTree cp = new CommonParentOfTree();
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right = new TreeNode(5);
		
		long startTime = System.nanoTime();
		TreeNode.printTree(cp.lowestCommonAncestor1(root, root.left, root.left.right));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		TreeNode.printTree(cp.lowestCommonAncestor2(root, root.left, root.left.right));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		TreeNode.printTree(cp.lowestCommonAncestor3(root, root.left, root.left.right));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

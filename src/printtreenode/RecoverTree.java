package src.printtreenode;

import java.util.ArrayDeque;

/**
 * ��������������ĸ��ڵ� root �������е� ǡ�� �����ڵ��ֵ������ؽ��������ڲ��ı���ṹ������£��ָ������ ��
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @author Dreihunde
 *
 */
public class RecoverTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//method 1 �������+dfs O(n) O(H) HΪջ�ĸ߶�
class RTCSolution1 {
  //��¼ǰһ���ڵ�
  TreeNode prev = null;
  //��¼��һ���쳣�ڵ�
  TreeNode s = null;
  //��¼�ڶ����쳣�ڵ�
  TreeNode t = null;
  public void recoverTree1(TreeNode root) {
      if (root == null) {
          return;
      }
      inorder(root);
      if (s != null && t != null)
          swap(s, t);
      
  }
  private void inorder(TreeNode root) {
      if (root == null) {
          return;
      }
      inorder(root.left);
      if (prev != null && root.val < prev.val) {
          s = (s == null) ? prev : s;
          t = root;
      }
      prev = root;
      inorder(root.right);
  }

  private void swap(TreeNode a, TreeNode b) {
      int t = a.val;
      a.val = b.val;
      b.val = t;
  }
}

//method 2 �������+bfs O(n) O(H) HΪջ�ĸ߶�
class RTCSolution2 {
  //��¼ǰһ���ڵ�
  TreeNode prev = null;
  //��¼��һ���쳣�ڵ�
  TreeNode s = null;
  //��¼�ڶ����쳣�ڵ�
  TreeNode t = null;
  public void recoverTree2(TreeNode root) {
      if (root == null) {
          return;
      }
      ArrayDeque<TreeNode> stack = new ArrayDeque<>();
      TreeNode prev = null;
      TreeNode s = null;
      TreeNode t = null;
      while (!stack.isEmpty() || root != null) {
          while (root != null) {
              stack.push(root);
              root = root.left;
          }
          root = stack.pop();
          if (prev != null && root.val < prev.val) {
              s = (s == null) ? prev : s;
              t = root;
          }
          prev = root;
          root = root.right;
      }        
      if (s != null && t != null)
          swap(s, t);
  }


  private void swap(TreeNode a, TreeNode b) {
      int t = a.val;
      a.val = b.val;
      b.val = t;
  }
}

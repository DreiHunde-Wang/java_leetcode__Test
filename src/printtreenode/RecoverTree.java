package src.printtreenode;

import java.util.ArrayDeque;

/**
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @author Dreihunde
 *
 */
public class RecoverTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//method 1 中序遍历+dfs O(n) O(H) H为栈的高度
class RTCSolution1 {
  //记录前一个节点
  TreeNode prev = null;
  //记录第一个异常节点
  TreeNode s = null;
  //记录第二个异常节点
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

//method 2 中序遍历+bfs O(n) O(H) H为栈的高度
class RTCSolution2 {
  //记录前一个节点
  TreeNode prev = null;
  //记录第一个异常节点
  TreeNode s = null;
  //记录第二个异常节点
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

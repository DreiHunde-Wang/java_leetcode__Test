package src.printtreenode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * ����Ҫ����ǰ������ķ�ʽ����һ��������ת����һ�������ź�������ɵ��ַ�����
 * �սڵ�����һ�Կ����� "()" ��ʾ����������Ҫʡ�����в�Ӱ���ַ�����ԭʼ������֮���һ��һӳ���ϵ�Ŀ����Ŷԡ�
 * ���ӣ�https://leetcode-cn.com/problems/construct-string-from-binary-tree
 * @author Dreihunde
 *
 */
public class Tree2Str {
	//method 1 dfs O(n) O(n)
    StringBuffer sb = new StringBuffer();
    public String tree2str1(TreeNode root) {
        inOrder(root);
        return sb.toString();
    }


    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        if (root.left != null || root.right != null) {
            sb.append("(");
            inOrder(root.left);
            sb.append(")");
        }
        
        if (root.right != null) {
            sb.append("(");
            inOrder(root.right);
            sb.append(")");
        }
        
    }
    //method 1 bfs O(n) O(n)
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Set<TreeNode> vis = new HashSet<>();
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            TreeNode t = d.pollLast();
            if (vis.contains(t)) {
                sb.append(")");
            } else {
                d.addLast(t);
                sb.append("(");
                sb.append(t.val);
                if (t.right != null) d.addLast(t.right);
                if (t.left != null) d.addLast(t.left);
                else if (t.right != null) sb.append("()");
                vis.add(t);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package printtreenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ����һ�����������ҳ��������ȡ�
 * �����������Ϊ���ڵ㵽��ԶҶ�ӽڵ���·���ϵĽڵ���(����)��
 * ˵��: Ҷ�ӽڵ���ָû���ӽڵ�Ľڵ㡣
 * @author Dreihunde
 *
 */
public class MaxTreeDepth {
	//method 1 �ݹ�
    public static int maxDepth1(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
    }

    //method 2 ����
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                if (t.left != null)
                    queue.offer(t.left);
                if (t.right != null)
                    queue.offer(t.right);
            }
            level++;
        }

        return level;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

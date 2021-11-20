package printtreenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ��תһ�ö�������
 * @author Dreihunde
 *
 */
public class InvertTree {
	//method 1 �ݹ�
    public TreeNode invertTree1(TreeNode root) {
        //�ڵ�Ϊ��ֱ�ӷ���
        if (root == null)
            return root;
        
        //��ת���������
        TreeNode left = invertTree1(root.left);
        //��ת���������
        TreeNode right = invertTree1(root.right);
        
        //��������������λ��
        root.left = right;
        root.right = left;
        return root;
    }

    //method 2 ����
       public static TreeNode invertTree2(TreeNode root) {
        //�ڵ�Ϊ��ֱ�ӷ���
        if (root == null)
            return root;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            //ȡ��һ���ڵ�
            TreeNode temp = queue.poll();

            //��ӵ�ǰ�ڵ���ӽڵ�������
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }

            //������ǰ�ڵ����������
            TreeNode t = temp.left;
            temp.left = temp.right;
            temp.right = t;
        }

        return root;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

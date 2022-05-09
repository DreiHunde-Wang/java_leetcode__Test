package src.printtreenode;

import java.util.ArrayDeque;

/**
 * ���һ�ö���������������������������Գ�Ϊ ��ż�� ��
 * ���������ڵ����ڲ��±�Ϊ 0 �������ӽڵ����ڲ��±�Ϊ 1 ��������ڵ����ڲ��±�Ϊ 2 ���������ơ�
 * ż���±� ���ϵ����нڵ��ֵ���� �� �����������Ұ�˳�� �ϸ����
 * �����±� ���ϵ����нڵ��ֵ���� ż �����������Ұ�˳�� �ϸ�ݼ�
 * ����������ĸ��ڵ㣬���������Ϊ ��ż�� ���򷵻� true �����򷵻� false ��
 * @author Dreihunde
 *
 */
public class IsEvenOddTree {
	//method 1 ������� O(n) O(n)
    public boolean isEvenOddTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                int value = temp.val;
                if (value % 2 == level % 2) {
                    return false;
                }
                if ( (level % 2 == 0 && pre >= value) || (level % 2 == 1 && pre <= value)) {
                    return false;
                }
                pre = value;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            level++;
        }
        return true;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(10);
		root.right = new TreeNode(4);
		root.left.left = new TreeNode(3);
		root.right.left = new TreeNode(7);
		root.right.left = new TreeNode(9);
		
		IsEvenOddTree ie = new IsEvenOddTree();
		System.out.println(ie.isEvenOddTree(root));

	}

}

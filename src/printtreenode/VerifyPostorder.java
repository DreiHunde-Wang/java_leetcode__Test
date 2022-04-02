package printtreenode;

import java.util.ArrayDeque;

/**
 * ����һ���������飬�жϸ������ǲ���ĳ�����������ĺ�����������
 * ������򷵻� true�����򷵻� false���������������������������ֶ�������ͬ��
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * @author Dreihunde
 *
 */
public class VerifyPostorder {
	//method 1 �ݹ���� O(n^2) O(n)
    public boolean verifyPostorder1(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) return true;
        int p = i;
        while(postorder[p] < postorder[j]) p++;
        //������������������
        int m = p;
        while(postorder[p] > postorder[j]) p++;
        //������������С��root��������������root
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    //method 2 ����ջ(����������Ϊ�����������������) O(n) O(n)
    public boolean verifyPostorder(int[] postorder) {
        int root = Integer.MAX_VALUE;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)    return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                root = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

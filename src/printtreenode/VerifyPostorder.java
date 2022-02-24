package printtreenode;

import java.util.ArrayDeque;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * @author Dreihunde
 *
 */
public class VerifyPostorder {
	//method 1 递归遍历 O(n^2) O(n)
    public boolean verifyPostorder1(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) return true;
        int p = i;
        while(postorder[p] < postorder[j]) p++;
        //划分左子树与右子树
        int m = p;
        while(postorder[p] > postorder[j]) p++;
        //满足左子树都小于root，右子树都大于root
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    //method 2 单调栈(将整棵树视为无穷大树根的左子树) O(n) O(n)
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

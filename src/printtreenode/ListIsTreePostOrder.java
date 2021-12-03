package printtreenode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 * @author Dreihunde
 *
 */
public class ListIsTreePostOrder {
	//method 1 分治递归 O(n2) O(n)
    public boolean verifyPostorder1(int[] postorder) {
        return cur(postorder, 0, postorder.length - 1);
    }

    private boolean cur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        //划分左右子树： 遍历后序遍历的 [i, j][i,j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 m 。
        //此时，可划分出左子树区间 [i,m-1][i,m−1] 、右子树区间 [m, j - 1][m,j−1] 、根节点索引 j
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }

        return p == j && cur(postorder, i, m - 1) && cur(postorder, m, j - 1);
    }

    //method 2 遍历 O(n) O(n)
    public boolean verifyPostorder2(int[] postorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int root = Integer.MAX_VALUE;
        //后序遍历的逆遍历
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)    return false;
            //切换遍历节点，清除上次遍历数据，出栈
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                root = stack.pop();
            }
            //单调递增逻辑，压栈
            stack.push(postorder[i]);
        }

        return true;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListIsTreePostOrder lt = new ListIsTreePostOrder();
		int[] postOrder = new int[] {4, 8, 6, 12, 16, 14, 10};
		
		long startTime = System.nanoTime();
		System.out.println(lt.verifyPostorder1(postOrder));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(lt.verifyPostorder2(postOrder));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

package printtreenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断两棵树是否相同
 * @author Dreihunde
 *
 */
public class IsSameTree {
	//method 1 递归
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null || p.val != q.val)
            return false;
        else
            return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }

    //method 2 迭代
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode t1 = queue1.poll();
            TreeNode t2 = queue2.poll();

            if (t1 == null && t2 == null)
                continue;
            else if (t1 == null || t2 == null || t1.val != t2.val)
                return false;
            else {
                queue1.offer(t1.left);
                queue2.offer(t2.left);
                queue1.offer(t1.right);
                queue2.offer(t2.right);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[2,3,1]
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(3);
		root.right = new TreeNode(1);
		
		TreeNode sub = new TreeNode(2);
		sub.left = new TreeNode(3);
		sub.right = new TreeNode(1);
		
		long startTime = System.nanoTime();
		System.out.println(isSameTree1(root, sub));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(isSameTree2(root, sub));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

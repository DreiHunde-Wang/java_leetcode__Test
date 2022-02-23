package printtreenode;

import java.util.LinkedList;
import java.util.Queue;


/**
 * ��ʵ��һ�������������ж�һ�ö������ǲ��ǶԳƵġ����һ�ö����������ľ���һ������ô���ǶԳƵġ�
 * ���磬������ [1,2,2,3,4,4,3] �ǶԳƵġ�
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 * @author Dreihunde
 *
 */
public class SymmetricTree {
	//method 1 �ݹ� O(n) O(n)
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null)
            return true;
        return recur(root.left, root.right);
        
    }

    private static boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null || left.val != right.val)
            return false;
        else 
            return recur(left.left, right.right) && recur(left.right, right.left);
    }

    //method 2 ���� O(n) O(n)
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetricTree(root.left, root.right);
        
    }

    private static boolean isSymmetricTree(TreeNode L, TreeNode R) {
    	//ArrayDeque��֧��Ԫ��Ϊnull��������linkedListʵ��queue
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(L);
        queue.offer(R);

        while (!queue.isEmpty()) {
            L = queue.poll();
            R = queue.poll();

            if (L == null && R == null)
                continue;
            else if (L == null || R == null || L.val != R.val)
                return false;
            else {
                queue.offer(L.left);
                queue.offer(R.right);
                queue.offer(L.right);
                queue.offer(R.left);
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
		//[1,2,2,3,4,4,3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		long startTime = System.nanoTime();
		System.out.println(isSymmetric1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(isSymmetric2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

package printtreenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转一棵二叉树。
 * @author Dreihunde
 *
 */
public class InvertTree {
	//method 1 递归
    public TreeNode invertTree1(TreeNode root) {
        //节点为空直接返回
        if (root == null)
            return root;
        
        //反转后的左子树
        TreeNode left = invertTree1(root.left);
        //反转后的右子树
        TreeNode right = invertTree1(root.right);
        
        //交换两个子树的位置
        root.left = right;
        root.right = left;
        return root;
    }

    //method 2 迭代
       public static TreeNode invertTree2(TreeNode root) {
        //节点为空直接返回
        if (root == null)
            return root;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            //取出一个节点
            TreeNode temp = queue.poll();

            //添加当前节点的子节点进入队列
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }

            //交换当前节点的左右子树
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

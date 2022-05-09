package src.printtreenode;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * @author Dreihunde
 *
 */
public class FindKLargeNode {
	int temp = 0;
    int k = 0;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        revInOrder(root);
        return temp;
    }
    //逆中序遍历，递减数列
    private void revInOrder(TreeNode cur) {
        if (cur == null || k == 0) {
            return;
        }

        revInOrder(cur.right);
        k--;
        if (k == 0) {
            temp = cur.val;
            return;
        }
 
        revInOrder(cur.left);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1, 2, 3, 4, 5, 6, 7
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		
		int k = 3;
		FindKLargeNode fk = new FindKLargeNode();
		long startTime = System.nanoTime();
		TreeNode.printTree(root);
		System.out.println(fk.kthLargest(root, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

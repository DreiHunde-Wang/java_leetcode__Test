package printtreenode;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.HandlerBase;

/**
 * ����һ�ö��������������ö���������ת����һ�������ѭ��˫������Ҫ���ܴ����κ��µĽڵ㣬ֻ�ܵ������нڵ�ָ���ָ��
 * @author Dreihunde
 *
 */
public class TreeToDList {
	//method 1 �������
    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null)
            return null;
        inOrderPrint(root);
        head.left = pre;
        pre.right = head;
        return head;
        
    }

    private void inOrderPrint(TreeNode cur) {
        if (cur == null)
            return;
        
        inOrderPrint(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        inOrderPrint(cur.right);

    }
    
    private void printListTree(TreeNode head) {
    	Set<TreeNode> set = new HashSet<>();
    	while (head != null) {
    		if (!set.contains(head)) {
    			set.add(head);
    		} else {
    			break;
    		}
    		System.out.print(head.val + " ");
    		head = head.right;
    	}
    	System.out.println();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		
		
		TreeToDList tt = new TreeToDList();
		long startTime = System.nanoTime();
		TreeNode.printTree(root);
		tt.printListTree(tt.treeToDoublyList(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

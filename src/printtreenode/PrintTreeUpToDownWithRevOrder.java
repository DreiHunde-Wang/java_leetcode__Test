package src.printtreenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * ��ʵ��һ����������֮����˳���ӡ��������
 * ����һ�а��մ����ҵ�˳���ӡ��
 * �ڶ��㰴�մ��ҵ����˳���ӡ��
 * �������ٰ��մ����ҵ�˳���ӡ���������Դ����ơ�
 * @author Dreihunde
 *
 */
public class PrintTreeUpToDownWithRevOrder {
	//method 1
	 public static List<List<Integer>> levelOrder1(TreeNode root) {
	        List<List<Integer>> list = new ArrayList<>();
	        if (root == null)
	            return list;
	        
	        Queue<TreeNode> queue = new LinkedList<>();
	        queue.offer(root);
	        int level = 0;
	        while (!queue.isEmpty()) {
	            LinkedList<Integer> temp = new LinkedList<>();
	            int size = queue.size();
	            for (int i = 0; i < size; i++) {
	                TreeNode node = queue.poll();
	                if (level % 2 == 0) {
	                    temp.addLast(node.val);
	                } else {
	                    temp.addFirst(node.val);
	                }

	                if (node.left != null)
	                    queue.offer(node.left);
	                if (node.right != null)
	                    queue.offer(node.right);
	            }
	            list.add(temp);
	            level++;
	        }

	        return list;
	    }

	    //method 2
	    public static List<List<Integer>> levelOrder2(TreeNode root) {
	        Deque<TreeNode> deque = new LinkedList<>();
	        List<List<Integer>> res = new ArrayList<>();
	        if(root != null) deque.add(root);
	        while(!deque.isEmpty()) {
	            // ��ӡ������
	            List<Integer> tmp = new ArrayList<>();
	            for(int i = deque.size(); i > 0; i--) {
	                // �������Ҵ�ӡ
	                TreeNode node = deque.removeFirst();
	                tmp.add(node.val);
	                // ������Ҽ����²�ڵ�
	                if(node.left != null) deque.addLast(node.left);
	                if(node.right != null) deque.addLast(node.right);
	            }
	            res.add(tmp);
	            if(deque.isEmpty()) break; // ��Ϊ������ǰ����
	            // ��ӡż����
	            tmp = new ArrayList<>();
	            for(int i = deque.size(); i > 0; i--) {
	                // ���������ӡ
	                TreeNode node = deque.removeLast();
	                tmp.add(node.val);
	                // ���Һ�������²�ڵ�
	                if(node.right != null) deque.addFirst(node.right);
	                if(node.left != null) deque.addFirst(node.left);
	            }
	            res.add(tmp);
	        }
	        return res;
	    }
	    
	    //method 3
	    public static List<List<Integer>> levelOrder3(TreeNode root) {
	        Queue<TreeNode> queue = new LinkedList<>();
	        List<List<Integer>> res = new ArrayList<>();
	        if(root != null) queue.add(root);
	        while(!queue.isEmpty()) {
	            List<Integer> tmp = new ArrayList<>();
	            for(int i = queue.size(); i > 0; i--) {
	                TreeNode node = queue.poll();
	                tmp.add(node.val);
	                if(node.left != null) queue.add(node.left);
	                if(node.right != null) queue.add(node.right);
	            }
	            if(res.size() % 2 == 1) Collections.reverse(tmp);
	            res.add(tmp);
	        }
	        return res;
	    }
	    
	    
		public static void main(String[] args) {
			//[3,9,20,null,null,15,7]
			TreeNode root = new TreeNode(3);
			root.left = new TreeNode(9);
			root.right = new TreeNode(20);
			root.right.left = new TreeNode(15);
			root.right.right = new TreeNode(7);
			
			long startTime = System.nanoTime();
			System.out.println(levelOrder1(root));
			long endTime = System.nanoTime();
			System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
			
			startTime = System.nanoTime();
			System.out.println(levelOrder2(root));
			endTime = System.nanoTime();
			System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
			
			startTime = System.nanoTime();
			System.out.println(levelOrder3(root));
			endTime = System.nanoTime();
			System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
			
		}


}

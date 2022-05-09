package src.printtreenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔。
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * @author Dreihunde
 *
 */
class Node {
	public int val;
	public List<Node> children;
	
	public Node(int x) {
		this.val = x;
	}
	
	public Node(int x, List<Node> children) {
		this.val = x;
		this.children = children;
	}
}

public class MaxNTreeDepth {
	//method 1 递归
    public int maxDepth1(Node root) {
        if (root == null)
            return 0;
        int maxD = 0;
        for (Node t : root.children) {
            maxD = Math.max(maxD, maxDepth1(t));
        }
        return 1 + maxD;
        
    }
    //method 2 遍历
    public int maxDepth2(Node root) {
        if (root == null)
            return 0;
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node temp = queue.poll();
                List<Node> childrens = temp.children;
                for (Node c : childrens) {
                    queue.offer(c);
                }
            }
            level++;
        }
        return level;

        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = new Node(2, new ArrayList<Node>());
		root.children.add(new Node(3, new ArrayList<Node>()));
		root.children.add(new Node(4, new ArrayList<Node>()));
		root.children.add(new Node(5, new ArrayList<Node>()));
		root.children.get(0).children.add(new Node(1, new ArrayList<Node>()));
		root.children.get(0).children.add(new Node(2, new ArrayList<Node>()));
		root.children.get(0).children.add(new Node(3, new ArrayList<Node>()));
		MaxNTreeDepth md = new MaxNTreeDepth();
		
		long startTime = System.nanoTime();
		System.out.println(md.maxDepth1(root));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(md.maxDepth2(root));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

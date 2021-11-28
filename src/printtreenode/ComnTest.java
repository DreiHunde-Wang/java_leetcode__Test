package printtreenode;

import java.util.ArrayList;
import java.util.List;

import printtreenode.PrintTreeUpToDown;

public class ComnTest {
	public static void main(String[] args) {
		//[3,9,20,null,null,15,7]
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}
	
	public static void printListList(List<List<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j) + " ");
			}
		}
		System.out.println();
	}
	
	public static void printList(List<?> list) {
		ArrayList<?> temp = new ArrayList<>(list);
		for (int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i) + " ");
		}
		System.out.println();
	}
	

}

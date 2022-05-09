package src.printtreenode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 判断树2是否为树1的子树
 * @author Dreihunde
 *
 */
public class IsSubTree {
	//method 1 迭代
	public static boolean isSubtree1(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;
        if (root == null || subRoot == null)
            return false;
        return isSameTree(root, subRoot) || isSubtree1(root.left, subRoot) || isSubtree1(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null || node1.val != node2.val)
            return false;
        
        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }

    static List<Integer> sOrder = new ArrayList<Integer>();
    static List<Integer> tOrder = new ArrayList<Integer>();
    static int maxElement, lNull, rNull;

    public static boolean isSubtree2(TreeNode s, TreeNode t) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);

        return kmp1();
    }

    public static void getMaxElement(TreeNode t) {
        if (t == null) {
            return;
        }
        maxElement = Math.max(maxElement, t.val);
        getMaxElement(t.left);
        getMaxElement(t.right);
    }

    public static void getDfsOrder(TreeNode t, List<Integer> tar) {
        if (t == null) {
            return;
        }
        tar.add(t.val);
        if (t.left != null) {
            getDfsOrder(t.left, tar);
        } else {
            tar.add(lNull);
        }
        if (t.right != null) {
            getDfsOrder(t.right, tar);
        } else {
            tar.add(rNull);
        }
    }

    public static boolean kmp() {
        int sLen = sOrder.size(), tLen = tOrder.size();
        int[] fail = new int[tOrder.size()];
        Arrays.fill(fail, -1);
        for (int i = 1, j = -1; i < tLen; ++i) {
            while (j != -1 && !(tOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (tOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            fail[i] = j;
        }
        for (int i = 0, j = -1; i < sLen; ++i) {
            while (j != -1 && !(sOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (sOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            if (j == tLen - 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean kmp1() {
        int sLen = sOrder.size();
        int tLen = tOrder.size();
        int[] next = new int[tLen];
        next[0] = -1;
        int t = -1;
        int j = 0;
        while (j < tLen - 1) {
            if (t < 0 || tOrder.get(j).equals(tOrder.get(t))) {
                t++;
                j++;
                next[j] = t;
            }
            else
                t = next[t];
        }

        int i = 0;
        j = 0;
        while (i < sLen && j < tLen) {
            if (j < 0 || sOrder.get(i).equals(tOrder.get(j))) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == tLen)
                return true;
        }

        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[1,2,2,3,4,4,3]
//		TreeNode root = new TreeNode(1);
//		root.left = new TreeNode(2);
//		root.right = new TreeNode(2);
//		root.left.left = new TreeNode(3);
//		root.left.right = new TreeNode(4);
//		root.right.left = new TreeNode(4);
//		root.right.right = new TreeNode(3);
//				
//		TreeNode sub = new TreeNode(2);
//		sub.left = new TreeNode(3);
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		TreeNode sub = new TreeNode(1);
		sub.left = new TreeNode(2);
//		sub.right = new TreeNode(3);
			
		long startTime = System.nanoTime();
		System.out.println(isSubtree1(root, sub));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(isSubtree2(root, sub));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

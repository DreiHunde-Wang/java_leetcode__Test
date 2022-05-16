package src.printtreenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * https://leetcode.cn/problems/successor-lcci/
 * @author Dreihunde
 *
 */
public class InorderSuccessor {
	//method 1 map映射 O(n) O(n)
    Map<TreeNode, Integer> map;
    List<TreeNode> list;
    int index;
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        map = new HashMap<>();
        list = new ArrayList<>();
        index = 0;
        inOrder(root);
        int ret = map.get(p) + 1;
        return ret == index ? null : list.get(ret);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root);
        map.put(root, index++);
        inOrder(root.right);
    }

    //method 2 中序遍历 O(n) O(n)
     public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode prev = null, curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev == p) {
                return curr;
            }
            prev = curr;
            curr = curr.right;
        }
        return null;
    }

    //method 3 二叉搜索树性质 O(n) O(1)
     /**
      * 如果node 的节点值大于p的节点值，则p的后继节点可能是node 或者在node 的左子树中，因此用node更新答案，并将node移动到其左子节点继续遍历；
      * 如果node 的节点值小于或等于p的节点值，则p的后继节点可能在node 的右子树中，因此将node移动到其右子节点继续遍历。
      */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

}

package src.printtreenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���һ���㷨���ҳ�������������ָ���ڵ�ġ���һ�����ڵ㣨Ҳ�������̣���
 * ���ָ���ڵ�û�ж�Ӧ�ġ���һ�����ڵ㣬�򷵻�null��
 * https://leetcode.cn/problems/successor-lcci/
 * @author Dreihunde
 *
 */
public class InorderSuccessor {
	//method 1 mapӳ�� O(n) O(n)
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

    //method 2 ������� O(n) O(n)
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

    //method 3 �������������� O(n) O(1)
     /**
      * ���node �Ľڵ�ֵ����p�Ľڵ�ֵ����p�ĺ�̽ڵ������node ������node ���������У������node���´𰸣�����node�ƶ��������ӽڵ����������
      * ���node �Ľڵ�ֵС�ڻ����p�Ľڵ�ֵ����p�ĺ�̽ڵ������node ���������У���˽�node�ƶ��������ӽڵ����������
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

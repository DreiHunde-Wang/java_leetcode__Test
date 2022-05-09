package src.printtreenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @author Dreihunde
 *
 */
public class LevelOrder {
	//method 1 层序遍历 O(n) O(n)
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                Node t = queue.poll();
                ans.get(level).add(t.val);
                for (Node children : t.children) {
                    if (children != null) {
                        queue.offer(children);
                    }
                }
            }
            level++;
        }
        return ans;
        
    }

}

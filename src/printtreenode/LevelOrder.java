package src.printtreenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * ����һ�� N ������������ڵ�ֵ�Ĳ�����������������ң�����������
 * �������л��������ò��������ÿ���ӽڵ㶼�� null ֵ�ָ����μ�ʾ������
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @author Dreihunde
 *
 */
public class LevelOrder {
	//method 1 ������� O(n) O(n)
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

package printtreenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * ���� root1 �� root2 �����ö��������������㷵��һ���б����а��� ������ �е������������� ���� ����.
 * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 * @author Dreihunde
 *
 */
public class GetAllElements {
	//method 1 ����Ӻ����� O((n + m)log(n + m)) O(n + m)
    List<Integer> list;
    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        list = new ArrayList<>();
        inOrder1(root1);
        inOrder1(root2);
        Collections.sort(list);
        return list;
    }

    public void inOrder1(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder1(root.left);
        list.add(root.val);
        inOrder1(root.right);
    }
    
    //method 2 ����Ӻ�鲢 O(n + m) O(n + m)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> nums1 = new ArrayList<Integer>();
        List<Integer> nums2 = new ArrayList<Integer>();
        inorder(root1, nums1);
        inorder(root2, nums2);

        List<Integer> merged = new ArrayList<Integer>();
        int p1 = 0, p2 = 0;
        while (true) {
            if (p1 == nums1.size()) {
                merged.addAll(nums2.subList(p2, nums2.size()));
                break;
            }
            if (p2 == nums2.size()) {
                merged.addAll(nums1.subList(p1, nums1.size()));
                break;
            }
            if (nums1.get(p1) < nums2.get(p2)) {
                merged.add(nums1.get(p1++));
            } else {
                merged.add(nums2.get(p2++));
            }
        }
        return merged;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node != null) {
            inorder(node.left, res);
            res.add(node.val);
            inorder(node.right, res);
        }
    }
}

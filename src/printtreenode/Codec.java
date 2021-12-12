package printtreenode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * @author Dreihunde
 *
 */
public class Codec {
	//method 1 先序遍历（递归）O(n) O(n)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	String str = reserialize(root, "");
        return str.substring(0, str.length() - 1);
    }
    //先序遍历
    private String reserialize(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += String.valueOf(root.val) + ",";
            str = reserialize(root.left, str);
            str = reserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArr = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
        return rdeserialize(dataList);
    }

    private TreeNode rdeserialize(List<String> dataList) {
    	if (dataList.size() == 0) {
    		return null;
    	}
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        if (dataList.size() > 0) {
        	root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);
        }
        
        return root;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "1,2,3,null,null,4,5";
		Codec cd = new Codec();
		TreeNode root = cd.deserialize(data);
		ComnTest.printTree(root);
		data = cd.serialize(root);
		System.out.println(data);
		
//		String data2 = "1,2,3,null,null,4,5";
		String data2 = "null";
		Codec2 cd2 = new Codec2();
		TreeNode root2 = cd2.deserialize(data2);
		ComnTest.printTree(root2);
		data = cd2.serialize(root2);
		System.out.println(data2);
	}

}

class Codec2 {
	//method 2 层序遍历（迭代）O(n) O(n)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuffer sb = new StringBuffer();
        
        while (!queue.isEmpty()) {
        	TreeNode temp = queue.poll();
        	if (temp == null) {
        		sb.append("null,");
        		continue;
        	} else {
        		sb.append(String.valueOf(temp.val) + ",");
        		queue.add(temp.left);
            	queue.add(temp.right);
        	}
        }
        //删除最后一个逗号
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	
        String[] dataArr = data.split(",");
        if (dataArr[0].length() == 0 || dataArr[0].equals("null"))
    		return null;
        TreeNode root = new TreeNode(Integer.valueOf(dataArr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        //层序遍历
        while (!queue.isEmpty() && index < dataArr.length) {
        	TreeNode t = queue.poll();
        	if (! dataArr[index].equals("null")) {
        		t.left = new TreeNode(Integer.parseInt(dataArr[index]));	
        		queue.offer(t.left);
        	}
        	index++;
        	if (! dataArr[index].equals("null")) {
        		t.right = new TreeNode(Integer.parseInt(dataArr[index]));
        		queue.offer(t.right);
        	}
        	index++;
        }
        return root;
    }



}

package src.stringnumtest;

import java.util.ArrayDeque;

/**
 * 给你�?个字符串 path ，表示指向某�?文件或目录的 Unix 风格 绝对路径 （以 '/' �?头），请你将其转化为更加�?洁的规范路径�?
 * �? Unix 风格的文件系统中，一个点�?.）表示当前目录本身；此外，两个点 �?..） 表示将目录切换到上�?级（指向父目录）�?
 * 两�?�都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即�?'//'）都被视为单个斜�? '/' �? 
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文�?/目录名称�?
 * 请注意，返回�? 规范路径 必须遵循下述格式�?
 * 始终以斜�? '/' �?头�??
 * 两个目录名之间必须只有一个斜�? '/' �?
 * �?后一个目录名（如果存在）不能 �? '/' 结尾�?
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' �? '..'）�??
 * 返回�?化后得到�? 规范路径 �?
 * @author Dreihunde
 *
 */
public class SimplyPath {
	//method 1 �? O(n) O(n)
    public String simplifyPath(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        String[] paths = path.split("/");
        if (paths.length == 0) {
            return "/";
        }
        StringBuffer sb = new StringBuffer(); 
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].equals("") || paths[i].equals(".")) {
                continue;
            }
            if (paths[i].equals("..")) {
                if (!stack.isEmpty()) {
                    //pop = pollFirst
                    stack.pop();
                }   
            } else {
                //push = offerFirst
                stack.push(paths[i]);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            sb.append('/');
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "/home//foo/";
		SimplyPath sp = new SimplyPath();
		System.out.println(sp.simplifyPath(path));

	}

}

package src.stringnumtest;

import java.util.ArrayDeque;

/**
 * 这里�? dir 作为根目录中的唯�?目录。dir 包含两个子目�? subdir1 �? subdir2 �?
 * subdir1 包含文件 file1.ext 和子目录 subsubdir1；subdir2 包含子目�? subsubdir2，该子目录下包含文件 file2.ext �?
 * 在文本格式中，如下所�?(⟶表示制表符)�?
 * dir
 * �? subdir1
 * �? �? file1.ext
 * �? �? subsubdir1
 * �? subdir2
 * �? �? subsubdir2
 * �? �? �? file2.ext
 * 如果是代码表示，上面的文件系统可以写�? "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" �?
 * '\n' �? '\t' 分别是换行符和制表符�?
 * 文件系统中的每个文件和文件夹都有�?个唯�?�? 绝对路径 ，即必须打开才能到达文件/目录�?在位置的目录顺序，所有路径用 '/' 连接�?
 * 上面例子中，指向 file2.ext �? 绝对路径 �? "dir/subdir2/subsubdir2/file2.ext" �?
 * 每个目录名由字母、数字和/或空格组成，每个文件名遵�? name.extension 的格式，其中 name 和 extension由字母�?�数字和/或空格组成�??
 * 给定�?个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 �? �?长绝对路�? 的长度 �?? 如果系统中没有文件，返回 0�?
 * 链接：https://leetcode-cn.com/problems/longest-absolute-file-path
 * @author Dreihunde
 *
 */
public class LengthLongestPath {
	//method 1 �? O(n) O(n)
    public int lengthLongestPath1(String input) {
        int n = input.length();
        int maxLen = 0;
        int depth = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        while (index < n) {
            depth = 1;
            //�?测文件的深度
            while (index < n && input.charAt(index) == '\t') {
                depth++;
                index++;
            }
            boolean isFile = false;
            int len = 0;
            while (index < n && input.charAt(index) != '\n') {
                if (input.charAt(index) == '.') {
                    isFile = true;
                }
                len++;
                index++;
            }

            //跳过\n
            index++;
            while (stack.size() >= depth) {
                stack.pop();
            }
            //和上�?级目录结�?
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }
            if (isFile) {
                maxLen = Math.max(maxLen, len);
            } else {
                stack.push(len);
            }
        }
        return maxLen;
    }

    //method 2 数组 O(n) O(n)
    public int lengthLongestPath(String input) {
        int n = input.length();
        int maxLen = 0;
        int depth = 0;
        int[] level = new int[n + 1];
        int index = 0;
        while (index < n) {
            depth = 1;
            //�?测文件的深度
            while (index < n && input.charAt(index) == '\t') {
                depth++;
                index++;
            }
            boolean isFile = false;
            int len = 0;
            while (index < n && input.charAt(index) != '\n') {
                if (input.charAt(index) == '.') {
                    isFile = true;
                }
                len++;
                index++;
            }

            //跳过\n
            index++;
            
            //和上�?级目录结�?
            if (level[depth - 1] != 0) {
                len += level[depth - 1] + 1;
            }
            if (isFile) {
                maxLen = Math.max(maxLen, len);
            } else {
                level[depth] = len;
            }
        }
        return maxLen;
    }

}

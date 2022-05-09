package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 有一个长度为n的字符串s ，你可以删除其中的m个字符，使剩余字符串的字典序最小，输出这个剩余字符串。
 * @author Dreihunde
 *
 */
public class deleteChar {
	//要想字典序小，那么字符串中的字母应该尽可能满足升序。
	//因此只需遵循一个原则，当前字母的ASCII码不能比前面字母的小，如果出现这种情况，就要把前面的字母删除掉，从当前字母重新开始算字符串。
	//最后需要注意，返回的字符串长度应该为n-m。
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T -- > 0){
            String[] params = br.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);
            String str = br.readLine().trim();
            System.out.println(delete(str.toCharArray(), n, m));
        }
    }
     
    private static String delete(char[] str, int n, int m) {
        Stack<Character> stack = new Stack<>();
        int removeCharNums = 0;
        for(int i = 0; i < n; i++){
            char c = str[i];
            while(!stack.isEmpty() && c < stack.peek() && removeCharNums < m){
                stack.pop();
                removeCharNums += 1;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        return sb.reverse().toString().substring(0, n - m);
    }

}

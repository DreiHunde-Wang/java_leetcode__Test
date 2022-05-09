package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * ��һ������Ϊn���ַ���s �������ɾ�����е�m���ַ���ʹʣ���ַ������ֵ�����С��������ʣ���ַ�����
 * @author Dreihunde
 *
 */
public class deleteChar {
	//Ҫ���ֵ���С����ô�ַ����е���ĸӦ�þ�������������
	//���ֻ����ѭһ��ԭ�򣬵�ǰ��ĸ��ASCII�벻�ܱ�ǰ����ĸ��С��������������������Ҫ��ǰ�����ĸɾ�������ӵ�ǰ��ĸ���¿�ʼ���ַ�����
	//�����Ҫע�⣬���ص��ַ�������Ӧ��Ϊn-m��
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

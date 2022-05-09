package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * ��n����Ʒ�ɹ�ѡ�񣬱���ѡ������m����Ʒ���밴�ֵ���˳���������ѡȡ��������Ʒ���123��321��213�ȱ���Ϊ��ͬһ�ַ���������ֵ�����С�ļ���
 * @author Dreihunde
 *
 */
public class AllSortNum {
	public static ArrayList<Stack<Integer>> res = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().trim().split(" ");
        int n = Integer.parseInt(params[0]), m = Integer.parseInt(params[1]);
        dfs(n, m, 1, new Stack<Integer>());
        for(Stack<Integer> stack: res){
            for(Integer elem : stack)
                System.out.print(elem + " ");
            System.out.println();
        }
    }
     
    private static void dfs(int n, int m, int depth, Stack<Integer> stack) {
        if(stack.size() == m){
            res.add((Stack<Integer>)stack.clone());
            return;
        }
        for(int i = depth; i <= n; i++){
            stack.push(i);
            dfs(n, m, i + 1, stack);
            stack.pop();
        }
    }

}

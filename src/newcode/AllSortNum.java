package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 有n个物品可供选择，必须选择其中m个物品，请按字典序顺序输出所有选取方案的物品编号123与321与213等被认为是同一种方案，输出字典序最小的即可
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

package newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 有n个物品，每个物品有k个属性，第i件物品的第j个属性用一个正整数表示记为aij，
 * 两个不同的物品i,j被称为是完美对的当且仅当ai1+bi1 = ai2 + bi2 =...=aij + bij，求完美对的个数。
 * @author Dreihunde
 *
 */
public class PerfectPair {
	
	//剪枝 O(nlogn) O(n)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().trim().split(" ");
        int n = Integer.parseInt(params[0]), k = Integer.parseInt(params[1]);
        int[][] fields = new int[n][k];
        HashMap<Integer, ArrayList<Integer>> counter = new HashMap<>();
        int count = 0;
        for(int i = 0; i < n; i++){
            String[] row = br.readLine().trim().split(" ");
            for(int j = 0; j < k; j++)
                fields[i][j] = Integer.parseInt(row[j]);
            // 计算出差分之和
            int diffSum = fields[i][k - 1] - fields[i][0];
            // 检查相反数是否在里面，并检查是否是完美对
            if(counter.containsKey(-diffSum)){
                for(int j = 0; j < counter.get(-diffSum).size(); j++)
                    if(isValid(i, counter.get(-diffSum).get(j), fields)) count++;
            }
            if(counter.containsKey(diffSum))
                counter.get(diffSum).add(i);
            else{
                ArrayList<Integer> path = new ArrayList<>();
                path.add(i);
                counter.put(diffSum, path);
            }
        }
        System.out.println(count);
    }
     
    private static boolean isValid(int i, int j, int[][] fields) {
        int val = fields[i][0] + fields[j][0];
        for(int fi = 1; fi < fields[0].length; fi++)
            if(fields[i][fi] + fields[j][fi] != val) return false;
        return true;
    }
}

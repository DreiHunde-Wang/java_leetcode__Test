package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小强有一个3xn的矩阵a，他将a中每列的三个数字中取出一个按顺序组成一个长度为n的数组b，即bi可以是其中ai_1, ai_2, ai_3任意一个。问|bi -bi+1|的最小值是多少。
 * @author Dreihunde
 *
 */
public class MinAbsInSubNum {
	//dp 遍历每个元素 O(3^n) O(n)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[3][n];
        for(int i = 0; i < 3; i++){
            String[] rawRow = br.readLine().trim().split(" ");
            for(int j = 0; j < n; j++)
                arr[i][j] = Long.parseLong(rawRow[j]);
        }
        long[][] dp = new long[3][n];
        for(int j = 1; j < n; j++){
            for(int i = 0; i < 3; i++){
                dp[i][j] = Math.min(Math.abs(arr[i][j] - arr[0][j - 1]) + dp[0][j - 1],
                                    Math.min(Math.abs(arr[i][j] - arr[1][j - 1]) + dp[1][j - 1],
                                             Math.abs(arr[i][j] - arr[2][j - 1]) + dp[2][j - 1]));
            }
        }
        System.out.println(Math.min(Math.min(dp[0][n - 1], dp[1][n - 1]), dp[2][n - 1]));
    }

}

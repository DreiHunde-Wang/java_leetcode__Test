package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小强想要从[1,A]中选出一个整数x,从[1,B]中选出一个整数y.使得满足x/y=a/b的同时且x和y的乘积最大。如果不存在这样的x和y,请输出“0 0”.
 * @author Dreihunde
 *
 */
public class DivisorMulMax {
	//对a,b先约分，再找满足约束的最大倍数 O(n) O(n)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().trim().split(" ");
        int A = Integer.parseInt(params[0]), B = Integer.parseInt(params[1]), a = Integer.parseInt(params[2]), b = Integer.parseInt(params[3]);
        int greatestCommonDivisor = gcd(a, b);
        // 先约分
        a /= greatestCommonDivisor;
        b /= greatestCommonDivisor;
        // 然后计算unit
        int unit = Math.min(A / a, B / b);
        // x占a份unit，y占b份unit
        System.out.println(unit * a + " " + unit * b);
    }
     
    private static int gcd1(int a, int b){
        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        while(b > 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd (b, a % b);
    }

}

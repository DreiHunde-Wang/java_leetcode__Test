package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Сǿ��Ҫ��[1,A]��ѡ��һ������x,��[1,B]��ѡ��һ������y.ʹ������x/y=a/b��ͬʱ��x��y�ĳ˻�������������������x��y,�������0 0��.
 * @author Dreihunde
 *
 */
public class DivisorMulMax {
	//��a,b��Լ�֣���������Լ��������� O(n) O(n)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().trim().split(" ");
        int A = Integer.parseInt(params[0]), B = Integer.parseInt(params[1]), a = Integer.parseInt(params[2]), b = Integer.parseInt(params[3]);
        int greatestCommonDivisor = gcd(a, b);
        // ��Լ��
        a /= greatestCommonDivisor;
        b /= greatestCommonDivisor;
        // Ȼ�����unit
        int unit = Math.min(A / a, B / b);
        // xռa��unit��yռb��unit
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

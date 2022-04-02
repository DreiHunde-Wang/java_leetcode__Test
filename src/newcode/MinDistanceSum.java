package newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * ��һ��2D��ͼ��Сǿ��n������,��Ϊ����λ�õ�ԭ��û�а취��ÿ�������ṩˮԴ,����Сǿ�����޽�һ��ƽ��y���ˮ��.��Ϊ����ˮ�����޳�.
 * �����ܹ�������һ��ƽ����y���ֱ��. ����Сǿ��ȷ���޽�ˮ����λ��,�ܹ�ʹ����n�����ӵ�ˮ���Ĵ�ֱ�������С,���������С�ľ����.
 * @author Dreihunde
 *
 */
public class MinDistanceSum {
	
	 //method ��λ����Ϊ���λ�� O(nlogn) O(1)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        for(int i = 0; i < n; i++)
            x[i] = Integer.parseInt(br.readLine().trim().split(" ")[0]);
        Arrays.sort(x);
        int median = x[n / 2];
        long res = 0;
        for(int i = 0; i < n; i++) res += Math.abs(median - x[i]);
        System.out.println(res);
    }

}

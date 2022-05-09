package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Сǿ��Ϊǿ��İ೤.�������Ű��������ڵĸ�ͬѧȥ����.·���ߵ�һ��,����ǰ����һ������.��ֻ��һ��С��.
 * ����ʵ�����,���С��һ�����ֻ������������.���ҹ��ӵ�ʱ���ǵ��������������ؽϴ���Ǹ��˵�����.���ֻ��һ����,��ô����ʱ���������˵�����.
 * ����Сǿ���������������ΰ��Ų��������ʱ����ʹ�����˶�ͨ����������.Сǿ����,��������֪��������ô����,ֻҪ���������̵�ʱ��.
 * @author Dreihunde
 *
 */
public class CrossRiver {
	//�ڴ��ڵ���4����ʱ��������������ȹ�ȥ������Ļ������������ص��ڹ�ȥ������Ļ���
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            String[] params = br.readLine().trim().split(" ");
            int[] weights = new int[n];
            for(int i = 0; i < n; i++) weights[i] = Integer.parseInt(params[i]);
            System.out.println(crossRiver(weights));
        }
    }
     
    private static int crossRiver(int[] weights) {
        Arrays.sort(weights);
        int n = weights.length;
        int totalTime = 0;
        while(n >= 4){
            /* 1.�����ÿ�ζ�������������ÿ����һ����
               2.��������ȹ�ȥ��������Ǹ��������������ص�������ȥ���Ǳ�����ٰѴ�������
            */
            totalTime += Math.min(weights[0]*2 + weights[n - 2] + weights[n - 1], 
                                  weights[0] + weights[1]*2 + weights[n - 1]);
            n -= 2;
        }
        // ��ʣ����4��
        if(n == 3)
            totalTime += weights[0] + weights[1] + weights[2];
        else
            totalTime += weights[1];     // ������������ٹ���
        return totalTime;
    }

}

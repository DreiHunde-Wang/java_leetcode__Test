package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ���Сǿ������һ�����ʽ����ᣬ����ڻ�����һ��Բ��Χ����һ�����ڴ���Ŀ�ľ����ò�ͬ���ҵ��˸���һ�²�ͬ��������Ϣ��Ϊ�˸��õشﵽ���Ŀ�ģ�Сǿϣ�������������֮��Ĳ���̶Ⱥ͡�
 * Ϊ�ˣ����ҵ����㣬ϣ�����ܸ�������һ����λ���ﵽ����֮��Ĳ���֮�����
 * @author Dreihunde
 *
 */
public class MaxAbsBetweenNear {
	//Ҫ������Ԫ�صľ��Բ�֮����󣬾�Ҫ��֤�������Եľ��Բ���ܾ��ȷֲ����������²����Ϳ��Դﵽ���Ч����
	//�Ƚ������������Ȼ�����Ϊǰ�����������飬��˳��ȡ�������������Ԫ��������ԡ�
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] strArr = br.readLine().trim().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(strArr[i]);
        Arrays.sort(arr);
        int midIdx = n % 2 == 0? n / 2: n / 2 + 1;
        int ptr1 = 0, ptr2 = midIdx;
        ArrayList<Integer> list = new ArrayList<>();
        while(ptr1 < midIdx && ptr2 < n){
            list.add(arr[ptr1 ++]);
            list.add(arr[ptr2 ++]);
        }
        if(ptr1 < midIdx) list.add(arr[ptr1]);
        if(ptr2 < n) list.add(arr[ptr2]);
        long sum = 0;
        for(int i = 0; i < n - 1; i++) sum += Math.abs(list.get(i + 1) - list.get(i));
        System.out.println(sum + Math.abs(list.get(0) - list.get(n - 1)));
        for(int i = 0; i < n; i++) System.out.print(list.get(i) + " ");
    }

}

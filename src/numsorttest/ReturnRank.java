package numsorttest;

import java.net.Socket;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import printtreenode.TreeNode;

/**
 * ����һ������Ϊ n ���������� score ������ score[i] �ǵ� i λ�˶�Ա�ڱ����еĵ÷֡����е÷ֶ� ������ͬ ��
 * �˶�Ա�����ݵ÷� �������� ���������ε� 1 ���˶�Ա�÷���ߣ����ε� 2 ���˶�Ա�÷ֵ� 2 �ߣ��������ơ�
 * �˶�Ա�����ξ��������ǵĻ������
 * ���ε� 1 ���˶�Ա����� "Gold Medal" ��
 * ���ε� 2 ���˶�Ա������ "Silver Medal" ��
 * ���ε� 3 ���˶�Ա��ͭ�� "Bronze Medal" ��
 * �����ε� 4 ���� n ���˶�Ա��ֻ�ܻ�����ǵ����α�ţ��������ε� x ���˶�Ա��ñ�� "x"����
 * ʹ�ó���Ϊ n ������ answer ���ػ񽱣����� answer[i] �ǵ� i λ�˶�Ա�Ļ������
 * @author Dreihunde
 *
 */
public class ReturnRank {
	//method 1 HashMap O(nlogn) O(n)
    public String[] findRelativeRanks1(int[] score) {
        int[] ans = Arrays.copyOf(score, score.length);
        Arrays.sort(ans);
        String[] rev = new String[score.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = ans.length - 1; i >= 0; i--) {
            map.put(ans[i], ans.length - 1 - i);
        }

        for (int i = 0; i < score.length; i++) {
            if (map.get(score[i]) == 0) {
                rev[i] = "Gold Medal";
            } else if (map.get(score[i]) == 1) {
                rev[i] = "Silver Medal";
            } else if (map.get(score[i]) == 2) {
                rev[i] = "Bronze Medal";
            } else {
                rev[i] = String.valueOf(map.get(score[i]) + 1);
            }  
        }

        return rev;

    }
    
    //method 2 HashMap O(nlogn) O(n)
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        //Ԥ��
        String[] ss = new String[] {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
        	arr[i][0] = score[i];
        	arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> (b[0] - a[0]));
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            if (i < 3) {
            	ans[arr[i][1]] = ss[i]; 
            } else {
            	ans[arr[i][1]] = String.valueOf(i + 1);
            }
        }

        return ans;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReturnRank rr = new ReturnRank();
		int[] score = new int[] {5,4,3,2,1};
		
		long startTime = System.nanoTime();
		CommonTest.printNum(rr.findRelativeRanks1(score));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(rr.findRelativeRanks2(score));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

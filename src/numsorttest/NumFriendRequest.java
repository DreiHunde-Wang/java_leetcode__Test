package src.numsorttest;

import java.util.Arrays;

/**
 * ���罻ý����վ���� n ���û�������һ���������� ages ������ ages[i] �ǵ� i ���û������䡣
 * �����������һ������Ϊ�棬��ô�û� x ���������û� y��x != y�����ͺ�������
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * ����x ������ y ����һ����������
 * ע�⣬��� x �� y ����һ����������y ����Ҳ�� x ����һ�������������⣬�û��������Լ����ͺ�������
 * �����ڸ��罻ý����վ�ϲ����ĺ�������������
 * @author Dreihunde
 *
 */
public class NumFriendRequest {
	//method 1 ˫�ر��� O(n2) O(1) ��ʱ
    public int numFriendRequests1(int[] ages) {
        int count = 0;
        int n = ages.length;
        Arrays.sort(ages);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if(canSend(ages, i, j)) {
                    count++;
                }
            }
        }
        return count;
        
    }

    private boolean canSend(int[] ages, int i, int j) {
        if (i == j || ages[i] / 2 + 7 >= ages[j] || ages[j] > ages[i]) {
            return false;
        }
        return true;
    }

    //method 2 ˫ָ�� O(nlogn) O(logn)
    public int numFriendRequests2(int[] ages) {
        int count = 0;
        int n = ages.length;
        int left = 0;
        int right = 0;
        Arrays.sort(ages);
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            
            while (ages[left] <= age / 2 + 7) {
                left++;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                right++;
            }
            count += right - left;
        }
        return count;
        
    }

    //method 3 ǰ׺��+�������� O(n + C) O(C)
    public int numFriendRequests3(int[] ages) {
        int[] cnt = new int[121];

        for (int age : ages) {
            cnt[age]++;
        }
        int[] pre = new int[121];
        for (int i = 1; i < 121; i++) {
            pre[i] = pre[i - 1] + cnt[i];
        }

        int count = 0;
        for (int i = 15; i < 121; i++) {
            int left = i / 2 + 8;
            count += cnt[i] * (pre[i] - pre[left - 1] - 1);
        }

        return count;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ages = new int[] {20,30,100,110,120};
		
		NumFriendRequest nf = new NumFriendRequest();
		long startTime = System.nanoTime();
		System.out.println(nf.numFriendRequests1(ages));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(nf.numFriendRequests2(ages));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(nf.numFriendRequests3(ages));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");


	}

}

package src.numsorttest;

import java.util.Arrays;

/**
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 * @author Dreihunde
 *
 */
public class NumFriendRequest {
	//method 1 双重遍历 O(n2) O(1) 超时
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

    //method 2 双指针 O(nlogn) O(logn)
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

    //method 3 前缀和+计数排序 O(n + C) O(C)
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

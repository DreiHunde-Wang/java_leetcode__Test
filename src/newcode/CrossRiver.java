package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 小强作为强班的班长.决定带着包含他在内的个同学去春游.路程走到一半,发现前面有一条河流.且只有一条小船.
 * 经过实验后发现,这个小船一次最多只能运送两个人.而且过河的时间是等于两个人中体重较大的那个人的体重.如果只有一个人,那么过河时间就是这个人的体重.
 * 现在小强想请你帮他分析如何安排才能在最短时间内使所有人都通过这条河流.小强很懒,他并不想知道具体怎么过河,只要你告诉他最短的时间.
 * @author Dreihunde
 *
 */
public class CrossRiver {
	//在大于等于4个人时，让最轻的两个先过去，最轻的回来，两个最重的在过去，次轻的回来
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
            /* 1.最轻的每次都将船开回来，每次载一个；
               2.最轻的两先过去，最轻的那个开船回来让最重的两个过去，那边轻的再把船开回来
            */
            totalTime += Math.min(weights[0]*2 + weights[n - 2] + weights[n - 1], 
                                  weights[0] + weights[1]*2 + weights[n - 1]);
            n -= 2;
        }
        // 还剩不到4人
        if(n == 3)
            totalTime += weights[0] + weights[1] + weights[2];
        else
            totalTime += weights[1];     // 最轻的两个快速过河
        return totalTime;
    }

}

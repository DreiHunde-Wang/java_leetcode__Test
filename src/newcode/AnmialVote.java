package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 很久很久以前，在蚂蚁森林里住着n只小动物，编号从1到n。编号越小的动物能力值越大。现在他们想投票选出一只小动物当森林之王，对于每只小动物来说，如果他有崇拜的对象，那么他可能投票选择自己，或与自己崇拜的对象投相同票；如果他没有崇拜的对象，那么他投票只可能选择自己。
 * 每只小动物只会崇拜能力值比自己大的小动物。
 * 记者小强拜访了这n只小动物，了解到每只小动物是否有崇拜的对象以及具体是谁。现在他想知道每个人能得到的最高票数是多少。
 * @author Dreihunde
 *
 */
public class AnmialVote {
	
	//逆序遍历 小动物可以把别人给自己的票也投出去
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] strParams = br.readLine().trim().split(" ");
        int[] worshipId = new int[n + 1];
        int[] tickets = new int[n + 1];
        Arrays.fill(tickets, 1);
        for(int i = 0; i < n; i++) worshipId[i + 1] = Integer.parseInt(strParams[i]);
        for(int i = n; i >= 1; i--)
            tickets[worshipId[i]] += tickets[i];   // 有崇拜对象，假设把自己身上所有的票都投给崇拜对象
        for(int i = 1; i <= n; i++)
            System.out.println(tickets[i]);
    }

}

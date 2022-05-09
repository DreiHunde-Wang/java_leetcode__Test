package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * �ܾúܾ���ǰ��������ɭ����ס��nֻС�����Ŵ�1��n�����ԽС�Ķ�������ֵԽ������������ͶƱѡ��һֻС���ﵱɭ��֮��������ÿֻС������˵��������г�ݵĶ�����ô������ͶƱѡ���Լ��������Լ���ݵĶ���Ͷ��ͬƱ�������û�г�ݵĶ�����ô��ͶƱֻ����ѡ���Լ���
 * ÿֻС����ֻ��������ֵ���Լ����С���
 * ����Сǿ�ݷ�����nֻС����˽⵽ÿֻС�����Ƿ��г�ݵĶ����Լ�������˭����������֪��ÿ�����ܵõ������Ʊ���Ƕ��١�
 * @author Dreihunde
 *
 */
public class AnmialVote {
	
	//������� С������԰ѱ��˸��Լ���ƱҲͶ��ȥ
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] strParams = br.readLine().trim().split(" ");
        int[] worshipId = new int[n + 1];
        int[] tickets = new int[n + 1];
        Arrays.fill(tickets, 1);
        for(int i = 0; i < n; i++) worshipId[i + 1] = Integer.parseInt(strParams[i]);
        for(int i = n; i >= 1; i--)
            tickets[worshipId[i]] += tickets[i];   // �г�ݶ��󣬼�����Լ��������е�Ʊ��Ͷ����ݶ���
        for(int i = 1; i <= n; i++)
            System.out.println(tickets[i]);
    }

}

package dfsandbfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;

/**
 * ����һ������ n �����ֵ��򷵻ط�Χ [1, n] ������������
 * ��������һ��ʱ�临�Ӷ�Ϊ O(n) ��ʹ�� O(1) ����ռ���㷨��
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 * @author Dreihunde
 *
 */
public class LexicalOrder {
	//method 1 dfs+��֦���ݹ�ʵ��) O(n) O(1)
    List<Integer> list;
    public List<Integer> lexicalOrder1(int n) {
       list = new ArrayList<>();
       dfs(0, 0, n);
       return list;
    }

    private void dfs(int cur, int sum, int n) {
        if (sum > n) {
            return;
        } else if (cur > 0) {
            list.add(sum);
        }
        for (int i = 0; i < 10; i++) {
            if (cur == 0 && i == 0) {
                continue;
            }
            dfs(cur + 1, sum * 10 + i, n);
        }
    }

    //method 2 dfs+��֦������ʵ��) O(n) O(1)
    public List<Integer> lexicalOrder(int n) {
       list = new ArrayList<>();
       int num = 1;
       for (int i = 0; i < n; i++) {
           list.add(num);
           if (num * 10 <= n) {
               num *= 10;
           } else {
               while (num % 10 == 9 || num + 1 > n) {
                   num /= 10;
               }
               num++;
           }
       }
       return list;
    }
    
    @RepeatedTest(value = 3)
    public void lexicalOrderTest() {
    	Random rand = new Random();
    	int n = rand.nextInt(20);
    	System.out.println(n);
    	List<Integer> list = lexicalOrder(n);
    	System.out.println(list);
    }

}

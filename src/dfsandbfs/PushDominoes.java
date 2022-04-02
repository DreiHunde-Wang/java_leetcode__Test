package dfsandbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * n �Ŷ���ŵ�����ų�һ�У���ÿ�Ŷ���ŵ���ƴ�ֱ�������ڿ�ʼʱ��ͬʱ��һЩ����ŵ��������������ơ�
 * ÿ��һ�룬������ߵĶ���ŵ���ƻ��ƶ���������ڵĶ���ŵ���ơ�ͬ���أ������ұߵĶ���ŵ����Ҳ���ƶ����������Ҳ�����ڶ���ŵ���ơ�
 * ���һ�Ŵ�ֱ�����Ķ���ŵ���Ƶ�����ͬʱ�ж���ŵ���Ƶ���ʱ����������ƽ�⣬ �ù�����Ȼ���ֲ��䡣
 * �����������ԣ����ǻ���Ϊһ�����ڵ��µĶ���ŵ���Ʋ�����������ڵ��»��Ѿ����µĶ���ŵ����ʩ�Ӷ��������
 * ����һ���ַ��� dominoes ��ʾ��һ�ж���ŵ���Ƶĳ�ʼ״̬�����У�
 * dominoes[i] = 'L'����ʾ�� i �Ŷ���ŵ���Ʊ�������࣬
 * dominoes[i] = 'R'����ʾ�� i �Ŷ���ŵ���Ʊ������Ҳ࣬
 * dominoes[i] = '.'����ʾû���ƶ��� i �Ŷ���ŵ���ơ�
 * ���ر�ʾ����״̬���ַ�����
 * ���ӣ�https://leetcode-cn.com/problems/push-dominoes
 * @author Dreihunde
 *
 */
public class PushDominoes {
	//method 1 ģ�� O(n) O(n)
    public String pushDominoes1(String dominoes) {
        char[] s = dominoes.toCharArray();
        int n = s.length, i = 0;
        char left = 'L';
        while (i < n) {
            int j = i;
            while (j < n && s[j] == '.') { // �ҵ�һ��������û�б��ƶ��Ĺ���
                j++;
            }
            char right = j < n ? s[j] : 'R';
            if (left == right) { // ������ͬ����ô��Щ��������Ҳ�ᵹ��ͬһ����
                while (i < j) {
                    s[i++] = right;
                }
            } else if (left == 'R' && right == 'L') { // ������ԣ���ô�ʹ��������м䵹
                int k = j - 1;
                while (i < k) {
                    s[i++] = 'R';
                    s[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return new String(s);
    }

    //method 2 ��Դbfs O(n) O(n)
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        Deque<Integer> queue = new ArrayDeque<Integer>();
        int[] time = new int[n];
        Arrays.fill(time, -1);
        List<Character>[] force = new List[n];
        for (int i = 0; i < n; i++) {
            force[i] = new ArrayList<Character>();
        }
        for (int i = 0; i < n; i++) {
            char f = dominoes.charAt(i);
            if (f != '.') {
                queue.offer(i);
                time[i] = 0;
                force[i].add(f);
            }
        }

        char[] res = new char[n];
        Arrays.fill(res, '.');
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (force[i].size() == 1) {
                char f = force[i].get(0);
                res[i] = f;
                int ni = f == 'L' ? i - 1 : i + 1;
                if (ni >= 0 && ni < n) {
                    int t = time[i];
                    if (time[ni] == -1) {
                        queue.offer(ni);
                        time[ni] = t + 1;
                        force[ni].add(f);
                    } else if (time[ni] == t + 1) {
                        force[ni].add(f);
                    }
                }
            }
        }
        return new String(res);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

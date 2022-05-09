package src.printtreenode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.numsorttest.CommonTest;
import src.printtreenode.Trie;

/**
 * ����һ�� �����ظ� ���ʵ��ַ������� words �������ҳ������� words �е����� ���Ӵ� ��
 * ���Ӵ� ����Ϊ��һ����ȫ�ɸ��������е����������϶̵�����ɵ��ַ�����
 * @author Dreihunde
 *
 */
public class FindAllConcateWord {
	Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict1(String[] words) {
        List<String> ans = new ArrayList<>();
        //���ַ������ȴ�С��������
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //���ַ�������
            if (word.length() == 0) {
                continue;
            }
            //�����ǰ�ַ���Դ���ֵ���ƴ�������𰸣� ��������ֵ���
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                trie.insert(word);
            }
        }
        return ans;

    }
    //method 1 dfs O(��l * logn(����) + ��l^2(ƥ��)) O(��l*C)
    private boolean dfs(String word, int start) {
        //��������������ַ�����˵��word���ַ���ƴ��
        if (word.length() == start) {
            return true;
        }
        Trie node = trie;
        //��ȱ��������ַ���
        for (int i = start; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            node = node.childrens[idx];
            //��node�����ڣ���ζ���ַ�������
            if (node == null) {
                return false;
            }
            //�����ǰ��Ӧ�ַ����������������һ���ַ���
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
    	trie = new Trie();
        List<String> ans = new ArrayList<>();
        //���ַ������ȴ�С��������
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //���ַ�������
            if (word.length() == 0) {
                continue;
            }
            
            boolean[] visited = new boolean[words.length];
            //�����ǰ�ַ���Դ���ֵ���ƴ�������𰸣� ��������ֵ���
            if (dfs(word, 0, visited)) {
                ans.add(word);
            } else {
                trie.insert(word);
            }
        }
        return ans;

    }
    //method 2 dfs + ���仯���� O(��l * logn(����) + ��l^2(ƥ��)) O(��l*C + n)
    private boolean dfs(String word, int start, boolean[] visited) {
        //��������������ַ�����˵��word���ַ���ƴ��
        if (word.length() == start) {
            return true;
        }
        if (visited[start]) {
        	return false;
        }
        visited[start] = true;
        Trie node = trie;
        //��ȱ��������ַ���
        for (int i = start; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            node = node.childrens[idx];
            //��node�����ڣ���ζ���ַ�������
            if (node == null) {
                return false;
            }
            //�����ǰ��Ӧ�ַ����������������һ���ַ���
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		
		FindAllConcateWord fa = new FindAllConcateWord();
		
		ComnTest.printList(fa.findAllConcatenatedWordsInADict1(words));
		ComnTest.printList(fa.findAllConcatenatedWordsInADict2(words));

	}

}

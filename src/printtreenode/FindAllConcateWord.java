package src.printtreenode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.numsorttest.CommonTest;
import src.printtreenode.Trie;

/**
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * @author Dreihunde
 *
 */
public class FindAllConcateWord {
	Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict1(String[] words) {
        List<String> ans = new ArrayList<>();
        //按字符串长度从小到大排序
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //空字符串跳过
            if (word.length() == 0) {
                continue;
            }
            //如果当前字符来源于字典树拼接则加入答案， 否则插入字典树
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                trie.insert(word);
            }
        }
        return ans;

    }
    //method 1 dfs O(∑l * logn(排序) + ∑l^2(匹配)) O(∑l*C)
    private boolean dfs(String word, int start) {
        //如果遍历完所有字符，则说明word由字符树拼接
        if (word.length() == start) {
            return true;
        }
        Trie node = trie;
        //深度遍历访问字符树
        for (int i = start; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            node = node.childrens[idx];
            //当node不存在，意味着字符树见底
            if (node == null) {
                return false;
            }
            //如果当前对应字符串结束，则访问下一个字符串
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
        //按字符串长度从小到大排序
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //空字符串跳过
            if (word.length() == 0) {
                continue;
            }
            
            boolean[] visited = new boolean[words.length];
            //如果当前字符来源于字典树拼接则加入答案， 否则插入字典树
            if (dfs(word, 0, visited)) {
                ans.add(word);
            } else {
                trie.insert(word);
            }
        }
        return ans;

    }
    //method 2 dfs + 记忆化搜索 O(∑l * logn(排序) + ∑l^2(匹配)) O(∑l*C + n)
    private boolean dfs(String word, int start, boolean[] visited) {
        //如果遍历完所有字符，则说明word由字符树拼接
        if (word.length() == start) {
            return true;
        }
        if (visited[start]) {
        	return false;
        }
        visited[start] = true;
        Trie node = trie;
        //深度遍历访问字符树
        for (int i = start; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            node = node.childrens[idx];
            //当node不存在，意味着字符树见底
            if (node == null) {
                return false;
            }
            //如果当前对应字符串结束，则访问下一个字符串
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

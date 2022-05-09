package src.stringnumtest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordInWords {
	//method 1 hash¼ÇÂ¼ O(nlogn) O(n)
	public String longestWord1(String[] words) {
        Arrays.sort(words, (a, b) ->  {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });
        String longest = "";
        Set<String> candidates = new HashSet<String>();
        candidates.add("");
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (candidates.contains(word.substring(0, word.length() - 1))) {
                candidates.add(word);
                longest = word;
            }
        }
        return longest;
    }
	
	//method 2 ×ÖµäÊ÷ O(n) O(n)
    public String longestWord(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        String longest = "";
        for (String word : words) {
            if (root.search(word)) {
                if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        

        return longest;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


class Trie {
    private Trie[] children;
    boolean isEnd;
    int len;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
        len = 0;
    }

    public void insert(String word) {
        int n = word.length();
        Trie node = this;
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node.children[idx].len = node.len + 1;
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public Trie searchPrefix(String word) {
        int n = word.length();
        Trie node = this;
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null || !node.children[idx].isEnd) {
                return null;
            }
            node.children[idx].len = node.len + 1;
            node = node.children[idx];
        }
        return node;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
}

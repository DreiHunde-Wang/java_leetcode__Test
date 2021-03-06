package src.classdesign;
 
/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
 * 否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；
 * 否则，返回 false 。
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * @author Dreihunde
 *
 */
class Trie {
    //26个字母
    private Trie[] childrens;
    private boolean isEnd;

    public Trie() {
        childrens = new Trie[26];
        isEnd = false;
    }
    
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.childrens[idx] == null) {
                node.childrens[idx] = new Trie();
            }
            node = node.childrens[idx];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            //每一层节点都比较
            int idx = prefix.charAt(i) - 'a';
            if (node.childrens[idx] == null) {
                return null;
            }
            node = node.childrens[idx];
        }
        return node;
    }
}
package src.printtreenode;

/**
 * Trie（发音类�? "try"）或者说 前缀�? 是一种树形数据结构，用于高效地存储和�?索字符串数据集中的键�?
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查�??
 * 请你实现 Trie 类：
 * Trie() 初始化前�?树对象�??
 * void insert(String word) 向前�?树中插入字符�? word �?
 * boolean search(String word) 如果字符�? word 在前�?树中，返�? true（即，在�?索之前已经插入）；否则，返回 false �?
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前�?之一�? prefix ，返�? true ；否则，返回 false �?

 * @author Dreihunde
 *
 */
public class Trie {
	//26个字�? 初始化O(1) 操作O(S) 空间复杂�?:O(T*C)
    public Trie[] childrens;
    public boolean isEnd;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie node = new Trie();
		node.insert("test");
		node.insert("text");
		System.out.println(node.search("test"));
		System.out.println(node.startsWith("te"));

	}

}

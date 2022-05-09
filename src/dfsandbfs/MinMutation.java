package src.dfsandbfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MinMutation {
	//method 1 bfs O(Cmn) O(mn) C=4 m=8 n = bank.length
    public int minMutation1(String start, String end, String[] bank) {
        Set<String> cnt = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        char[] keys = {'A', 'C', 'G', 'T'};        
        for (String w : bank) {
            cnt.add(w);
        }
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
    int ans;
    //method 2 dfs + 回溯 O(mn) O(mn) 
    public int minMutation2(String start, String end, String[] bank) {
        ans = Integer.MAX_VALUE;
        boolean[] visited = new boolean[bank.length];
        dfs(start, end, bank, visited, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public void dfs(String start, String end, String[] bank, boolean[] visited, int count) {
        if (count > ans) {
            return;
        }
        if (start.equals(end)) {
            ans = Math.min(ans, count);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            if (visited[i]) {
                continue;
            }
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                diff += start.charAt(j) == bank[i].charAt(j) ? 0 : 1;
            }
            if (diff == 1) {
                visited[i] = true;
                dfs(bank[i], end, bank, visited, count + 1);
                visited[i] = false;
            }
        }
    }

    //method 3 启发式算法 
    class Node {
        String s;
        int val;
        Node(String _s) {
            s = _s;
            for (int i = 0; i < 8; i++) {
                if (s.charAt(i) != T.charAt(i)) val++;
            }
        }
    }
    static char[] items = new char[]{'A', 'C', 'G', 'T'};
    String S, T;
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String s : bank) set.add(s);
        S = start; T = end;
        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->a.val-b.val);
        Map<String, Integer> map = new HashMap<>();
        q.add(new Node(S));
        map.put(S, 0);
        while (!q.isEmpty()) {
            Node node = q.poll();
            char[] cs = node.s.toCharArray();
            int step = map.get(node.s);
            for (int i = 0; i < 8; i++) {
                for (char c : items) {
                    if (cs[i] == c) continue;
                    char[] clone = cs.clone();
                    clone[i] = c;
                    String sub = String.valueOf(clone);
                    if (!set.contains(sub)) continue;
                    if (sub.equals(T)) return step + 1;
                    if (!map.containsKey(sub) || map.get(sub) > step + 1) {
                        map.put(sub, step + 1);
                        q.add(new Node(sub));
                    }
                }
            }
        }
        return -1;
    }
    
    @Test
    public void test() {
    	String start = "AACCGGTT";
    	String end = "AAACGGTA";
    	String[] bank = new String[] {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
    	int i = minMutation(start, end, bank);
    	System.out.println(i);
    }

}

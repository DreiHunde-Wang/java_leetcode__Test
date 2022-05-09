package src.dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.numsorttest.CommonTest;

/**
 * 有一�? n 个人作为实验对象，从 0 �? n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）�??
 * 为了方便起见，我们将编号为 x 的人�?称为 "person x "�?
 * 给你�?个数�? richer ，其�? richer[i] = [ai, bi] 表示 person ai �? person bi 更有钱�??
 * 另给你一个整数数�? quiet ，其中 quiet[i] �? person i 的安静�?��??
 * richer 中所给出的数据�?�辑自恰（也就是说，�? person x �? person y 更有钱的同时�?
 * 不会出现 person y �? person x 更有钱的情况 ）�??
 * 现在，返回一个整数数�? answer 作为答案，其中 answer[x] = y 的前提是�?
 * 在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] �?小的人）�?
 * @author Dreihunde
 *
 */
public class LoudAndRich {
	//method 1 dfs O(n+m) O(n + m)
    int[] rev;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        if (n == 0)
            return new int[0];
        List<Node> relatedList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            relatedList.add(new Node(quiet[i], i));
        }

        for (int[] rich : richer) {
            Node.ANextIsB(relatedList.get(rich[1]), relatedList.get(rich[0]));
        }
        rev = new int[n];
        Arrays.fill(rev, -1);

        for (int i = 0; i < n; i++) {
            dfs(i, quiet, relatedList);
        }
        return rev;
    }
    
    //深度优先
    private void dfs(int x, int[] quiet, List<Node> list) {
        if (rev[x] != -1)
            return;
        rev[x] = x;
        for (Node t : list.get(x).next) {
            dfs(t.idx, quiet, list);
            if (quiet[rev[t.idx]] < quiet[rev[x]]) {
                rev[x] = rev[t.idx];
            }
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] richer = new int[][] {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
		int[] quiet = new int[] {3,2,5,4,6,1,7,0};
		LoudAndRich la = new LoudAndRich();
		CommonTest.printNum(la.loudAndRich(richer, quiet));

	}

}
class Node {
    int quiet;
    int idx;
    List<Node> next;

    public Node(int quiet, int idx) {
        this.quiet = quiet;
        this.idx = idx;
        next = new ArrayList<>();
    }

    public static void ANextIsB(Node A, Node B) {
        A.next.add(B);
    }

}
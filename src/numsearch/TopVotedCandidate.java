package src.numsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你两个整数数组 persons �? times 。在选举中，第 i 张票是在时刻为 times[i] 时投给�?��?�人 persons[i] 的�??
 * 对于发生在时�? t 的每个查询，�?要找出在 t 时刻在�?�举中领先的候�?�人的编号�??
 * 在 t 时刻投出的�?�票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候�?�人将会获胜�?
 * 实现 TopVotedCandidate 类：
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons �? times 数组初始化对象�??
 * int q(int t) 根据前面描述的规则，返回在时�? t 在�?�举中领先的候�?�人的编号�??
 * @author Dreihunde
 *
 */
public class TopVotedCandidate {
	List<Integer> tops;
    Map<Integer, Integer> voteCnt;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        tops = new ArrayList<>();
        voteCnt = new HashMap<>();
        this.times = times;
        voteCnt.put(-1, -1);
        int top = -1;
        for (int i = 0; i < persons.length; i++) {
            int p = persons[i];
            voteCnt.put(p, voteCnt.getOrDefault(p, 0) + 1);
            if (voteCnt.get(p) >= voteCnt.get(top)) {
                top = p;
            }
            tops.add(top);
        }
    }
    
    public int q(int t) {
        int l = 0, r = times.length - 1;
        //找到l <= t的最大�?�l
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (t >= times[mid]) {
                l = mid;
            } else if (t < times[mid]) {
                r = mid - 1;
            }
        }
        return tops.get(l);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

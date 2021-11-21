package enumtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinWatch {
	//method 1 直接枚举
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<>();

        for (int h = 0; h <= 11; h++) {
            for (int m = 0; m <= 59; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;

    }

    //method 2 打表法
    static Map<Integer, List<String>> map = new HashMap<>();
    static {
        for (int h = 0; h <= 11; h++) {
            for (int m = 0; m <= 59; m++) {
                int tot = Integer.bitCount(h) + Integer.bitCount(m);
                List<String> list = map.getOrDefault(tot, new ArrayList<String>());
                list.add(h + ":" + (m < 10 ? "0" + m : m));
                map.put(tot, list);
            }
        }
    }

    public List<String> readBinaryWatch(int turnedOn) {
        return map.getOrDefault(turnedOn, new ArrayList<String>());

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

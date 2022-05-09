package src.simulationtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 假设 Andy �? Doris 想在晚餐时�?�择�?家餐厅，并且他们都有�?个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示�??
 * 你需要帮助他们用�?少的索引和找出他们共同喜爱的餐厅�? 如果答案不止�?个，则输出所有答案并且不考虑顺序�? 
 * 你可以假设答案�?�是存在�?
 * 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
 * @author Dreihunde
 *
 */
public class FindRestaurant {
	//method 1 哈希�? O(m + n) O(m + n)
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        int m = list1.length;
        int n = list2.length;
        for (int i = 0; i < m; i++) {
            map.put(list1[i], i);
        }
        int minIndexSum = m + n;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > minIndexSum) {
                break;
            }
            if (map.containsKey(list2[i])) {
                int indexSum = i + map.get(list2[i]);
                if (indexSum < minIndexSum) {
                    minIndexSum = indexSum;
                    ans.clear();
                    ans.add(list2[i]);
                } else if (indexSum == minIndexSum){
                    ans.add(list2[i]);
                }
            }
        }

        return ans.toArray(new String[0]);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

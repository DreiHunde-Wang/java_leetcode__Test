package src.classdesign;

import java.util.Map;
import java.util.TreeMap;

/**
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 * 实现 RangeModule 类:
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 * @author Dreihunde
 * https://leetcode.cn/problems/range-module/
 */

//method 1 TreeMap O(log(a + r)) O(a + r) a为add的次数，r为remove的次数
public class RangeModule {
	TreeMap<Integer, Integer> intervals;
    public RangeModule() {
         intervals = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        //如果新输入区间与原始区间重叠，则合并重叠区间
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {   
                return;
            }
            if (start != null && start.getValue() >= left) {
                left = start.getKey();
                intervals.remove(start.getKey());
            }
        }
        //如果之后的区间有重叠，就合并重叠区间
        while (entry != null && entry.getKey() <= right) {
            right = Math.max(entry.getValue(), right);
            intervals.remove(entry.getKey());
            entry = intervals.higherEntry(entry.getKey());
        }
        intervals.put(left, right);

    }
    
    public boolean queryRange(int left, int right) {
        //比left大的第一个区间
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        //如果intervals第一个区间都比left大，说明肯定不包含
        if (entry == intervals.firstEntry()) {
            return false;
        }
        //倒退到前一个区间，此时entry.key <= left
        entry = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        //所以只要entry.value > right，就能包含整个区间了
        return entry != null && entry.getValue() >= right;
    }
    
    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            //start.getKey() <= left
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            //如果entry包含新区间，则删除重叠的部分
            if (start != null && start.getValue() >= right) {
                //提前保存entry.value 防止被覆盖
                int ri = start.getValue();
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    //覆盖原来区间的右边界
                    intervals.put(start.getKey(), left);
                }

                if (ri != right) {
                    intervals.put(right, ri);
                }
                return;
            } else if (start != null && start.getValue() > left){
                intervals.put(start.getKey(), left);
            }
        } 
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {
                intervals.remove(entry.getKey());
                entry = intervals.higherEntry(entry.getValue());
            } else {
                intervals.put(right, entry.getValue());
                intervals.remove(entry.getKey());
                break;
            }  
        }
    }

}

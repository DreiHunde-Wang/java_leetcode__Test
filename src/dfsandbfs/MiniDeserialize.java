package dfsandbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * 链接：https://leetcode-cn.com/problems/mini-parser
 * @author Dreihunde
 *
 */
public class MiniDeserialize {
	//method 1 dfs模拟 O(n) O(n)
    int index = 0;
    public NestedInteger deserialize1(String s) {
        if (s.charAt(index) == '[') {
            index++;
            NestedInteger ni = new NestedInteger();
            while (s.charAt(index) != ']') {
                ni.add(deserialize(s));
                if (s.charAt(index) == ',') {
                    index++;
                }
            }
            index++;
            return ni;
        } else {
            boolean neg = false;
            if (s.charAt(index) == '-') {
                neg = true;
                index++;
            }
            int num = 0;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            if (neg) {
                num *= -1;
            }
            return new NestedInteger(num);
        }     
    }

    //method 2 stack O(n) O(n)
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        ArrayDeque<NestedInteger> stack = new ArrayDeque<>();
        int num = 0;
        boolean neg = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                neg = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                stack.push(new NestedInteger());
            }else if (c == ',' || c == ']') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    if (neg) {
                        num *= -1;
                    }
                    stack.peek().add(new NestedInteger(num));
                }
                num = 0;
                neg = false;
                if (c == ']' && stack.size() > 1) {
                    NestedInteger ni = stack.pop();
                    stack.peek().add(ni);
                }
            }        
        }
        return stack.peek();
    }

}

class NestedInteger {
	int value;
	ArrayList<NestedInteger> list;
	public NestedInteger() {
		value = 0;
		list = new ArrayList<>();
	}
	public NestedInteger(int _value) {
		value = _value;
	}
	
	public void setInteger(int _value) {
		value = _value;
	}
	public void add(NestedInteger ni) {
		list.add(ni);
	}
}

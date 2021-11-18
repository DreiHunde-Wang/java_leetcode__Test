package slidewindowtest;

import java.util.HashMap;
import java.util.HashSet;

public class LongSubWoRepeat {
	
	//method 1 ��������
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0)
            return 0;

        int rb = 0;
        int ans = 0;

        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (i != 0)
                set.remove(s.charAt(i - 1));
            while (rb < s.length() && !set.contains(s.charAt(rb))) {
                set.add(s.charAt(rb));
                rb++;
            }
            ans = Math.max(ans, rb - i);
        }

        return ans;
        
    }
    /**
    1�����ȣ��жϵ�ǰ�ַ��Ƿ������map�У�����������������ַ���ӵ�map���ַ����ַ��������±꣩,
     ��ʱû�г����ظ����ַ�����ָ�벻��Ҫ�仯����ʱ���ظ��Ӵ��ĳ���Ϊ��i-left+1����ԭ����maxLen�Ƚϣ�ȡ���ֵ��

    2�������ǰ�ַ� ch ������ map�У���ʱ��2�������
     1����ǰ�ַ������ڵ�ǰ��Ч���Ӷ��У��磺abca�������Ǳ������ڶ���a����ǰ��Ч��Ӷ��� abc�������ֱ�����a��
     ��ô��ʱ���� left Ϊ map.get(a)+1=1����ǰ��Ч�Ӷθ���Ϊ bca��
     2����ǰ�ַ��������ڵ�ǰ���Ч�Ӷ��У��磺abba�����������a,b��map����ʱleft=0�����������b������map�а���b��
     ����b���������Ч�Ӷ��У�����1������������Ǹ��� left=map.get(b)+1=2����ʱ�Ӷθ���Ϊ b������map����Ȼ����a��map.get(a)=0��
     ������Ǳ�����a������a������map�У���map.get(a)=0�����������1��һ�������ͻᷢ�� left=map.get(a)+1=1��ʵ���ϣ�left��ʱ
     Ӧ�ò��䣬leftʼ��Ϊ2���Ӷα�� ba�Ŷԡ�

     Ϊ�˴�������2�����������ÿ�θ���left��left=Math.max(left , map.get(ch)+1).
     ���⣬����left�󣬲���ԭ���� s.charAt(i) �Ƿ�����Ӷ��У����Ƕ�Ҫ�� s.charAt(i) ��λ�ø���Ϊ��ǰ��i��
     ��˴�ʱ�µ� s.charAt(i) �Ѿ����뵽 ��ǰ����Ӷ��У�
     */
    //method 2 map�Ż�����Ծ����
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0)
            return 0;

        int left = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
            	left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
        
    }
    
    public static void main(String[] args) {
    	String s = "abcdeftbreweqeqggasg3eg";
    	long startTime = System.nanoTime();
		System.out.println(lengthOfLongestSubstring1(s));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(lengthOfLongestSubstring2(s));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

package enumtest;

public class BuddyString {
	public boolean buddyStrings1(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        
        int[] mask1 = new int[26];
        int[] mask2 = new int[26];
        int sum = 0;
        boolean isTwoSame = false;;
        for (int i = 0; i < s.length(); i++) {
            mask1[s.charAt(i) - 'a']++;
            mask2[goal.charAt(i) - 'a']++;
            if (s.charAt(i) - goal.charAt(i) != 0) {
                sum++;
            }
        }
        for (int j = 0; j < 26; j++) {
            if (mask1[j] != mask2[j])
                return false;
            if (mask1[j] > 1)
                isTwoSame = true;
        }
        return sum == 2 || (sum == 0 && isTwoSame);
    }
	
	public boolean buddyStrings2(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        
        int[] mask = new int[26];
        if (s.equals(goal)) {
        	for (int i = 0; i < s.length(); i++) {
        		int t = s.charAt(i) - 'a';
        		if (mask[t] > 0)
        			return true;
        		mask[t]++;
        	}
        	return false;
        } else {
        	int first = -1; 
        	int second = -1;
        	for (int j = 0; j < s.length(); j++) {
            	if (s.charAt(j) != goal.charAt(j)) {
            		if (first == -1) {
            			first = j;
            		} else if (second == -1) {
            			second = j;
            		} else {
            			return false;
            		}           		
            	}
            }
        	return second != -1 && (s.charAt(first) == goal.charAt(second) 
        			&& s.charAt(second) == goal.charAt(first));
        }
      
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "abbaabcdefd";
		String str2 = "adbaabcdefb";
		BuddyString bs = new BuddyString();
		
		long startTime = System.nanoTime();
		System.out.println(bs.buddyStrings1(str1, str2));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(bs.buddyStrings1(str1, str2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(bs.buddyStrings2(str1, str2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}

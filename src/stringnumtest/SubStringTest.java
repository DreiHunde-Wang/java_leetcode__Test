package stringnumtest;

/**½Ø¶Ï×Ö·û
 * 
 * @author Dreihunde
 *
 */
public class SubStringTest {
	//method 1 ±éÀú O(n) O(1)
    public String truncateSentence1(String s, int k) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                k--;
            }
            if (k == 0)
                break;

            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //method 2 Ë÷Òý O(n) O(1)
    public String truncateSentence2(String s, int k) {
        int endIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                k--;
            }
            if (k == 0)
                break;

            endIndex = i;
        }
        return s.substring(0, endIndex + 1);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Hello how are you Contestant";
		int k = 4;
		
		SubStringTest ss = new SubStringTest();
		long startTime = System.nanoTime();
		System.out.println(ss.truncateSentence1(s, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(ss.truncateSentence2(s, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}

package stringnumtest;

/**
 * ���ӽ���Сд��ĸ��'a' �� 'z'�������֣�'0' �� '9'�������ַ���'-'���������ţ�'!'��'.' �� ','���Լ��ո�' '����ɡ�
 * ÿ�����ӿ��Ը��ݿո�ֽ�� һ�����߶�� token ����Щ token ֮����һ�����߶���ո� ' ' �ָ���
 * ���һ�� token ͬʱ������������������Ϊ��� token ��һ����Ч���ʣ�
 * ����Сд��ĸ�����ַ���/���㣨�������֣���
 * ����һ�� ���ַ� '-' ��������ڣ����ַ�����Ӧ��������Сд��ĸ��"a-b" ��һ����Ч���ʣ��� "-ab" �� "ab-" ������Ч���ʣ���
 * ����һ�� �����š�������ڣ�������Ӧ��λ�� token �� ĩβ ��
 * �������������Ч���ʵ����ӣ�"a-b."��"afad"��"ba-c"��"a!" �� "!" ��
 * ����һ���ַ��� sentence �������ҳ������� sentence �� ��Ч���ʵ���Ŀ ��
 * https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/
 * @author Dreihunde
 *
 */
public class CountValidWords {
	public int countValidWords(String sentence) {
//		sentence = sentence.trim();
        int start = 0;
        int end = 0;
        int count = 0;
        int sign = 0;
        boolean isDigit = false;
        int connectIdx = -1;
        int n = sentence.length();
        while (end < n) {
            while (end < n && sentence.charAt(end) != ' ') {
                char c = sentence.charAt(end);
                if (c == '-') {
                    if (connectIdx != -1) {
                        isDigit = true;
                    }
                    connectIdx = end - start;
                } else if (c == '!' || c == '.' || c == ',') {
                    sign++;
                } else if (Character.isDigit(c)) {
                    isDigit = true;
                }
                end++;
            }
            if (!isDigit && isVaild(sentence.substring(start, end), sign, connectIdx)) {
                count++;
            }
            while (end < n && sentence.charAt(end) == ' ') {
                end++;
            }
            start = end;
            sign = 0;
            connectIdx = -1;
            isDigit = false;
        }
        return count;
    }

    private boolean isVaild(String token, int sign, int connectIdx) {
    	if (token.length() == 0) {
    		return false;
    	}
        if (sign > 1) {
            return false;
        }
        if (sign == 1 && Character.isLetterOrDigit(token.charAt(token.length() - 1))) {
            return false;
        }
        if (connectIdx == -1) {
            return true;
        } else if (connectIdx == 0 || connectIdx == token.length() - 1) {
            return false;
        } else if (!Character.isLetter(token.charAt(connectIdx - 1)) || !Character.isLetter(token.charAt(connectIdx + 1))) {
            return false;
        } else {
            return true;
        } 
    }
    
    //method 2 ���� O(n) O(n)
    public int countValidWords1(String sentence) {
    	sentence = sentence.trim();
    	int count = 0;
    	String[] strs = sentence.split(" ");
    	for (String str : strs) {
    		if (isValidStr(str)) {
    			count++;
    		}
    	}
    	
    	return count;
    }
    
    private boolean isValidStr(String str) {
    	int n = str.length();
    	boolean isHaveConnect = false;
    	if (n == 0) {
    		return false;
    	}
    	for (int i = 0; i < n; i++) {
    		char c = str.charAt(i);
    		if (c == '.' || c == '!' || c == ',') {
    			if (i != n - 1) {
    				return false;
    			}
    		}
    		if (c == '-') {
    			if (i == 0 || i == n - 1) {
    				return false;
    			}
    			if (!Character.isLetter(str.charAt(i - 1)) || !Character.isLetter(str.charAt(i + 1))) {
    				return false;
    			}
    			if (isHaveConnect) {
    				return false;
    			}
    			isHaveConnect = true;
    		}
    		if (Character.isDigit(c)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    //method 3 �ٷ� O(n) O(1)
    public int countValidWords2(String sentence) {
        int n = sentence.length();
        int l = 0, r = 0;
        int ret = 0;
        while (true) {
            while (l < n && sentence.charAt(l) == ' ') {
                l++;
            }
            if (l >= n) {
                break;
            }
            r = l + 1;
            while (r < n && sentence.charAt(r) != ' ') {
                r++;
            }
            if (isValid(sentence.substring(l, r))) { // �жϸ��ݿո�ֽ������ token �Ƿ���Ч
                ret++;
            }
            l = r + 1;
        }
        return ret;
    }

    public boolean isValid(String word) {
        int n = word.length();
        boolean hasHyphens = false;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(word.charAt(i))) {
                return false;
            } else if (word.charAt(i) == '-') {
                if (hasHyphens == true || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
                    return false;
                }
                hasHyphens = true;
            } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                if (i != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountValidWords cv = new CountValidWords();
		String words = " o6 t";
		System.out.println(cv.countValidWords(words));
		System.out.println(cv.countValidWords1(words));
		System.out.println(cv.countValidWords2(words));

	}

}

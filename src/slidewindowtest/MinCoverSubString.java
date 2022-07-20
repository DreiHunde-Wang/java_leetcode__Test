package src.slidewindowtest;

public class MinCoverSubString {
    //method 1 数组统计+滑动窗口 O(Cn) O(C) C = 52
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return "";
        }
        int[] tMask = getMask(t);
        int[] sMask = new int[52];

        int lt = 0, gt = 0;
        int minLength = n;
        String temp = "";
        while (gt < n) {
            sMask[getIndex(s.charAt(gt))]++;
            if (gt - lt + 1 < m) {
                gt++;
            } else {
                boolean isContains = true;
                int[] diff = new int[52];
                for (int i = 0; i < 52; i++) {
                    if (sMask[i] < tMask[i]) {
                        isContains = false;
                        break;
                    }
                    diff[i] = sMask[i] - tMask[i];
                }
                if (isContains) {
                    char p = s.charAt(lt);
                    while (lt < gt && diff[getIndex(p)] > 0) {
                        sMask[getIndex(s.charAt(lt))]--;
                        diff[getIndex(s.charAt(lt))]--;
                        lt++;
                        p = s.charAt(lt);

                    }
                    if (gt - lt + 1 <= minLength) {
                        temp = s.substring(lt, gt + 1);
                        minLength = gt - lt + 1;
                    }
                    sMask[getIndex(s.charAt(lt))]--;
                    lt++;
                }
                gt++;

            }
        }
        return temp;

    }

    private int getIndex(char p) {
        if (p >= 'a' && p <= 'z') {
            return p - 'a';
        } else {
            return p - 'A' + 26;
        }
    }

    private int[] getMask(String str) {
        if (str == null || str.length() == 0) {
            return new int[0];
        }
        int[] ret = new int[52];
        for (int i = 0; i < str.length(); i++) {
            char p = str.charAt(i);
            int index = getIndex(p);
            ret[index]++;
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "cabwefgewcwaefgcf";
        String t = "cae";

        MinCoverSubString minCoverSubString = new MinCoverSubString();

        System.out.println(minCoverSubString.minWindow(s, t));

    }

}

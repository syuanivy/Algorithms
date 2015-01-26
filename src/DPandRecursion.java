import java.util.List;

/**
 * Created by ivy on 12/22/14.
 */
public class DPandRecursion {
    //steps 1,2 3, find all possible ways of going up n stairs


    // fast and easy longest palindrome, grow from central character
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = centerPalindrome(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
            tmp = centerPalindrome(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }

        return longest;
    }

    //find longest palindrome from a given center character or two, begin = end if only one center character
    public static String centerPalindrome(String s, int begin, int end){
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }
    //longest palindrome, DP time limit exceeded!!!
    public static String longestPalindromeDP(String s) {
        if(s == null || s.length() == 0) return "";
        if(s.length() == 1) return s;
        int l = s.length();
        int[][] T = new int[l][l];
        int max = 0, start = 0, end = 0;

        for(int j = 0; j < l; j++){
            for (int i = 0; i <= j; i++){
                if(i == j) T[i][j] =1;
                if(i+1 == j){
                    if(s.charAt(i) == s.charAt(j)) {
                        T[i][j] = 2;
                    }
                    else T[i][j] = 1;
                }
                if(i+1 < j){
                    if(s.charAt(i) == s.charAt(j)){
                        T[i][j] = T[i+1][j-1]+2;
                    }
                    else{
                        if(T[i][j-1] >= T[i+1][j]) T[i][j] = T[i][j-1];
                        else T[i][j] = T[i+1][j];
                    }
                }
                if(T[i][j] > max){
                    max = T[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end+1);
    }



    // sheet column title, big A 65, small a 97
    //1 to A, 26 to Z, 27 to AA, 28 to AB,
    // 53 % 26 =1, last letter = A,  previous = (53-1) /26
    public static String convertToTitle(int n) {
        if (n < 1) return "";
        if (n >=1 && n<=26) return String.valueOf( (char) (64+n));
        else{
            int last = n % 26;
            if(last == 0) last = 26;
            int previous = (n-last)/26;
            return convertToTitle(previous).concat(convertToTitle(last));
        }
    }

    //The opposite, convert title to number

    public static int titleToNumber(String s) {
        if(s == null || s.length() == 0) return 0;
        int l = s.length();
        int num = 0;
        for(int i = l-1; i >= 0; i--){
            double radix = Math.pow(26,l-1-i);
            int digit = (int) s.charAt(i)-64;
            num += radix * digit;
        }
        return num;
    }

    //n steps, 1 or 2 each time, how many ways to climb
    //Recursion
    public static int climbStairs(int n) {
        if(n == 0 || n == 1) return 1;
        if(n > 1) return climbStairs(n-1)+climbStairs(n-2);
        return 0;
    }
    //DP
    public static int climbStairsDP(int n){
        if(n == 0 || n == 1) return 1;
        int[] ways = new int[n+1];
        ways[0] = 1;
        ways[1] = 1;
        for(int i = 2; i < n+1; i++){
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[n];
    }
}

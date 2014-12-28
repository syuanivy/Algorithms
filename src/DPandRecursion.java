

/**
 * Created by ivy on 12/22/14.
 */
public class DPandRecursion {
    //Excel sheet column title, big A 65, small a 97
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

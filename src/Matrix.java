import java.util.Stack;

public class Matrix {
    //EA coding test
    //DP solution
    public static void findLargestSumPath(int[][] M){
        if(M[0][0] < 0 || M[M.length-1][M.length-1] < 0) {
            System.out.println("Invalid starting or end point!");
            return;
        }
        int[][] sumTable = largestSum(M);
        int largestSum = sumTable[M.length-1][M.length-1];

        if(largestSum == Integer.MIN_VALUE){
            System.out.println("No valid path found!");
            return;
        }
        System.out.println("Largest Sum of Path: " +largestSum);
        printPath(M,sumTable);

    }

    public static int[][] largestSum(int[][] M){
        int n = M.length;
        int[][] S = new int[n][n];
        //initialize S[0][i] and S[j][0];
        S[0][0] = M[0][0] + adjSum(M,0,0);
        for (int i = 1; i< n; i++){
            if(hasPathTo(S,M, i,0)) S[i][0] = S[i-1][0] + M[i][0]+adjSum(M,i,0);
            else S[i][0] = Integer.MIN_VALUE;
        }
        for(int j = 1; j< n; j++){
            if(hasPathTo(S,M,0,j)) S[0][j] = S[0][j-1] + M[0][j]+adjSum(M,0,j);
            else S[0][j] = Integer.MIN_VALUE;
        }
        //fill the table bottom up
        for (int i = 1; i< n; i++){
            for(int j = 1; j< n; j++){
                if(hasPathTo(S,M,i,j)){
                    S[i][j] = Math.max(S[i-1][j], S[i][j-1]) + M[i][j] + adjSum(M,i,j);
                }else{
                    S[i][j] = Integer.MIN_VALUE;
                }
            }
        }
        return S;
    }

    // sum of all adjacent negative values
    public static int adjSum(int[][] M, int i, int j){
        int[] adjs= new int[4];
        if(i-1 >= 0) adjs[0] = M[i-1][j];
        if(i+1 < M.length) adjs[1] = M[i+1][j];
        if(j-1 >=0) adjs[2] = M[i][j-1];
        if(j+1 < M.length) adjs[3] = M[i][j+1];
        int negSum = 0;
        for (int k = 0; k< 4; k++){
            if(adjs[k] < 0){
                negSum += adjs[k];
            }
        }
        return negSum;
    }
    // find whether there is path from start to M[i,j], based on the current S
    public static boolean hasPathTo(int[][] S,int[][] M, int i, int j){
        if(M[i][j] < 0) return false;
        if(i == 0 && S[0][j-1] > Integer.MIN_VALUE ) return true;
        if(j == 0 && S[i-1][0] > Integer.MIN_VALUE ) return true;
        if(S[i-1][j] > Integer.MIN_VALUE || S[i][j-1] > Integer.MIN_VALUE) return true;
        else return false;
    }

    //print out the largest sum path from start to end
    public static void printPath(int[][] M, int[][] S){
        Stack<Integer> reversed = new Stack<Integer>();
        int i = M.length-1;
        int j = M.length-1;
        while(i > 0 || j > 0){
            reversed.push(M[i][j]);
            if(i == 0) j--;
            if(j == 0) i--;
            if(i > 0  && j > 0){
                int above = S[i-1][j];
                int left = S[i][j-1];
                if(above > left) i--;
                else j--;
            }
        }
        System.out.print("The path is: " + M[0][0]);
        while(!reversed.isEmpty()){
            System.out.print(" -> "+ reversed.pop());
        }
        System.out.println();
    }

}

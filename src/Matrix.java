import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayDeque;
import java.util.Stack;

public class Matrix {
    //capture enclosed O
    public static void solve(char[][] board) {
        if(board == null || board.length ==0)
            return;
        int rows = board.length;
        int cols = board[0].length;
        //create a table of booleans recording whether the element has been visited;
        boolean[][] visited = new boolean[rows][cols];

        //check the boundary of O, and go inside if it has O neighbors inside the board;
        for(int i = 0; i< rows; i++){
            if(board[i][0] == 'X' && board[i][cols-1]=='X')
                continue;
            else{
                if(board[i][0] == 'O' && visited[i][0] == false)
                    visitInsideOStack(board, visited, i, 0);
                if(board[i][cols-1]=='O' && visited[i][cols-1] == false)
                    visitInsideOStack(board, visited, i, cols-1);
            }
        }

        for(int j = 1; j< cols-1; j++){
            if(board[0][j] == 'X' && board[rows-1][j]=='X')
                continue;
            else{
                if(board[0][j] == 'O' && visited[0][j] == false )
                    visitInsideOStack(board,visited,  0, j);
                if(board[rows-1][j]=='O' && visited[rows-1][j] == false)
                    visitInsideOStack(board,visited,  rows-1, j);
            }
        }
        //scan the inside of the board, for Os that are marked false, change it to X;

        for (int i= 1; i<rows-1;i++){
            for(int j = 1; j<cols-1; j++){
                if(board[i][j] == 'O' && visited[i][j] == false)
                    board[i][j] = 'X';
            }
        }
    }

    public static void visitInsideO(char[][] board, boolean[][] visited, int i, int j){
        if(i<0 || i>board.length-1 || j<0 || j>board[0].length-1)
            return;
        if(board[i][j] == 'X'|| visited[i][j] == true)
            return;


        if(board[i][j]=='O')
            visited[i][j] = true;

        visitInsideO(board, visited, i+1, j);
        visitInsideO(board, visited, i-1, j);
        visitInsideO(board, visited, i, j+1);
        visitInsideO(board, visited, i, j-1);
    }

    public static void visitInsideOStack(char[][] board, boolean[][] visited, int i, int j){
        Stack<int[]> stack = new Stack<int[]>();
        int[] unvisitedO = new int[2]; unvisitedO[0] = i; unvisitedO[1] = j;
        stack.push(unvisitedO);

        while(!stack.isEmpty()){
            int[] visitedO = stack.pop();
            visited[visitedO[0]][visitedO[1]]= true;
            if(visitedO[0] == 3 && visitedO[1] == 3)
                System.out.println("problem coming");
            if(visitedO[0]-1 >=0 && board[visitedO[0]-1][visitedO[1]] == 'O' && visited[visitedO[0]-1][j] == false) {
                int[]newO = new int[2]; newO[0] = visitedO[0]-1; newO[1] = visitedO[1];
                stack.push(newO);
            }
            if(visitedO[0]+1 <board.length && board[visitedO[0]+1][visitedO[1]] == 'O' && visited[visitedO[0]+1][j] == false) {
                int[]newO = new int[2]; newO[0] = visitedO[0]+1; newO[1] = visitedO[1];
                stack.push(newO);
            }
            if(visitedO[1]+1 <board.length && board[visitedO[0]][visitedO[1]+1] == 'O' && visited[i][visitedO[1]+1] == false) {
                int[]newO = new int[2]; newO[0] = visitedO[0]; newO[1] = visitedO[1]+1;
                stack.push(newO);
            }
            if(visitedO[1]-1>=0 && board[visitedO[0]][visitedO[1]-1] == 'O' && visited[i][visitedO[1]-1] == false) {
                int[]newO = new int[2]; newO[0] = visitedO[0]; newO[1] = visitedO[1]-1;
                stack.push(newO);
            }
        }
    }


    //EA coding test
    //DP solution
    public static void findLargestSumPath(int[][] M){
        if(M == null){
            System.out.println("Invalid matrix input!");
            return;
        }
        if(M[0][0] < 0 || M[M.length-1][M[0].length-1] < 0) {
            System.out.println("Invalid starting or end point!");
            return;
        }
        int[][] sumTable = largestSum(M);
        int largestSum = sumTable[M.length-1][M[0].length-1];

        if(largestSum == Integer.MIN_VALUE){
            System.out.println("No valid path found!");
            return;
        }
        System.out.println("Largest Sum of Path: " +largestSum);
        printPath(M,sumTable);

    }

    public static int[][] largestSum(int[][] M){
        int m = M.length;
        int n = M[0].length;
        int[][] S = new int[m][n];
        //initialize S[0][i] and S[j][0];
        S[0][0] = M[0][0] + adjSum(M,0,0);
        for (int i = 1; i< m; i++){
            if(hasPathTo(S,M, i,0)) S[i][0] = S[i-1][0] + M[i][0]+adjSum(M,i,0);
            else S[i][0] = Integer.MIN_VALUE;
        }
        for(int j = 1; j< n; j++){
            if(hasPathTo(S,M,0,j)) S[0][j] = S[0][j-1] + M[0][j]+adjSum(M,0,j);
            else S[0][j] = Integer.MIN_VALUE;
        }
        //fill the table bottom up
        for (int i = 1; i< m; i++){
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
        if(j+1 < M[0].length) adjs[3] = M[i][j+1];
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
        int j = M[0].length-1;
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

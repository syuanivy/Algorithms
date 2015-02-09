

/**
 * Created by ivy on 12/14/14.
 */



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){

         char[][] board = new char[3][3];
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3;j++){
                    board[i][j] = 'O';
                }
            }
/*        board[0][0] = 'O';
        board[0][3] = 'O';
        board[1][1] = 'O';
        board[1][2] = 'O';
        board[1][4] = 'O';
        board[2][1] = 'O';
        board[2][3] = 'O';
        board[3][0] = 'O';
        board[3][2] = 'O';
        board[3][3] = 'O';
        board[3][4] = 'O';
        board[4][2] = 'O';
        board[4][4] = 'O';*/
        printMatrix(board);

        Matrix.solve(board);
        System.out.println("-------------");
        printMatrix(board);


    }
    //read file name from STDIN
    public static String getFileName(){
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder fileName = new StringBuilder();
        try{
            fileName.append(kb.readLine());
        }catch(IOException e){

        }
        return fileName.toString();
    }

    //read from file
    //i.e a matrix file, one row per line, with tab delimiter
    public static int[][] readMatrixFromFile(String filename){
        File file = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find this file!");
            return null;
        }
        int rows = 0;
        int cols = 0;
        List<String[]> lines = new ArrayList<String[]>();
        while(in.hasNextLine()){
            String[] newLine = in.nextLine().trim().split("\\s+");
            lines.add(newLine);
            rows++;

        }
        in.close();
        cols = lines.get(0).length;
        if(rows == 0 || cols == 0)
            return null;
        int[][] M = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            if(lines.get(i).length != cols)
                return null;
            for(int j = 0; j< cols; j++){
                M[i][j] = Integer.parseInt(lines.get(i)[j]);
            }
        }

        return M;
    }


   // read input from STDIN
    public static void readData(){
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        try{
            String firstline = kb.readLine();
            String secondline = kb.readLine();
            int[] A = parseInput(firstline, secondline);
            for(int i: A){
                System.out.println(i);
            }

        }catch(IOException e){

        }
        return;

    }
    //parse input string if necessary
    public static int[] parseInput(String firstline, String secondline){
        int N = Integer.parseInt(firstline);
        String[] splits = secondline.split("\\s+");

        int[] num = new int[N];
        for (int i = 0; i< N; i++){
            num[i] = Integer.parseInt(splits[i]);
        }
        return num;
    }

    public static void printMatrix(char[][] M){
        for(char[] chars: M){
            for(char c: chars){
                System.out.print(c);System.out.print("  ");
            }
            System.out.println('\n');
        }

    }

}
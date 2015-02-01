

/**
 * Created by ivy on 12/14/14.
 */



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        int[] S = {1,1,1,0};
        System.out.println(AllPossibility.threeSumClosest(S, 100));


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

        }catch(IOException e){

        }
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

}
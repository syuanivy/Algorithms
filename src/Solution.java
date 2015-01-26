

/**
 * Created by ivy on 12/14/14.
 */

import sun.reflect.generics.tree.Tree;

import java.io.*;
public class Solution {
    public static void main(String[] args){
        int[] A = new int[2];
        A[0] = 1;
        A[1] = 0;



        ReviewProbs.sortColor(A);
        ArrayProbs.printArray(A);


    }


    //read from file


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
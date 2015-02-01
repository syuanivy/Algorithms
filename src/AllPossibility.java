import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by ivy on 2/1/15.
 */
public class AllPossibility {
    //3Sum Closest
    //Brutal force DFS, find all threeSum and updating return value, O(n^3) solution
    public static int threeSumClosest(int[] num, int target) {

        Stack<Integer> topSum= new Stack<Integer>();
        topSum.add(target>0? Integer.MIN_VALUE:Integer.MAX_VALUE);
        List<Integer> current = new ArrayList<Integer>();
        threeSumClosestHelper(num,0,target, topSum, current);
        return topSum.peek();
    }
    //DFS
    public static void threeSumClosestHelper(int[] num, int left, int target, Stack<Integer> topSum, List<Integer> current){
        //when condition satisfied--three elements found, update
        if(current.size()==3){
            int temp=0;
            for(int k: current)
                temp+=k;
            if(Math.abs(temp-target) < Math.abs(topSum.peek()-target))
                topSum.push(temp);
            return;
        }
        //when out of index-- return directly
        if(left >num.length-1)
            return;

        //all possibilities
        for(int i=left; i<num.length; i++){ //O(n)
            current.add(num[i]);  // deal with the current element
            threeSumClosestHelper(num,i+1, target, topSum, current); //recurively deal with the next, DFS for each i, O(n^2)
            current.remove(current.size()-1);// remove the current at the end of each possibility
        }
        return;
    }
    //n^2 solution, sort first, scan the array, for each element, scan the elements after it
    //from front and from end, try to make the sum closer and closer to 0
    public static int threeSumClosetIter(int[] num, int target){
        if(num==null || num.length<3)
            return Integer.MIN_VALUE;
        Arrays.sort(num);
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i<num.length; i++){
            int l = i+1;
            int r = num.length-1;
            while(l<r){
                int temp = num[i]+num[l]+num[r];
                if(Math.abs(target-temp) < minDiff){
                    minDiff = Math.abs(target-temp);
                    sum = temp;
                }
                if(target-temp > 0){
                    l++; //increase left, so that the sum increases
                }else if(target-temp < 0){
                    r--; //decrease right, so that the sum decreases
                } else {
                    return sum;
                }
            }
        }
        return sum;
    }


}

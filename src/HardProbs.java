import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ivy on 1/25/15.
 */
public class HardProbs {

    //4Sum Time Limit Exceed Solution
    //find target - num[1], call 3sum, call 2sum, should be n^3 too, why exceed?
    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        //convert the array to a list
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<num.length; i++){
            list.add(num[i]);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        for(int i = 0; i< num.length; i++){
            solution.add(num[i]);
            int sum = target-list.remove(i);
            helper3Sum(list, sum, result, solution);
            list.add(i,solution.remove(0));
        }
        return result;
    }


    public void helper3Sum(List<Integer> list1, int sum, List<List<Integer>> result, List<Integer> solution){
        for(int i=0; i<list1.size();i++){
            int first = list1.remove(i);
            solution.add(first);
            helper2Sum(list1, sum-first, result, solution);
            list1.add(i, solution.remove(1));
        }
    }

    public void helper2Sum(List<Integer> list2, int sumOf2, List<List<Integer>> result, List<Integer> solution){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<list2.size();i++){
            int cur = list2.get(i);
            if(!map.containsKey(cur))
                map.put(sumOf2-cur, i);
            else {
                int prev = list2.get(map.get(cur));
                solution.add(prev);
                solution.add(cur);
                result.add(new ArrayList(solution));
                solution.remove(3);
                solution.remove(2);
            }
        }
    }
}

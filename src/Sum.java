import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ivy on 2/7/15.
 */
public class Sum {
    // Look for two numbers in an array that sum up to a target value, assuming a unique solution exists
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        int i=0;
        while(i<numbers.length){
            if(map.containsKey(numbers[i])){
                result[0] = map.get(numbers[i])+1;
                result[1] = i+1;
                return result;
            }else{
                map.put(target-numbers[i], i);
                i++;
            }
        }

        return result;
    }
    //3Sum, sort first, i scan from 0 through length-2, skip same values
    // l= i+1, r = length-1, while (l<r) look for sum[i]+sum[l]+sum[r] == target value, skip same values from both ends
    public static List<List<Integer>> threeSumSortSolution(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length<3)
            return result;
        // Sort
        Arrays.sort(num);
        for (int i=0; i<num.length-2; i++) {
            int l = i+1;
            int r = num.length-1;
            while(l<r){
                int sum = num[i]+num[l]+num[r];
                if(sum==0){
                    List<Integer> solution = new ArrayList<Integer>();
                    solution.add(num[i]);
                    solution.add(num[l]);
                    solution.add(num[r]);
                    result.add(solution);
                    while(l+1< num.length && num[l+1] == num[l]){
                        l++;
                    }
                    l++; // find the next different element
                    while(r-1>0 && num[r-1] == num[r] ){
                        r--;
                    }
                    r--;//find the previous different element
                }else if(sum < 0){
                    l++;
                }else{
                    r--;
                }
            }
            while(i+1<num.length && num[i+1] == num[i]){ // skipping the same first element
                i++;
            }
        }
        return result;
    }

    //4sum, same idea, double loop for first two elements, skip repeated elements
    //l<r, find all four sums to the target, skip repeated values
    public List<List<Integer>> fourSum(int[] num, int target){
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null) {
            return ret;
        }

        Arrays.sort(num);

        int len = num.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                // skip duplicate.
                continue;
            }

            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && num[j] == num[j - 1]) {
                    // skip duplicate.
                    continue;
                }

                int l = j + 1;
                int r = len - 1;

                while (l < r) {
                    int sum = num[i] + num[j] + num[l] + num[r];
                    if (sum == target) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(num[i]);
                        list.add(num[j]);
                        list.add(num[l]);
                        list.add(num[r]);
                        ret.add(list);
                        do {
                            l++;
                        } while (l < r && num[l] == num[l - 1]);

                        do {
                            r--;
                        } while (l < r && num[r] == num[r + 1]);
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ret;
    }
   //similar to 3sum, just keep an upadated minDiff, if smaller than target, increment l, if greater, decrement r, else = 0, return current
    public int threeSumClosest(int[] num, int target) {

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

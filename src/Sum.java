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

    //two sum, nlgn but in space
    public int[] twoSumSort(int[] numbers, int target){
        Arrays.sort(numbers);
        int[] result = new int[2];
        int l = 0;
        int r = numbers.length-1;
        while(l<r){
            int tempSum = numbers[l] + numbers[r];
            if(tempSum <  target){
                l++;
            }else if(tempSum > target){
                r--;
            }else{
                result[0] = numbers[l];
                result[1] = numbers[r];
                return result;
            }
        }

        return result;
    }

    //Number of pairs of 2Sum or K-complimentary number pairs
    // this solution has nlgn time complexity, but constant space
    public static int pairsOf2Sum(int K, int[] A){
        Arrays.sort(A); //nlgn, numbers sort in ascending order
        int i = 0;
        int j = A.length-1;
        int pairs = 0;
        while(i<=j){
            int front = 1; // number of repeating element from the front
            int end = 1;   // number of repeating element from the end

            if(A[i]+A[j] < K){ // increase first element to achieve K
                i++;
            }else if(A[i] +A[j] > K){ // decrease second element to achieve K
                j--;
            }else{   // when sums to K
                if(A[i] == A[j]){  // reaches the center of the array, values are all equal from i to j
                    int c = j-i+1;  // number of repeating center element
                    pairs+= c + c*(c-1);  // selfpairs + combination of any two (order matters, other wise divided by 2)
                    return pairs; // no further counting needed
                }else{  // A[i] != A[j], can still move further toward each other
                    while(A[i+1] == A[i] && i <= j){
                        front++;  // count repeating front element
                        i++;
                    }
                    i++; //when exit, i is still at the last repeating element, so increment again to the first non-repeating element
                    while(A[j-1] == A[j] && i<= j){
                        end++;
                        j--;  //count repeating end element
                    }
                    j--; //when exit, i is still at the last repeating element, so decrement again to the first non-repeating element
                    pairs+= front*end*2; // order matters( otherwise just front * end)
                }
            }
        }
        return pairs;
    }
    //This solution takes linear space, but also linear time, taking advantage of a hash map
    public static int pairsOf2SumHash(int K,int[] A)
    {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        // first pass
        for(int i=0; i<A.length; i++){
            if( !h.containsKey(A[i]) )
                h.put(A[i], 1);
            else
                h.put(A[i], h.get(A[i])+1);
        }

        // second pass
        int c = 0;
        for(int i=0; i<A.length; i++){
            int comp = K - A[i]; //diff, or complimentary
            if( h.containsKey( comp ))
                c += h.get(comp); // order matters only: (1,3) and (3,1) both count
        }
        return c;
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

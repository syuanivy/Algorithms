import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ivy on 12/16/14.
 */
public class ArrayProbs {
    //gas station, circular cost, n^2 solution
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];
        diff[0]+=gas[0]-=cost[0];
        int start = 0;
        int left = diff[0];
        for(int i = 1; i< gas.length; i++){
            if(left<0){
                start = i;
                left=0;
            }
            diff[i] =gas[i]-cost[i];
            left+=diff[i];
        }
        if(left < 0)
            return-1;
        left = 0;
        for(int i = 0; i< gas.length; i++){
            int k = start+i<gas.length? start+i : (start+i)%gas.length;
            left += diff[k];
            if(left < 0)
                return -1;
        }
        return start;
    }
    //maximal product of subarray
    public static  int maxProduct(int[] A){
        //linear solution
        //idea: maxProduct can be obtained by both max * positive and min * negative, or A[i]
        // scan from the beginning, maintain max/min and result
        //compare max*=A[i], min*=A[i] and A[i], update max and min, and result
        int max = A[0];
        int min = A[0];
        int result = A[0];
        for(int i = 1; i< A.length; i++){
            int temp = max;
            max = Math.max(Math.max(temp*A[i], min*A[i]), A[i]);
            min = Math.min(Math.min(temp*A[i], min*A[i]), A[i]);
            if(max>result)
                result = max;
        }
        return result;
    }
    //majority element more than floor n/2
    public static int majorityElement(int[] num) {
        HashMap counts = new HashMap();
        for (int i = 0; i < num.length; i++){
            if(counts.containsKey(num[i])) counts.put(num[i], (Integer)counts.get(num[i])+1);
            else counts.put(num[i],1);
            if((Integer) counts.get(num[i])> num.length/2) return num[i];
        }
        return 0;
    }

    //remove element
    public static int removeElement(int[] A, int elem) {
        int l = A.length;
        int front=0;
        int end = A.length-1;

        while (front < end ){
            if(A[front] == elem){
                while(end>front && A[end] == elem){
                    A[end] = 0;
                    end--;
                    l--;
                }
                if(end == front){
                    A[front] = 0;
                    l = front;
                    return l;
                }else{
                    A[front] = A[end];
                    A[end] = 0;
                    end--;
                    l--;
                }
            }

            front++;
        }
        if(front >l-1) return l;
        if (A[front] != elem ) return l;
        else{
            A[front] = 0;
            l = l-1;
            return l;
        }
    }

    // merge two sorted array
    public static void merge(int A[], int m, int B[], int n) {
        int l = m+n-1;
        int a = m-1;
        int b = n-1;

        while (l > m-1 && a>=0 && b>=0){
            if(A[a] >= B[b] ) {
                A[l] = A[a];
                a--;
            }else{
                A[l] = B[b];
                b--;
            }
            l--;
        }

        while(l>=0 && a>=0 && b>=0){
            int tempA = A[a];
            int tempB = B[b];
            if(tempA >= tempB) {
                A[l] = tempA;
                a--;
            }else{
                A[l] = tempB;
                b--;
            }
            l--;
        }


        if(b >= 0){
            for (int i = 0; i <= b; i++){
                A[i] = B[i];
            }
        }


    }




    // remove duplicates from sorted array in constant memory
    public static int removeDuplicates(int[] A) {
        int l = A.length;
        int index = 0;
        int check = index+1;

        while(check<A.length){
            int temp = A[check-1];
            while(check<A.length && A[check] == temp){
                check++;
                l--;
            }
            if(check < A.length) {
                A[index+1] = A[check];
                index++;
            }
            check++;
        }

        for(int i= index+1; i<A.length; i++){
            A[i]=0;
        }
        return l;
    }
    // allow twice duplicated elements 111233444 to 11 2 33 44 return new length 7

    public static int removeDuplicatesAllowTwice(int[] A) {
        int l = A.length;
        int index = 0;
        int check = index+1;

        while(check<A.length){
            int temp = A[index];
            int count = 1;
            while(check<A.length && A[check] == temp){
                check++;
                count++;
                if (count > 2) l--;
            }
            if(check < A.length){
                if(count >= 2) {
                    A[index+1] = A[index];
                    A[index+2] = A[check];
                    index+=2;
                }else{
                    A[index+1] = A[check];
                    index +=1;
                }
            }else if(count >= 2){
                    A[index+1] = A[index];
            }
            check++;
        }

        for(int i= l; i<A.length; i++){
            A[i]=0;
        }
        return l;

    }
    public static void printArray(int[] a){
        int l = a.length;
        for(int i = 0; i < l; i++){
            System.out.println(a[i]);
        }
    }
}

import java.util.HashMap;

/**
 * Created by ivy on 12/16/14.
 */
public class ArrayProbs {
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
}

/**
 * Created by ivy on 1/18/15. All my interview questions and tests on HackerRank
 */
public class HackerRank {
    //HackerRank test
    //Find the missing element in an arithmetic progression
    public static int FindMissing(int[] A)
    {
        int cd= A[1] - A[0]; // common diff
        int idx_prev = 1; // previous idx
        int idx_pprev = 0; // previous previous idx
        for(int i=2; i<A.length; i++){
            int diff = A[i] - A[i-1];
            if(diff > cd){
                return A[idx_prev] + cd;
            }else if(diff < cd){
                return A[idx_pprev] + diff;
            }
            else{
                idx_prev++;
                idx_pprev++;
            }
        }
        return Integer.MIN_VALUE;
    }
    //Twitter online coding challenge 1
    // return the count of X, such that for 1<=X<=N, (X-1)! % X = X-1
    // Wilson theorem, if and only if X is prime, thus this problem is equivalent to finding the prime numbers including 1 not exceeding n;
    public static int FactorialRemainder(int n)
    {
        int[] A = new int [n+1];
        for(int i=0; i<=n; i++){
            A[i] = i;
        }
        for(int i=2; i<= Math.sqrt(n); i++){
            if( A[i] > 0 ){
                for(int j=2*i; j<=n; j += i)
                    A[j] = -1;
            }
        }
        int c=0;
        for(int i=1; i<=n; i++){
            if(A[i]>0)
                c++;
        }
        return c;
    }
    //another way using boolean
    public static int factorialRemainderBoolean(int n){

        boolean[] A = new boolean[n+1];
        for(int i =0 ;i< n+1; i++){
            A[i] = true;
        }
        for(int i = 2; i<= Math.sqrt(n); i++){
            if(A[i]) {
                for(int j = 2*i; j<= n; j+=i ){
                    A[j] = false;
                }
            }
        }
        int c = 0;
        for (int i = 1; i<=n; i++){
            if(A[i])
                c++;
        }
        return c;
    }




    //Twitter online coding challenge 2
    //Dodgeball ID fight problem: Given n IDs from 1 to n, find the largest K, such that any K-element subset of n, do not contain two elements that evenly divides each other,
    // or the smallest K', such that at least two elements can evenly divides each other. K' = K+1
    // I just return n/2+1 and it passed the tests
}

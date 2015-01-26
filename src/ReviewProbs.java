/**
 * Created by ivy on 1/18/15.
 */
import java.util.*;

public class ReviewProbs {
    //inplace sort 0 1 2 or color red white blue
    //findFirstNot0 index and findLastNot2 index, scan through from FN0 to LN2 by a current index
    //if current is 0, swap with FN0, update FN0; if current is 2, swap with LN2, update LN2; if current is 1, current++;

    public static int[] sortColor(int[] A){
        int firstNot0 = findFirstNot0(A, 0);
        int lastNot2 = findLastNot2(A,A.length-1);

        int current = firstNot0;
        while(current <= lastNot2){
            if(A[current] == 0){
                int temp = A[current];
                A[current] = A[firstNot0];
                A[firstNot0] = temp;
                firstNot0 = findFirstNot0(A, firstNot0);
            }
            if(A[current] == 2){
                int temp = A[current];
                A[current] = A[lastNot2];
                A[lastNot2] = temp;
                lastNot2 = findLastNot2(A, lastNot2);
            }
            else{
                current++;
            }
        }
        return A;
    }

    public static int findFirstNot0(int[] A, int old){
        int updated =old;
        while(updated<A.length && A[updated] == 0){
            updated++;
        }
        return updated;
    }
    public static int findLastNot2(int[] A, int old){
        int updated =old;
        while(updated>=0 && A[updated] == 2){
            updated--;
        }
        return updated;
    }
    //find A+B+C = D in an array, similar to 4Sum
    public static void findABCSumD(int[] A){
        //convert the array to a list
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i: A){
            list.add(i);
        }
        ArrayList<Integer> solution = new ArrayList<Integer>();
        for(int i = 0; i< A.length; i++){
            int sum = list.remove(i);
            helper3Sum(list, sum, solution);
            if(solution.size() == 3){
                solution.add(sum);
                break;
            }else{
                list.add(i,sum);
            }
        }

        for(int i = 0; i< solution.size(); i++){
            System.out.println(solution.get(i));
        }
    }

    public static void helper3Sum(List<Integer> list1, int sum, List<Integer> solution){
        for(int i=0; i<list1.size();i++){
            int first = list1.remove(i);
            solution.add(first);
            helper2Sum(list1, sum-first, solution);
            if(solution.size()== 3)
                return;
            else
                list1.add(i, solution.remove(0));
        }
    }

    public static void helper2Sum(List<Integer> list2, int sumOf2, List<Integer> solution){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<list2.size();i++){
            int cur = list2.get(i);
            if(!map.containsKey(cur))
                map.put(sumOf2-cur, i);
            else {
                int prev = list2.get(map.get(cur));
                solution.add(prev);
                solution.add(cur);
            }
            if(solution.size() == 3)
                return;
        }
    }

    //Lowest common ancestor of BST
    public static TreeNode lowestCABST(TreeNode root, TreeNode a, TreeNode b){
        if(!inBST(root, a) || !inBST(root, b)) // 2* log n
            return null;
        // root, a, b cannot be null, and a b are both in the tree
        TreeNode p = null;
        return lowestCABSTHelper(root, a, b, p);
    }
    public static TreeNode lowestCABSTHelper(TreeNode root, TreeNode a, TreeNode b, TreeNode p){
        if(a == root || b == root)
            return p;
        if(a.val <= root.val && b.val<= root.val)
            return lowestCABSTHelper(root.left, a, b, root);
        if(a.val > root.val && b.val > root.val)
            return lowestCABSTHelper(root.right, a, b, root);
        else
            return root;
    }
    public static boolean inBST(TreeNode root, TreeNode n){
        if(root == null || n == null) return false;
        if(n == root) return true;
        if(n.val<=root.val) return inBST(root.left, n);
        else return inBST(root.right, n);
    }


    //Common Ancestor of binary tree, return parent of root if one of a b is the root
    public static TreeNode lowestCA(TreeNode root, TreeNode a, TreeNode b){
        TreeNode p = null;
        return lowestCAhelper(root, a, b, p);
    }

    public static TreeNode lowestCAhelper(TreeNode root, TreeNode a, TreeNode b, TreeNode p){
        //base case
        if(root==null)
            return null;

        boolean ain = find(root, a);
        boolean bin = find(root, b);
        if(ain && bin){
            boolean ainLeft= find(root.left, a);
            boolean binLeft = find(root.left, b);
            boolean ainRight= find(root.right, a);
            boolean binRight = find(root.right, b);
            if(ainLeft && binLeft){
                return lowestCAhelper(root.left, a, b, root);
            }
            if(ainRight && binRight){
                return lowestCAhelper(root.right, a, b, root);
            }
            else{
                if(a==root || b == root){
                    return p;

                }else{
                    return root;
                }
            }
        }else{
            return null;
        }
    }
    //find a node in a binary tree
    public static boolean find(TreeNode root, TreeNode n){
        if(root == null) return false;
        if(root == n) return true;
        else return find(root.left, n) || find(root.right, n);
    }


    //2SUM
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
    //Letter combination of phone number
    public List<String> letterCombinations(String digits) {
        HashMap<Integer, List<String>> map = buildMap();
        return comb(digits,map);

    }
    public List<String> comb(String digits, HashMap<Integer,List<String>> map) {
        List<String> result = new LinkedList<String>();
        if(digits.length() == 0) {
            result.add(new String(""));
            return result;
        }
        if(digits.length() == 1) {
            for(String c:map.get(Integer.parseInt(digits)))
                result.add(c);
            return result;
        } else {
            for(String s : comb(digits.substring(1),map)) {
                for(String c:map.get(Integer.parseInt(digits.substring(0,1))))
                    result.add(c + s);
            }
        }
        return result;
    }
    public HashMap<Integer, List<String>> buildMap(){
        HashMap<Integer, List<String>> map = new HashMap<Integer,List<String>>();
        map.put(2,new ArrayList<String>(Arrays.asList("a","b","c")));
        map.put(3,new ArrayList<String>(Arrays.asList("d","e","f")));
        map.put(4,new ArrayList<String>(Arrays.asList("g","h","i")));
        map.put(5,new ArrayList<String>(Arrays.asList("j","k","l")));
        map.put(6,new ArrayList<String>(Arrays.asList("m","n","o")));
        map.put(7,new ArrayList<String>(Arrays.asList("p","q","r","s")));
        map.put(8,new ArrayList<String>(Arrays.asList("t","u","v")));
        map.put(9,new ArrayList<String>(Arrays.asList("w","x","y","z")));

        return map;
    }
    //simplify path
    public static String simplifyPath(String path) {
        String[] arr = path.split("/");
        ArrayList<String> filtered =  new ArrayList<String>();
        for(String s : arr){
            if(s.equals(".") || s.equals(""))
                continue;
            if(s.equals("..") ){
                if(filtered.size()-1 >=0)
                    filtered.remove(filtered.size()-1);
                else
                    continue;
            }
            else
                filtered.add(s);
        }

        if(filtered.size()<1)
            return "/";

        StringBuilder sb = new StringBuilder();
        for(String s: filtered){

            sb.append("/"+s);
        }
        return sb.toString();
    }
    //Anagram
    public static List<List<String>> anagrams(String[] A){
        String[] S = new String[A.length];
        for(int i = 0; i< A.length; i++){
            S[i] = A[i];
        }
        for(int i = 0 ; i< S.length; i++){
            S[i] = sortString(S[i]);
        }
        HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for(int i=0; i<S.length; i++){
            if(!map.containsKey(S[i])){
                ArrayList<Integer> indices= new ArrayList<Integer>();
                indices.add(i);
                map.put(S[i], indices);
            }else{
                map.get(S[i]).add(i);
            }
        }

        List<List<String>> result = new ArrayList<List<String>>();
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, List<Integer>> entry = (Map.Entry<String, List<Integer>>)it.next();
            List<Integer> indices = entry.getValue();
            List<String> group = new ArrayList<String>();
            for(int i: indices){
                group.add(A[i]);
            }
            result.add(group);
        }
        return result;
    }
    //sort String
    public static String sortString(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }

    //jump game
    public boolean canJump(int[] A) {
        int max = 0;
        for(int i=0;i<A.length;i++){
            if(i>max) {return false;}
            max = Math.max(A[i]+i,max);
        }
        return true;

    }

    //stock, multiple in order transaction
    public static int maxProfitMulti(int[] prices) {
        //find all monotonically increasing interval, calculate the diff, sum it up
        ArrayList<Integer> diff = new ArrayList<Integer>();
        int minI = 0;
        int maxI = 0;
        int prev = 0;
        int cur = 1;
        while(cur < prices.length){
            while(cur< prices.length && prices[prev]>prices[cur]){
                prev++;
                cur++;
            }
            if(cur == prices.length) break;
            minI = prev;
            while(cur< prices.length && prices[prev]<=prices[cur]){
                prev++;
                cur++;
            }

            maxI = prev;

            diff.add(prices[maxI]-prices[minI]);
        }

        int sum = 0;
        for(int i : diff){
            sum+= i;
        }
        return sum;
    }
    //stock, single transaction, find the greatest diff between A[i] and min(A[0] to A[i])
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        // a list of min indices as min being updated
        Queue<Integer> minIndex= new LinkedList<Integer>();
        minIndex.add(0);
        int prevMin = prices[0];
        for(int i = 0; i<prices.length; i++){
            if(prices[i] < prevMin){
                minIndex.add(i);
                prevMin = prices[i];
            }
        }
        //calculate the max diff of selling at each day
        int[] diff = new int[prices.length];
        int currentMin = minIndex.remove(); // position in the linkedlist minIndex
        int i=0;
        while( i< prices.length){
            while(!minIndex.isEmpty() && i<minIndex.peek() && i< prices.length){
                diff[i] = prices[i]-prices[currentMin];
                i++;
            }
            if(i==prices.length) break;

            if(!minIndex.isEmpty()){
                currentMin = minIndex.remove();
            }

            diff[i] = prices[i]-prices[currentMin];
            i++;
        }

        int maxdiff = 0;
        for(int j =0; j< prices.length; j++){
            if(diff[j] > maxdiff)
                maxdiff = diff[j];
        }
        return maxdiff;
    }
    //longest palindrome
    //grow from center solution, can also use DP, but same order of magnitude
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = centerPalindrome(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
            tmp = centerPalindrome(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }

        return longest;
    }

    //find longest palindrome from a given center character or two, begin = end if only one center character
    public String centerPalindrome(String s, int begin, int end){
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    //longest common prefix
    public String longestCommonPrefix(String[] strs) {
        int l = strs.length;
        if(l == 0) return "";
        if (l == 1 ) return strs[0];

        if (strs[0].equals("")) return "";
        String head = strs[0].substring(0,1);
        String[] strs1 = new String[l];
        for(int i = 0; i < l; i++){
            if(!strs[i].startsWith(head) || strs[i].equals("")) return "";
            strs1[i] = strs[i].substring(1, strs[i].length());
        }
        return head+= longestCommonPrefix(strs1);
    }

    //longest substring with no repeating characters
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0 ) return 0;
        int l = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int longest = 0;
        int begin = 0;
        int end = 0;
        int current = 0;
        while (end < l){
            if(!map.containsKey(s.charAt(end)) || map.get(s.charAt(end)) < begin){// if not contained, or, contained before begin
                map.put(s.charAt(end), end);// put or update the last position of the end character
            }else if(map.get(s.charAt(end)) >= begin){// if contained after begin
                begin =  map.get(s.charAt(end))+1; //update begin
                map.put(s.charAt(end), end);// update last position of the end character
            }
            end++;
            current = end - begin;
            if(current > longest) {
                longest = current;
            }
        }
        return longest;
    }




    //Write a function that will compute the number of valid interpretations of the input.A=1 B=2.C=3 L=12...W=23 Z=26
    //f("123") = 3 // {"ABC", "LC", "AW"}
    // recursion, divide as first character and the rest, plus first two characters and the rest, code tested
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        return decodeHelper(s, memo);
    }

    public int decodeHelper(String s, int[] m){
        if(s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        if(m[s.length()-1] > 0)
            return m[s.length()-1];

        if(s.length() == 1){
            m[s.length()-1] = decodeLen1(s);
            return m[s.length()-1];
        }

        if(s.length() == 2){
            m[s.length()-1] = decodeLen2(s);
            return m[s.length()-1];
        }
        int sub1 = 0;
        int sub2 = 0;

        sub1 = decodeHelper(s.substring(1),m);
        if(Integer.parseInt(s.substring(0,2))<=26)
            sub2 = decodeHelper(s.substring(2),m);
        m[s.length()-1] = sub1+sub2;
        return m[s.length()-1];

    }
    public int decodeLen1(String s){
        if(s.charAt(0) == '0')
            return 0;
        else
            return 1;
    }

    public int decodeLen2(String s){
        if(s.charAt(0) == '0')
            return 0;
        if(Integer.parseInt(s) <=26){
            if(s.charAt(1) == '0')
                return 1;
            else
                return 2;
        }else{
            if(s.charAt(1) == '0')
                return 0;
            else
                return 1;
        }
    }




    //Roman number to Integer
    public int romanToInt(String s) {
        if(s.equals("")) return 0;
        char[] chars = s.toCharArray();
        int temp = romanChar(chars[0]);
        int i = 0, j = 1;
        while (i < s.length() && j < s.length()){
            if(romanChar(chars[i]) >= romanChar(chars[j])){
                temp += romanChar(chars[j]);

            }else{
                temp -= romanChar(chars[i]);
                temp += romanChar(chars[j]);
                temp -= romanChar(chars[i]);

            }
            i++;
            j++;
        }
        return temp;
    }
    //convert single roman character to integer
    public int romanChar(char c){
        int value = 0;
        switch (c) {
            case 'I': value = 1; break;
            case 'V': value = 5; break;
            case 'X': value = 10; break;
            case 'L': value = 50; break;
            case 'C': value = 100; break;
            case 'D': value = 500; break;
            case 'M': value = 1000;
        }
        return value;
    }

    //traverse a tree in reverse order
    public static void reverseTrav(TreeNode root){
        if(root == null)
            return;
        if(root.right != null)
            reverseTrav(root.right);
        System.out.println(root.val);
        if(root.left != null)
            reverseTrav(root.left);
        return;
    }

    //print the nth node of a tree
    public static void printN(TreeNode root, int N){
        if(root == null)
            return;
        int nLeft = numOfNodes(root.left);
        if(nLeft+1 == N)
            System.out.println(root.val);
        if(nLeft+1 > N)
            printN(root.left, N);
        else
            printN(root.right, N-nLeft-1);
        return;
    }
    //number of nodes of a tree rooted at root
    public static int numOfNodes(TreeNode root){
        if(root == null)
            return 0;
        return numOfNodes(root.left)+1+numOfNodes(root.right);
    }

    //isPalindrome. considering only alphanumeric, case insensitive 26+10 characters. Case sensitive 52+10 characters.
    //recursive solution,
    //linear space
    public static boolean isPalindrome(String s) {
        //obtain the alphanumeric
        char[] chars = s.toCharArray();
        ArrayList<Character> alphanumerics = new ArrayList<Character>();
        for(char c : chars){
            if((isValidChar(c))){
                alphanumerics.add(c);
            }
        }
        int i = 0;
        int j = alphanumerics.size()-1;
        while (i<j){
            if((alphanumerics.get(i) != alphanumerics.get(j)) && Math.abs(alphanumerics.get(i)-alphanumerics.get(j)) != 32)return false;
            i++;
            j--;
        }
        return true;
    }
    //inplace solution,
    public static boolean isPalindromeInPlace(String s){
        int l = s.length();
        int i = 0;
        int j = l-1;
        while(i<j){
            while(i<l && !isValidChar(s.charAt(i))){ // i<l and j>=0 to protect s.charAt(i) inbound
                i++;
            }
            while(j>=0 && !isValidChar(s.charAt(j))){
                j--;
            }

            if(i<j){
                if(s.charAt(i) != s.charAt(j) &&
                        Math.abs(s.charAt(i)-s.charAt(j))!=32)
                    return false;
                else
                    i++;
                j--;
            }
        }

        return true;

    }
//alphanumeric characters are integers in certain range

    public static boolean isValidChar(char c){
        if((c>=48 && c<=57) ||
                (c>=65 && c<=90) ||
                (c>=97 && c<=122))
            return true;
        else
            return false;
    }



    //Find the first bug version, given a good version number and a bad version number, boolean isBad(int version) is available
    //Binary search solution
    public static int firstBug(int low, int high){
        if(low >= high)
            return high;
        int mid = (low+high)/2;
        if(isBad(mid) && isBad(mid-1))
            return firstBug(low, mid-1);
        if(!isBad(mid))
            return firstBug(mid+1, high);
        else
            return mid;
    }
    public static boolean isBad(int Version){
        return true;
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

    //Longest substring without repeating characters
    public static String longestNonRepeat(String s){
        return helperLongestNonRepeat(s, 0, s.length()-1);
    }

    public static String helperLongestNonRepeat(String s, int low, int high){
        if(low > high)
            return "";
        String sub = helperLongestNonRepeat(s, low, high-1);
        if(sub.contains(s.substring(high,high+1))){
            return sub;
        }else{
            return sub+s.substring(high, high+1);
        }


    }

    public static String addBinary(String a, String b) {
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        int l1 = arrayA.length;
        int l2 = arrayB.length;
        int s;
        int l;

        if(l1 <= l2) {
            s = l1;
            l = l2;
        }
        else{
            s = l2;
            l=  l1;
        }

        int[] newArray = new int[l];

        int temp = 0;
        for (int i = 0; i < s; i++ ){
            int sum = temp +
                    Integer.parseInt(String.valueOf(arrayA[l1-1-i])) +
                    Integer.parseInt(String.valueOf(arrayB[l2-1-i]));
            if (sum > 1) {
                sum = sum-2;
                temp = 1;
            }
            else {
                temp =0;
            }
            newArray[l-1-i] = sum;
        }

        if (l1 > l2){
            for (int i = s; i < l; i++){
                int sum = temp +
                        Integer.parseInt(String.valueOf(arrayA[l1-1-i]));
                if (sum >1) {
                    sum = sum-2;
                    temp = 1;
                }
                else {
                    temp =0;
                }
                newArray[l-1-i] = sum;
            }
        }else{
            for (int i = s; i < l; i++){
                int sum = temp +
                        Integer.parseInt(String.valueOf(arrayB[l2-1-i]));
                if (sum > 1) {
                    sum = sum-2;
                    temp = 1;
                }
                else {
                    temp =0;
                }
                newArray[l-1-i] = sum;
            }
        }

        String newStr;
        StringBuffer newBuff = new StringBuffer();

        if(temp ==1) {
            int[] newArrayPlus = new int[l+1];
            newArrayPlus[0]  = 1;
            newBuff.append(String.valueOf(1));
            for (int i= 1; i < l+1; i++){
                newArrayPlus[i] = newArray[i-1];
                newBuff.append(String.valueOf(newArrayPlus[i]));
            }
            newStr = newBuff.toString();

        }else{
            for (int i= 0; i < l; i++){
                newBuff.append(String.valueOf(newArray[i]));
            }
            newStr = newBuff.toString();
        }

        return newStr;
    }
    public static List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<Integer> current = new LinkedList<Integer>();
        subsets(result, S, current, 0);
        return result;
    }

    private static void subsets(List<List<Integer>> result, int[] S, LinkedList<Integer> current, int idx) {
        if (idx == S.length) {
            result.add(new LinkedList<Integer>(current));
            return;
        }
        current.addLast(S[idx]);
        subsets(result, S, current, idx+1);
        current.removeLast();
        subsets(result, S, current, idx+1);
    }

    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        List<Integer> candidates = new ArrayList<Integer>();
        for(int i = 0; i< num.length; i++){
            candidates.add(num[i]);
        }
        perHelper(candidates, result, current);
        return result;
    }

    public void perHelper(List<Integer> candidates, List<List<Integer>> result, List<Integer> current){
        if(candidates.size()==0){
            result.add(new ArrayList<Integer>(current));
            return;
        }
        //num[idx] + perHelper(num[ other than idx]) idx 0- num.length
        int sz = candidates.size();
        for(int i = 0; i<sz; i++){
            int v = candidates.remove(i);
            current.add(v);
            perHelper(candidates, result, current);
            current.remove(current.size()-1);
            candidates.add(i,v);
        }
        return;
    }
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        helper3Sum(num, list, solution);
        return list;
    }

    public void helper3Sum(int[] num, List<List<Integer>> list, List<Integer> solution){
        int l = num.length;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < l-1; i++){
            solution.add(num[i]);
            helper2Sum(num, i, list, solution, map);
            solution.remove(0);
            map.clear();
        }
        return;
    }

    public void helper2Sum(int[] num, int i, List<List<Integer>> list,
                           List<Integer> solution, HashMap<Integer,Integer> map){
        int low = i+1;
        int high = num.length-1;
        int sum = 0-num[i];
        for (int j = low; j <= high; j++){
            if(!map.containsKey(num[j])){
                map.put(sum-num[j], j);
            }else{
                solution.add(num[map.get(num[j])]);
                solution.add(num[j]);
                list.add(new ArrayList(solution));
                solution.remove(2);
                solution.remove(1);
            }
        }
        return;
    }
}

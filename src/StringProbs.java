import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by ivy on 12/18/14.
 */
public class StringProbs {


    //DP, one dimensional O(n) solution.
    //All possible substrings n+(n-1)+(n-2)+...+1 = O(n^2), checking by scanning all substrings leads to O(n^3) solution
    //Below, one pass DP solution use hashmap, or array if string is simply ASCII (128, or 256 if extended)
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0 ) return 0;
        int l = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int longest = 0;
        int begin = 0;
        int end = 0;
        int current = 0;
        while (end < l){
            if(!map.containsKey(s.charAt(end)) || map.get(s.charAt(end)) < begin){
                map.put(s.charAt(end), end);
            }else if(map.get(s.charAt(end)) >= begin){
                begin =  map.get(s.charAt(end))+1;
                map.put(s.charAt(end), end);
            }
            end++;
            current = end - begin;
            if(current > longest) {
                longest = current;
            }
        }

        return longest;
    }



    //Palindrome Partitioning
/*    Given a string s, partition s such that every substring of the partition is a palindrome.
    Return all possible palindrome partitioning of s.
    For example, given s = "aab",
    Return [  ["aa","b"], ["a","a","b"]   ]
 */
    public static List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<List<String>>();
        List<String> partition = new ArrayList<String>();
        if(s == null || s.length() == 0) return null;
        findPartitions(partitions, partition, s);
        return partitions;
    }
    // helper method find palindrome partitions
    public static void findPartitions(List<List<String>> partitions,
                                             List<String> partition,
                                             String s){
        if(s.length() == 0) {
            ArrayList<String> fullPartition = new ArrayList<String>();
            for(String e:partition){
                fullPartition.add(e);
            }
            partitions.add(fullPartition);
        }

        for(int i = 1; i <= s.length(); i++){
            String str = s.substring(0, i);
            String rest = s.substring(i);
            if(isPalindr(str)){
                partition.add(str);
                findPartitions(partitions, partition, rest);
                partition.remove(partition.size()-1);
            }
        }
    }
    // helper method, given a string, whether it is palindrome.
    public static boolean isPalindr(String s){
        if(s.length() == 0 || s.length() == 1 ) return true;
        if(s.charAt(0) != (s.charAt(s.length()-1))) return false;
        else return isPalindr(s.substring(1,s.length()-1));
    }




    //count and say
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String seq = "1";
        int it = 1;
        while(it<n) {
            StringBuffer newSeq = new StringBuffer();
            char last = seq.charAt(0);
            int count =0;
            for(int i =0; i< seq.length();i++) {
                if(seq.charAt(i) == last) {
                    count ++;
                    continue;
                }else{
                    newSeq.append(count).append(last);
                    last = seq.charAt(i);
                    count =1;
                }
            }
            newSeq.append(count).append(last);
            seq = newSeq.toString();
            it++;
        }
        return seq;
    }
    //zigzag
/* num of rows nrows, characters in the vertical line: nrows, characters in the between nrows-2
    q     a
    d   d a
    d d   a
    d     a
    thus the first row is the 0st, 2*nrows-2th, 4*nrows-4, 6*nrows-6... starting from i=0, 2i * (nrows-1)th in the original array
    the second row is 1st // 2*nrows-3 th, 2*nrows-1th// 4*nrows-5th... 2i *(nrows-1)+1//2i *(nrows-1)+1
    the second last row is the nrows-2th // nrowsth, 3*nrows-4th // 3*nrows-2th, ... (2i+1)(nrows-1)-1 // (2i+1)(nrows-1)+1
    and the last row is the nrows-1th, 3*nrows-3, 5*nrows-5... (2i+1)(nrows-1)th in the original array

*/
    //zigzag converter
    public static String zigzag(String s, int nRows){
        if(nRows <= 1) return s;

        StringBuffer result = new StringBuffer();
        if(s.length() ==0) return result.toString();

        for(int i =0; i< nRows; i++){
            for(int j =0, index =i; index < s.length(); j++, index = (2*nRows-2)*j +i) { // index is the position in the original string
                result.append(String.valueOf(s.charAt(index)));  //red element
                if(i ==0 || i == nRows-1) { // first or last element in the column
                    continue;
                }
                if(index+(nRows- i- 1)*2 < s.length()) { // green element in between
                    result.append(String.valueOf(s.charAt(index+(nRows- i-1)*2)));
                }
            }
        }
        return result.toString();
    }
    //Longest prefix of an array of strings
    public static  String longestCommonPrefix(String[] strs) {
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

    //considering only alphanumeric, case insensitive 26+10 characters. Case sensitive 52+10 characters.
    public static boolean isPalindrome(String s) {
        //obtain the alphanumeric
        char[] chars = s.toCharArray();
        ArrayList<Character> alphanumerics = new ArrayList<Character>();
        for(char c : chars){
            if((c>=48 && c<=57)||
                    (c>=65 && c<=90)||
                    (c>=97 && c<=122)){
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

    public static boolean isPalindromeRecur(String s) {
        if (s == "") return true;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length-1;
        char head;
        char tail;

        while(i<=j &&
                !((chars[i]>=48 && chars[i]<=57)||
                        (chars[i]>=65 && chars[i]<=90)||
                        (chars[i]>=97 && chars[i]<=122))){
            i++;
        }
        if(i<=j) head = chars[i];
        else return false;

        while(i<=j &&
                !((chars[j]>=48 && chars[j]<=57)||
                        (chars[j]>=65 && chars[j]<=90)||
                        (chars[j]>=97 && chars[j]<=122))){
            j--;
        }
        if(i<=j) tail = chars[j];
        else return false;

        if(head != tail) return false;
        if(i == j || i == j-1 ) return true;
        else return isPalindromeRecur(s.substring(i+1,j));

    }

    //substring(startIndex, endIndex+1)
    public static int strStr(String haystack, String needle) {
        int lh = haystack.length();
        int ln = needle.length();
        if (ln > lh) return -1; // needle longer than haystack
        if(haystack.substring(0,ln).equals(needle)) return 0; // haystack starts with needle
        int index = strStr(haystack.substring(1,lh), needle)+1;
        if(index == 0) return -1;
        else return index;
    }

    public static boolean isValid(String s) {
        if (s == null) return true;
        Stack<Character> stack = new Stack<Character>();
        char[] charArray =  s.toCharArray();
        for (char c:charArray){
            if( !stack.isEmpty() && ((stack.peek() == '(' && c ==')') ||
                    (stack.peek() == '[' && c ==']')||
                    (stack.peek() == '{' && c =='}'))){
                stack.pop();
            }else{
                stack.push(c);
            }
        }

        if (stack.isEmpty()) return true;
        else return false;
    }
// generate all possible combinations of valid parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if(n < 1) return list;
        if(n == 1){
            list.add("()");
            return list;
        }
        StringBuffer growingString = new StringBuffer();
        int left = 0;
        int right = 0;
        List<String> l = getParenthesisStrings(list, n, growingString, left, right );
        return l;
    }
//helper method, comparable to DFS-visit
    public static List<String> getParenthesisStrings(List<String> list, int n, StringBuffer s, int left, int right){
        if (left > n || right > n) return list; // error
        if (left == n && right == n) {
            list.add(s.toString());
        }

        if(left < n) {
            s.append("(");
            getParenthesisStrings(list, n, s, left+1, right);
            s.deleteCharAt(s.length()-1);
        }
        if(right < left){
            s.append(")");
            getParenthesisStrings(list, n , s, left, right+1 );
            s.deleteCharAt(s.length()-1);
        }

        return list;
    }

    public static int lengthOfLastWord(String s) {
        String[] words = s.split("\\s+");
        int arrl = words.length;
        if(arrl == 0) return 0;
        int l = words[arrl-1].length();
        return l;
    }
    //split around ".": \\.   split around white spaces \\s+
    public static int compareVersion(String version1, String version2) {
        String[] str1;
        String[] str2;
        if (version1.contains(".")) str1 = version1.split("\\.");
        else {
            str1 = new String[1];
            str1[0] = version1;
        }
        if (version2.contains(".")) str2 = version2.split("\\.");
        else {
            str2 = new String[1];
            str2[0] = version2;
        }


        int s1 = str1.length;
        int s2 = str2.length;
        int versions;
        if (s1 < s2) versions = s2;
        else versions = s1;

        int[] val1 = new int[versions];
        int[] val2 = new int[versions];

        for (int i = 0; i< versions; i++){
            if(i < s1) val1[i] = Integer.parseInt(str1[i]);
            else val1[i] = 0;
            if(i < s2) val2[i] = Integer.parseInt(str2[i]);
            else val2[i] = 0;
        }
        int i = 0;
        while (i< versions){
            if (val1[i] < val2[i]) return -1;
            if (val1[i] > val2[i]) return 1;
            else i++;
        }
        return 0;
    }

}

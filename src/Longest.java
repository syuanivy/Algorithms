import java.util.HashMap;

/**
 * Created by ivy on 2/7/15.
 */
public class Longest {
    //longest palindrome grow from center solution, scan the string, grow from i, i and i, i+1, updating longest solution
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


    //longestCommonPrefix, recursive solution
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
    //substring without repeating characters, use hashmap save the last appearance index of a character, as growing the longest substring
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
}

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ivy on 12/19/14.
 */
public class IntegerProbs {
    // factorial trailing zeros, the number of fives, twenty fives...n / 5 + n / 25 + n / 125 + n / 625 + n / 3125...;
    public static int trailingZeroes(int n) {
        int c = 0;
        while (n >= 5) {
            n /= 5;
            c += n;
        }
        return c;
    }

    //valid sudoku
    public static boolean isValidSudoku(char[][] board){
        Map<Character, Boolean> row = new HashMap<Character, Boolean>();
        Map<Character, Boolean> col = new HashMap<Character, Boolean>();
        Map<Character, Boolean> block = new HashMap<Character, Boolean>();

        for (int i=0;i<9;i++){
            col.clear();
            row.clear();
            for (int j=0;j<9;j++){
                if (board[i][j] != '.'){
                    if (col.containsKey(board[i][j])){
                        return false;
                    }else{
                        col.put(board[i][j], true);
                    }
                }
                if (board[j][i] != '.'){
                    if (row.containsKey(board[j][i])){
                        return false;
                    }else{
                        row.put(board[j][i],true);
                    }
                }
            }
        }

        for (int ii=0;ii<9;ii=ii+3){
            for (int jj=0;jj<9;jj=jj+3){
                block.clear();
                for (int i=ii;i<ii+3;i++){
                    for (int j=jj;j<jj+3;j++){
                        if (board[i][j]!= '.') {
                            if (block.containsKey(board[i][j])){
                                return false;
                            }else{
                                block.put(board[i][j], true);
                            }
                        }
                    }
                }
            }
        }

        return true;


    }


    //covert Roman to Integer,
    public static int romanToInt(String s) {
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
    public static int romanChar(char c){
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

    //int digits = (int) Math.log10(x)+1;, negative values are not palindrome
    public static boolean isPalindrome(int x) {
        if (x == 0) return true;
        Long y = new Long(x);
        if (y < 0 ) return false;
        int digits = (int) Math.log10(y)+1;

        int i = digits-1, j = 0;
        while (i>j){
            int head = (int)(y/Math.pow(10,i) % 10);
            int tail = (int)(y/Math.pow(10,j)%10);
            if(head != tail) return false;
            i--;
            j++;
        }

       return true;
    }

    //convert string to integer, any messy input, return 0 if not starting with numeric characters, return max or min if overflow
    public static int atoi(String str) {
        String[] sarray = str.split("\\s+");
        String str1;
        int i = 0;
        while(i< sarray.length && sarray[i].equals("")){ //string "", == does not work
            i++;
        }

        if(i == sarray.length) return 0;
        else  str1 =  sarray[i];

        String numerics;
        int pos = 1;
        if(str1.startsWith("+")){
            numerics = str1.substring(1);
        }else if(str1.startsWith("-")){
            pos = -pos;
            numerics = str1.substring(1);
        }
        else numerics = str1;
        if(numerics.length() == 0 ) return 0;

        char[] chars = numerics.toCharArray();
        Long temp = new Long(0);

        for(char c:chars){
            if(c>=48 && c<=57){
                temp = 10* temp + Integer.parseInt(String.valueOf(c));
                if(temp*pos > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if(temp*pos < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            }else{
                return temp.intValue() * pos;
            }
        }

        return temp.intValue() * pos;

    }

    //REAL CLEVER!
    public static int reverseClever(int x){
        int newN =0, left =0;
        Long temp = new Long(0) ;
        while(x != 0)
        {
            left = x%10;
            temp = temp*10 + left;
            if(Math.abs(temp) > Integer.MAX_VALUE) return 0;
            x = x/10;
        }
        newN = temp.intValue();
        return newN;
    }
    //In java, the minimal way of converting a char[], chars, to string, String s = new String(chars);
    //Wrong answer! cannot deal with INTEGER OVERFLOW!!!!
    public static int reverse(int x) {
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        char[] newchars = new char[chars.length];
        for(int i = 0; i < chars.length; i++){
            newchars[i] = chars[chars.length-1-i];
        }
        String revAll = new String(newchars);
        String reversed;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        if (revAll.contains("-")) {
            reversed = revAll.substring(0,revAll.length()-1);
            Long newInt = new Long(reversed);
            return newInt.intValue()*(-1);

        }

        else return (new Long(revAll)).intValue();

    }

    public static String countAndSay(int n) {
        return null;

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

    public static int[] plusOne(int[] digits) {
        int l = digits.length;
        int temp = 1;
        for (int i = l-1; i >=0; i--){
            digits[i]+= temp;
            if(digits[i] == 10){
                digits[i] = 0;
                temp = 1;
            }
            else{
                temp=0;
                return digits;
            }
        }
        int[] newDigits = new int[l+1];
        newDigits[0] =1;
        for (int i = 1; i < l+1; i++){
            newDigits[i] = digits[i-1];
        }
        return newDigits;
    }




}

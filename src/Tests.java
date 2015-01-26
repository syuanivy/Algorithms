/**
 * Created by ivy on 1/18/15.
 */
public class Tests {
    //test trailing zeros of n factorial
    //    System.out.println(IntegerProbs.trailingZeroes(30));
/*
        String s = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
            System.out.println(s.length());
            System.out.println(StringProbs.lengthOfLongestSubstring(s));
*/



   /*    int[] a = new int[7];
        a[0] = 1;
        a[1] = 1;
        a[2] = 1;
        a[3] = 1;
        a[4] = 2;
        a[5] = 2;
            a[6] = 3;

        ArrayProbs.printArray(a);
        System.out.println("New length: "+ ArrayProbs.removeDuplicatesAllowTwice(a));
        ArrayProbs.printArray(a);
*/
/*
        //insertion sort linked list
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(1);
*/
/*        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(4);*//*

        ListNode.printList(l1);
        ListNode.printList(ListNode.partition(l1,2));

*/




        /*//sort linkedlist in constant space and nlgn, mergesort
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
      //  l1.next.next = new ListNode(1);
        ListNode.sortList(l1);
*/

/*
        //Test longest palindrome
        String s = "abcba";
        System.out.println(DPandRecursion.longestPalindromeDP(s));
*/


/*
        //Test Palindrome partition, DFS recursion method
        String s = "aabaa";
        StringProbs.partition(s);
*/

/*        //Test valid sudoku
        char a = '.';
        System.out.println(a);
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                board[i][j] = '.';
            }
        }
        board[0][0] = '3';
        board[0][3] = '3';
        System.out.println(board[0][0]);
        System.out.println(IntegerProbs.isValidSudoku(board));*/


/*
        //Test count and say
        System.out.println(StringProbs.countAndSay(5));
*/

/*
        //Test zigzag
        String s = "PAYPALISHIRING";
        System.out.println(StringProbs.zigzag(s, 3));
*/


/*      //Test longest prefix
        String[] strs = new String[2];
        strs[0] = "def";
        strs[1] = "abcd";
        System.out.println(StringProbs.longestCommonPrefix(strs));*/

/*
        //Test roman to int
        System.out.println(IntegerProbs.romanToInt("MMMDCCCCXCIX"));
*/

/*
        //Test integer is panlindrome
        System.out.println(IntegerProbs.isPalindrome(-2147447412));
*/

/*        //Test atoi, string to integer

        System.out.println(IntegerProbs.atoi("    010"));*/



/*        //Test reverse integer
        System.out.println(IntegerProbs.reverseClever(1534236469));
        */

/*
        //Test valid palindrom
        String s = "2a2";
        System.out.println(StringProbs.isPalindromeRecur(s));
*/


/*        //Test majority element
        int[] array = new int[1];
        array[0] = 1;
*//*        array[1] = 2;
        array[2] = 2;
        array[3] = 2;*//*
        System.out.println(ArrayProbs.majorityElement(array));*/

/*
        //Test lengthOfLast
        System.out.println(StringProbs.lengthOfLastWord("   "));
*/

 /*       //Test Excel Column Title converstion
        System.out.println(DPandRecursion.convertToTitle(53));
        System.out.println(DPandRecursion.titleToNumber("AA"));
*/

/*
        //Test climbStairs
        System.out.println(DPandRecursion.climbStairsDP(50));
*/

/*
        //Test find need in haystack
        String haystack = "stack";
        String needle = "sdack";
        System.out.println(StringProbs.strStr(haystack, needle));
*/

/*
        //Test addBinary
        String a = "111";
        String b = "111";
        System.out.println(IntegerProbs.addBinary(a,b));

*/

/*
       //Test pascal I
        TreeNode.getRow(2);

*/


        /*//Test valid parentheses
        String s = "[(])";
        System.out.println(StringProbs.isValid(s));
        */
/*
        //Test compareVersion

        String string1 = "1.0";
        String string2 = "1.1";

    //    String[] array = string1.split("\\.");
        System.out.println(StringProbs.compareVersion(string1,string2));
*/


/*
         //Test removeNthFromEnd
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            System.out.println(ListNode.removeNthFromEnd(head,1).val);

*/
/*
        //Test addTwoNumbers
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(8);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(3);
        l2.next  = new ListNode(4);
        l2.next.next = new ListNode(1);
        ListNode.addTwoNumbers(l1,l2);

*/

/*
         //Test merge
            int[] A = new int[10];
            int[] B = new int[3];
            A[0] = -1;
            A[1] = 0;
            A[2] = 0;
            A[3] = 3;
            A[4] = 3;
            A[5] = 3;
            B[0] = 1;
            B[1] = 2;
            B[2] = 2;
            ArrayProbs.merge(A, 6, B, 3);
            System.out.println(A[0]+" "+A[1]+" "+A[2]+" "+A[3]+" "+A[4]+" "+A[5]+" " +A[6]+" "+A[7]+" "+A[8]+" "+A[9]);

*/



/*
        //Test removeDuplicates from sorted array
        int[] array = new int[2];
        array[0] = 1;
        array[1] = 1;
       // array[2] = 1;
        System.out.println(ArrayProbs.removeDuplicates(array));

*/




/*
        //Test LinkedList Intersection
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(3);
        headA.next.next = new ListNode(5);

        ListNode headB = new ListNode(10);
        headB.next = new ListNode(11);
        headB.next.next = new ListNode(13);
        headB.next.next.next = new ListNode(15);

        headA.next.next.next = headB;
        System.out.println(ListNode.getIntersectionNode(headA,headB).val);
*/

/*
        //Test isSymmetric
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        //root.left.left = new TreeNode(4);
        //root.left.right = new TreeNode(3);
        //root.right = new TreeNode(2);
        //root.right.left = new TreeNode(3);
       // root.right.right = new TreeNode(4);
        System.out.println(TreeNode.isSymmetricIter(root));
*/



/*        //Test isBalanced

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        System.out.println(TreeNode.isBalanced(root));*/
/*
        //Test BSTIterator

        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(4);
        root.right = new TreeNode(2);
            TreeNode N = null;
        BSTIterator i = new BSTIterator(N);
        System.out.println(i.hasNext());
        System.out.println(i.next());
        System.out.println(i.hasNext());
        System.out.println(i.next());
*/


/*
        //Test MinStack
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
*/

/*      //Test levelOrderBottom
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        List result = TreeNode.levelOrderBottom(root);
*/
/*       /

            //Test generate Parenthesis

      //      StringProbs.generateParenthesis(2);

// Test buildTree from inorder postorder
   /*         int[] inorder = new int[4];
            inorder[0] = 1; inorder[1] =2; inorder[2] =3; inorder[3] =4;
            int[] preorder = new int[4];
            preorder[0] = 4; preorder[1] = 1; preorder[2] =2; preorder[3] =3;
            TreeNode.buildTreePreInOrder(preorder, inorder);

*/

/*
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(1);
            root.right = new TreeNode(1);
//            root.left.left =  new TreeNode(2);
    //        root.left.right = new TreeNode(1);
  //          root.left.right.left = new TreeNode(1);
      //      root.right.left =  new TreeNode(1);
        //    root.right.right = new TreeNode(1);
            //Stack<TreeNode> s = new Stack<TreeNode>();
            //TreeNode n = root.left.right;
             TreeNode.pathSum(root,2);*/
    //Test clone graph

/*        UndirectedGraphNode node = new UndirectedGraphNode(0);

        node.neighbors.add(node);
        node.neighbors.add(node);
        UndirectedGraphNode clone = UndirectedGraphNode.cloneGraph(node);*/

/*        //Test ea coding test largestSum
         //Test case 1, 1x1
        int[][] M1 = new int[1][1];
        M1[0][0] = 2;
*//*
        Largest Sum of Path: 2
        The path is: 2
*//*

        //Test case 2, 2x2
        int[][] M2 = new int[2][2];
        M2[0][0] = 2;
        M2[0][1] = -3;
        M2[1][0] = 0;
        M2[1][1] = 1;

*//*
        Largest Sum of Path: 3
        The path is: 2 -> 0 -> 1
*//*

        //Test case 3, invalid start/end point
        int[][] M3 = new int[2][2];
        M3[0][0] = -2;
        M3[0][1] = -3;
        M3[1][0] = 0;
        M3[1][1] = -1;
   //     Invalid starting or end point!

        //Test case 4, no valid path
        int[][] M4 = new int[2][2];
        M4[0][0] = 2;
        M4[0][1] = -3;
        M4[1][0] = -2;
        M4[1][1] = 1;

     //


        Matrix.findLargestSumPath(M2);*/
}

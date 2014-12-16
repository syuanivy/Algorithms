/**
 * Created by ivy on 12/14/14.
 */

import sun.reflect.generics.tree.Tree;

import java.util.*;


public class Solution {

    public static void main(String[] args){
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
    }

}
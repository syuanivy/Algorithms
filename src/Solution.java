/**
 * Created by ivy on 12/14/14.
 */

import sun.reflect.generics.tree.Tree;

import java.util.*;


public class Solution {

    public static void main(String[] args){
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


/*
push(-2147483648),top,getMin,pop,getMin
*/


/*//Test levelOrderBottom
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        List result = TreeNode.levelOrderBottom(root);
*/
    }

}
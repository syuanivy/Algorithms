/**
 * Created by ivy on 12/14/14.
 */

import sun.reflect.generics.tree.Tree;

import java.util.*;


public class Solution {

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        List result = TreeNode.levelOrderBottom(root);
    }

}
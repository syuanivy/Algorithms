import java.util.*;

/**
 * Created by ivy on 12/14/14.
 */
//Definition for binary tree
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }

     //Binary Tree Level Order Traversal II
     public static List<List<Integer>> levelOrderBottom(TreeNode root) {
          Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>(); // save lists of integers in reversed level order
          List<List<Integer>> list = new ArrayList<List<Integer>>();// the list to return in the desired order
          if(root == null) return list;

          //BFS
          Queue<TreeNode> queue = new LinkedList<TreeNode>();
          queue.add(root);
          while (!queue.isEmpty()){

               //save all nodes in the parent level in a temp list, and enqueue all values in an ArrayList
               List<TreeNode> currentLevelNode = new ArrayList<TreeNode>();
               ArrayList<Integer> currentLevelVal = new ArrayList<Integer>();

               while(!queue.isEmpty()){
                    TreeNode current = queue.remove();
                    if (current != null){
                         currentLevelNode.add(current);
                         currentLevelVal.add(current.val);
                    }
               }
               stack.add(currentLevelVal);

               // enqueue the children level nodes in the queue
               for(TreeNode current:currentLevelNode){
                    if(current.left != null) {
                         queue.add(current.left);
                    }
                    if(current.right != null) {
                         queue.add(current.right);
                    }
               }
          }

          //Reverse the list


          while(!stack.isEmpty()){
               list.add(stack.pop());
          }
          return list;
     }
}
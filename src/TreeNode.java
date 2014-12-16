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



     //Same Tree
     public static boolean isSameTree(TreeNode p, TreeNode q){
          if(p == null && q == null) return true;
          if((p == null && q != null) || (p != null && q == null)) return false;
          if(p.val == q.val){
               if(isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) return true;
               else return false;
          }
          return false;
     }


     //Symmetric Tree
     public static boolean isSymmetricIter(TreeNode root) {
          if(root == null) return true;
          TreeNode left = root.left;
          TreeNode right = root.right;
          if(left == null && right == null) return true;
          if((left != null && right == null) || (left == null && right != null)) return false;

          Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
          currentLevel.add(left);
          currentLevel.add(right);

          while (!currentLevel.isEmpty()) {
               left = currentLevel.remove();
               right = currentLevel.remove();

               if (left.val != right.val) return false;

               if ((left.left == null && right.right != null) || (left.left != null && right.right == null))
                    return false;
               if ((left.right == null && right.left != null) || (left.right != null && right.left == null))
                    return false;

               if (left.left != null && right.right != null) {
                    currentLevel.add(left.left);
                    currentLevel.add(right.right);
               }
               if (left.right != null && right.left != null) {
                    currentLevel.add(left.right);
                    currentLevel.add(right.left);
               }
          }
          return true;
     }
     //Balanced Binary Tree
     public static boolean isBalanced(TreeNode root){
          if(root == null) return true;

          if(isBalanced(root.left) && isBalanced(root.right)){

               int d1 = maxDepth(root.left);
               int d2 = maxDepth(root.right);
               if(Math.abs(d1 - d2) <= 1)return true;
               else return false;
          }
          return false;
     }
     //maxDepth
     public static int maxDepth(TreeNode root){
          int d = 0;
          if (root == null) return d;

          TreeNode current = root;
          int dLeft = maxDepth(root.left);
          int dRight = maxDepth(root.right);
          if(dLeft >= dRight) d = dLeft + 1;
          else d = dRight + 1;
          return d;
     }

     //minDepth
     public int minDepth(TreeNode root) {
          int d = 0;
          if(root == null) return d;

          Queue<TreeNode> queue = new LinkedList<TreeNode>();
          queue.add(root);

          while(!queue.isEmpty()) {
               List<TreeNode> currentLevel = new LinkedList<TreeNode>();

               while (!queue.isEmpty()) {
                    currentLevel.add(queue.remove());
               }
               d++;

               for (TreeNode n : currentLevel) {
                    if (n.left == null && n.right == null) return d;
                    if (n.left != null) queue.add(n.left);
                    if (n.right != null) queue.add(n.right);
               }
          }
          return d;
     }

     //Binary Tree Level Order Traversal II, levelOrder does not require the stack to reverse the list
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
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
     //preorder traversal, resursive solution is trivial?
     public static List<Integer> preorderTraversalRec(TreeNode root) {
          List<Integer> list =  new ArrayList<Integer>();
          return preorder(list, root);
     }
     public static List<Integer> preorder(List<Integer> l, TreeNode root){
          if(root == null) return l;
          l.add(root.val);
          if(root.left != null) preorder(l, root.left);
          if(root.right != null) preorder(l, root.right);
          return l;
     }
     //preorder traversal, iterative solution
     public static List<Integer> preorderTraversal(TreeNode root) {
          List<Integer> list =  new ArrayList<Integer>();
          if(root == null) return list;

          Stack<TreeNode> stack =  new Stack<TreeNode>();
          stack.add(root);
          list.add(root.val);
          TreeNode current = root.left;

          while(!stack.isEmpty() || current != null){
               while(current != null){
                    stack.add(current);
                    list.add(current.val);
                    current = current.left;
               }
               current =  stack.pop().right;

          }
          return list;
     }
     //inorder traversal recursive solution is trivial
     public static List<Integer> inorderTraversalRec(TreeNode root) {
          List<Integer> list = new ArrayList<Integer>();
          if(root == null) return list;
          else{
               inorderHelper(list, root);
               return list;
          }
     }
     public static void inorderHelper(List<Integer> list, TreeNode root){
          if(root.left != null) inorderHelper(list, root.left);
          list.add(root.val);
          if(root.right != null) inorderHelper(list, root.right);
     }
     //inorder traversal iterative solution
     public List<Integer> inorderTraversal(TreeNode root) {
          List<Integer> list = new ArrayList<Integer>();
          if(root == null) return list;
          Stack<TreeNode> stack = new Stack<TreeNode>();
          stack.add(root);
          TreeNode current = root.left;
          while(!stack.isEmpty() || current != null) {
               while (current != null) {
                    stack.add(current);
                    current = current.left;
               }
               TreeNode temp = stack.pop();
               list.add(temp.val);
               if(temp.right != null) current = temp.right;
          }
          return list;

     }


     //pascal triangle ii, Given an index k, return the kth row of the Pascal's triangle.
     public static List<Integer> getRow(int rowIndex) {
          List<Integer> row = new ArrayList<Integer>();
          if(rowIndex < 0) return row;
          if(rowIndex == 0){
               row.add(1);
               return row;
          }else{
               List<Integer> previous = getRow(rowIndex-1);
               row.add(1);
               for(int i = 1 ; i< rowIndex; i++){
                    row.add(previous.get(i - 1) + previous.get(i));
               }
               row.add(1);
               return row;
          }
     }

     //pascal triangle i
     public static List<List<Integer>> generate(int numRows) {
          List<List<Integer>> list = new ArrayList<List<Integer>>();
          if(numRows < 1) return list;
          if(numRows == 1) {
               List<Integer> top = new ArrayList<Integer>();
               top.add(1);
               list.add(top);
          }else{
               list = generate(numRows-1);
               List<Integer> bottom = list.get(numRows-2);
               List<Integer> newBottom = new ArrayList<Integer>();

               newBottom.add(1);
               for(int i = 1 ; i< numRows-1; i++){
                    newBottom.add(bottom.get(i-1)+bottom.get(i));
               }
               newBottom.add(1);
               list.add(newBottom);
          }
          return list;

     }


     //path sum from root to leaf
     public static boolean hasPathSum(TreeNode root, int sum) {
          if(root == null) return false;
          if(root. val == sum && root.left == null && root.right == null) return true;
          if(hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val)) return true;
          return false;
     }

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
     public TreeNode sortedListToBST(ListNode head) {
          if(head == null) return null;
          if (head.next == null) return new TreeNode(head.val);

          int l = lengthOfList(head);
          ListNode current = head;
          for(int i=1; i< l/2; i++){
               current = current.next;
          }
          ListNode rootNode = current.next;
          current.next = null;
          ListNode head2 = rootNode.next;
          rootNode.next = null;

          TreeNode root = new TreeNode(rootNode.val);
          TreeNode leftsub = sortedListToBST(head);
          TreeNode rightsub = sortedListToBST(head2);
          root.left = leftsub;
          root.right = rightsub;
          return root;
     }

     public int lengthOfList(ListNode head){
          int l = 0;
          if(head == null) return l;

          ListNode current = head;
          while(current != null){
               l++;
               current = current.next;
          }
          return l;
     }
}
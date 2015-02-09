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

/*     //all nodes % 3 = 1
     public static void mod3remain1(TreeNode root, List<Pair> result, int c){
          if(root == null)
               return;
          mod3remain1(root.left, result, c);
          if(root.val % 3 == 1){
               Pair ok = new Pair();
               ok.val = root.val;
               ok.index = c;
               result.add(Pair);
          }
          c++;
          mod3remain1(root.right, result, c);

          return;
     }*/
// all possible solutions
     //helper function struction: void helper(input, size of the problem, List, growing solution)
     // base case,
     // if (input == null) return;
     // growing solution.add(current element);
     //if(satisfied condition) list.add(copy of growing solution), return if no further searching, else continue
     // helper (subproblem 1);
     //helper (subproblem 2);
     //... helper(all possible subproblems);
     // growing solution. remove (current element ); back trace
     //return
     public static List<List<Integer>> pathSum(TreeNode root, int sum) {
          List<List<Integer>> list = new ArrayList<List<Integer>>();
          if (root == null) return list;

          List<Integer> path = new ArrayList<Integer> ();

          pathSumHelper(root, sum, list, path);

          return list;
     }



     public static void pathSumHelper(TreeNode root, int sum,

                                      List<List<Integer>> list,  List<Integer> path){
          if(root == null) return;
          path.add(root.val);
          if(root.val == sum && root.left == null && root.right == null){
               List<Integer> copy = saveCopy(path);
               list.add(copy);
          }
          pathSumHelper(root.left, sum-root.val, list, path);
          pathSumHelper(root.right, sum-root.val, list, path);
          path.remove(path.size()-1);
          return;
     }

     public static List<Integer> saveCopy(List<Integer> original){
          List<Integer> copy = new ArrayList<Integer>();
          for(int i: original){
               copy.add(i);
          }
          return copy;
     }
     //find the path from root to node n in a binary tree, not a BST
     public static boolean findPath(TreeNode root, TreeNode n, Stack<TreeNode> s){
          if(root == null) return false;
          s.push(root);
          if(root == n) return true;
          boolean left = findPath(root.left, n, s);
          if(left) return left;
          boolean right = findPath(root.right, n,s);
          if(right) return right;
          s.pop();
          return false;
     }



// number of all valid BST trees consist of 1-n
     public int numTrees(int n) {
          HashMap<Range, Integer> map = new HashMap<Range,Integer>();

          return numTreesHelper(1, n, map);
     }


     public int numTreesHelper(int low, int high, HashMap<Range, Integer> map){
          Range range = new Range(low, high);
          if(map.containsKey(range)) return map.get(range);
          if (low > high) return 0;
          if(low == high) return 1;

          int sum = 0;
          for(int i = low; i<=high; i++ ){
               int leftNum = numTreesHelper(low, i-1, map);
               int rightNum = numTreesHelper(i+1, high, map);
               if(leftNum == 0) sum+= rightNum;
               if(rightNum == 0) sum+= leftNum;
               else sum+= leftNum*rightNum;
          }

          map.put(range, sum);
          return sum;
     }


     class Range{
          int low;
          int high;
          Range(int i, int j){
               this.low = i;
               this.high = j;
          }
     }


     //valid bst
     public static boolean isValidBST(TreeNode root) {
          if(root == null) return true;
          Wrapper wRoot = new Wrapper(root);
          return isValidBST(wRoot).valid;


     }
     public static Wrapper isValidBST(Wrapper wRoot){
          //base case
          if(wRoot.root.left == null && wRoot.root.right == null) {
               wRoot.valid = true;
               return wRoot;
          }
          //recursion

          if(wRoot.root.left != null && wRoot.root.right != null){
               Wrapper wLeft = isValidBST(new Wrapper(wRoot.root.left));
               Wrapper wRight = isValidBST(new Wrapper(wRoot.root.right));
               if(wLeft.valid && wRight.valid && wLeft.max < wRoot.root.val && wRight.min > wRoot.root.val){
                    wRoot.valid = true;
                    wRoot.min = wLeft.min;
                    wRoot.max = wRight.max;
               }else {
                    wRoot.valid = false;
               }
          }else if(wRoot.root.left != null && wRoot.root.right == null){
               Wrapper wLeft = isValidBST(new Wrapper(wRoot.root.left));
               if(wLeft.valid &&  wLeft.max < wRoot.root.val){
                    wRoot.valid = true;
                    wRoot.min = wLeft.min;
               }else {
                    wRoot.valid = false;
               }
          }else{
               Wrapper wRight = isValidBST(new Wrapper(wRoot.root.right));
               if(wRight.valid &&  wRight.min > wRoot.root.val){
                    wRoot.valid = true;
                    wRoot.max= wRight.max;
               }else {
                    wRoot.valid = false;
               }
          }


          return wRoot;

     }

     public static class Wrapper{
          TreeNode root;
          boolean valid;
          int min;
          int max;

          Wrapper (TreeNode root){
               this.root = root;
               valid = false;
               min = root.val;
               max = root.val;
          }
     }
// flatten BT to a right leaning linkedlist
     public static  void flatten(TreeNode root) {
          if(root == null) return;
          if(root.left == null && root.right == null) return;

          TreeNode left = root.left;
          TreeNode right = root.right;

          root.left = null;
          flatten(left);
          root.right = left;
          flatten(right);
          TreeNode n = root;
          while(n.right!=null){
               n = n.right;
          }
          n.right = right;
          return;
     }
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

     public static TreeNode sortedArrayToBST(int[] num){

          return buildBSTHelper(num, 0, num.length-1);
     }

     public static TreeNode buildBSTHelper(int[] num, int low, int high){
          int l = high-low+1;
          //base case
          if(l<1) return null;
          if(l==1) return new TreeNode(num[low]);
          //recursive case

          TreeNode root = new TreeNode(num[low+l/2]);
          TreeNode left = buildBSTHelper(num, low, low+l/2-1);
          TreeNode right = buildBSTHelper(num, low+l/2+1, high);
          root.left = left;
          root.right = right;
          return root;
     }

     //build BT from inorder and postorder
     public static TreeNode buildTree(int[] inorder, int[] postorder) {
          if(inorder.length == 0 || postorder.length == 0) return null;
          return buildTreeHelper(inorder, postorder,0, inorder.length-1, 0, postorder.length-1);
     }

     public static TreeNode buildTreeHelper(int[] inorder, int[] postorder, int ibegin, int iend, int pbegin, int pend){
          if(ibegin > iend || pbegin > pend) return null;
          if(ibegin == iend) return new TreeNode(inorder[ibegin]);
          TreeNode root = new TreeNode(postorder[pend]);
          int i = 0;
          while(inorder[ibegin+i] != root.val){
               i++;
          }
          if (i > 0) root.left = buildTreeHelper(inorder, postorder, ibegin, ibegin+i-1,pbegin, pbegin+i-1);
          if(ibegin+i+1 < inorder.length) root.right = buildTreeHelper(inorder, postorder, ibegin+i+1, iend, pbegin+i, pend-1);

          return root;

     }

     public static TreeNode buildTreePreInOrder(int[] preorder, int[] inorder) {
          if(preorder.length == 0 || inorder.length == 0) return null;
          else return buildTreeHelperPreInOrder(preorder, inorder, 0, inorder.length-1, 0, inorder.length - 1);
     }

     public static TreeNode buildTreeHelperPreInOrder(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2) {
          if (p1 > p2 || i1 > i2) return null;
          if(p1 == p2 && i1 == i2) return new TreeNode(preorder[p1]);

          TreeNode root = new TreeNode(preorder[p1]);

          int i = 0;
          while(inorder[i1+i] != root.val){
               i++;
          }

          if(i>0) root.left = buildTreeHelperPreInOrder(preorder, inorder, p1+1, p1+i, i1, i1+i-1);
          if(p1+i+1 < preorder.length)root.right = buildTreeHelperPreInOrder(preorder, inorder, p1+i+1, p2, i1+i+1, i2);

          return root;

     }

     public static void printTreeLevelOrder(TreeNode root){
          Queue<TreeNode> q = new LinkedList<TreeNode>();
          q.add(root);
          while(!q.isEmpty()){
               TreeNode cur = q.remove();
               printNode(cur);
               if(cur != null && (cur.left != null || cur.right != null)){
                    q.add(cur.left);
                    q.add(cur.right);
               }
          }
          System.out.println();
     }
     public static void printNode(TreeNode n){
          if(n == null)
               System.out.print("#");
          else
               System.out.print(n.val);
     }

}
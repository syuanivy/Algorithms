import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by ivy on 2/1/15.
 */
public class AllPossibility {
    //Unique BST from 1 to n
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(n<1){
            result.add(null);
            return result;
        }
        List<TreeNode> solution = new ArrayList<TreeNode>();
        TreeNode zero = new TreeNode(0);
        helperGenerateTrees(result, solution, 1, n, zero, n);
        return result;
    }

    public static void helperGenerateTrees(List<TreeNode> result, List<TreeNode> solution, int low, int high, TreeNode root, int n){
        if(solution.size()== n) {
            TreeNode complete= copyTree(solution.get(0));
            result.add(complete);
            return;
        }

        if(low>high)
            return;


        for(int i = low; i<=high; i++){
            TreeNode childroot = new TreeNode(i);
            connectChild(root,childroot);
            solution.add(childroot);
            helperGenerateTrees(result,solution,low,i-1,childroot, n);
            if(solution.size()<n){
                helperGenerateTrees(result, solution,i+1,high, childroot,n);
            }
            solution.remove(solution.size()-1);
        }
        return;
    }

    public static void connectChild(TreeNode root, TreeNode child){
        if(child.val<=root.val)
            root.left = child;
        else
            root.right = child;
    }

    public static TreeNode copyTree(TreeNode root){
        if(root == null)
            return null;
        TreeNode newRoot =  new TreeNode(root.val);
        newRoot.left = copyTree(root.left);
        newRoot.right = copyTree(root.right);
        return newRoot;
    }
    public List<TreeNode> generateTreesRec(int n) {
        return generateTreesHelp(1, n);
    }
    public List<TreeNode> generateTreesHelp(int start, int end) {
        ArrayList<TreeNode> ret = new ArrayList<TreeNode>();


        if (start > end) {
            ret.add(null);
            return ret;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> left = generateTreesHelp(start, i - 1);
            List<TreeNode> right =  generateTreesHelp(i + 1, end);


            for (TreeNode l: left) {
                for(TreeNode r: right) {

                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;


                    ret.add(root);
                }
            }
        }

        return ret;
    }
    //3Sum Closest
    //Brutal force DFS, find all threeSum and updating return value, O(n^3) solution
    public static int threeSumClosest(int[] num, int target) {

        Stack<Integer> topSum= new Stack<Integer>();
        topSum.add(target>0? Integer.MIN_VALUE:Integer.MAX_VALUE);
        List<Integer> current = new ArrayList<Integer>();
        threeSumClosestHelper(num,0,target, topSum, current);
        return topSum.peek();
    }
    //DFS
    public static void threeSumClosestHelper(int[] num, int left, int target, Stack<Integer> topSum, List<Integer> current){
        //when condition satisfied--three elements found, update
        if(current.size()==3){
            int temp=0;
            for(int k: current)
                temp+=k;
            if(Math.abs(temp-target) < Math.abs(topSum.peek()-target))
                topSum.push(temp);
            return;
        }
        //when out of index-- return directly
        if(left >num.length-1)
            return;

        //all possibilities
        for(int i=left; i<num.length; i++){ //O(n)
            current.add(num[i]);  // deal with the current element
            threeSumClosestHelper(num,i+1, target, topSum, current); //recurively deal with the next, DFS for each i, O(n^2)
            current.remove(current.size()-1);// remove the current at the end of each possibility
        }
        return;
    }
    //n^2 solution, sort first, scan the array, for each element, scan the elements after it
    //from front and from end, try to make the sum closer and closer to 0
    public static int threeSumClosetIter(int[] num, int target){
        if(num==null || num.length<3)
            return Integer.MIN_VALUE;
        Arrays.sort(num);
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i<num.length; i++){
            int l = i+1;
            int r = num.length-1;
            while(l<r){
                int temp = num[i]+num[l]+num[r];
                if(Math.abs(target-temp) < minDiff){
                    minDiff = Math.abs(target-temp);
                    sum = temp;
                }
                if(target-temp > 0){
                    l++; //increase left, so that the sum increases
                }else if(target-temp < 0){
                    r--; //decrease right, so that the sum decreases
                } else {
                    return sum;
                }
            }
        }
        return sum;
    }


}

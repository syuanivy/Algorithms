import java.sql.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by ivy on 1/4/15.
 */
public class BSTIterator {
    ArrayList<Integer> array;
    int index;




    public BSTIterator(TreeNode root) {
        array = new ArrayList<Integer>();
        array = inOrderTraverse(root, array);
        if (array == null) index = -1;
        else index = 0;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(index >= 0 && index < array.size()) return true; // cannot change order, index >= 0  is to make sure
                                                          // that array is not null
        else return false;
    }

    /** @return the next smallest number */
    public int next() {
        if(hasNext()){
            int temp = array.get(index);
            index++;
            return temp;
        }
        else return Integer.MIN_VALUE;

    }


    /**@ return all values in order and store in an array*/
    public ArrayList<Integer> inOrderTraverse(TreeNode root, ArrayList<Integer> array){
        if(root == null) return null;

        if(root.left != null) inOrderTraverse(root.left, array);
        array.add(root.val);
        if(root.right != null) inOrderTraverse(root.right,array);

        return array;

    }


}

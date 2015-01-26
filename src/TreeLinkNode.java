import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ivy on 1/8/15.
 */
public class TreeLinkNode {

          int val;
          TreeLinkNode left, right, next;
          TreeLinkNode(int x) { val = x; }

    public class Solution {
        public void connect(TreeLinkNode root) {
            if (root == null) return;

            Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
            queue.add(root);

            while(!queue.isEmpty()){

                Queue<TreeLinkNode> nextLevel = new LinkedList<TreeLinkNode>();
                while(!queue.isEmpty()){
                    TreeLinkNode current =  queue.remove();
                    if(!queue.isEmpty()) current.next = queue.peek();
                    if(current.left != null) nextLevel.add(current.left);
                    if(current.right != null) nextLevel.add(current.right);
                }

                while(!nextLevel.isEmpty()){
                    queue.add(nextLevel.remove());
                }

            }




        }
    }
}

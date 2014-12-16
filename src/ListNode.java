import java.util.List;

/**
 * Created by ivy on 12/15/14.
 */

 public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        int sizeA = 0;
        int sizeB = 0;
        ListNode a = headA;
        while(a != null){
            a = a.next;
            sizeA++;
        }
        ListNode b = headB;
        while(b != null){
            b = b.next;
            sizeB++;
        }

        ListNode currentA = headA;
        ListNode currentB = headB;

        if(sizeA >= sizeB){
            int d = sizeA - sizeB;
            while (d > 0){
                currentA = currentA.next;
                d--;
            }
        }else{
            int d = sizeB-sizeA;
            while (d>0){
                currentB = currentB.next;
                d--;
            }
        }

        while(currentA != currentB){
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return currentA;
    }
 }




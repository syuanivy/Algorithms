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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if(l1 == null && l2 != null) return l2;
        if(l1 != null && l2 == null) return l1;
        ListNode head;
        if(l1.val <= l2.val) head = l1;
        else head = l2;

        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode splice;
        while(n1 != null && n2 != null){
            if(n1.val <= n2.val){
                splice = n1;
                while(n1 !=null && n1.val <= n2.val){
                    splice = n1;
                    n1 = n1.next;
                }
                splice.next = n2;
                if(n1 == null) return head;
            }else{
                splice =n2;
                while(n2 != null && n2.val < n1.val){
                    splice = n2;
                    n2 = n2.next;
                }
                splice.next = n1;
                if(n2 == null) return head;
            }
        }
        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int c=0;
        ListNode current =head;
        while (current != null){
            current =current.next;
            c++;
        }

        //base case
        if(n == c) {
            ListNode newhead = head.next;
            head.next = null;
            return newhead;
        }

        //recursive

        head.next = removeNthFromEnd(head.next, n);

        return head;

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
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode previous = head;
        ListNode current = head.next;
        while(current != null){
            int temp = previous.val;
            while (current !=null && current.val == temp){
                previous.next = current.next;
                current = current.next;
            }
            if(current !=null){
                previous = previous.next;
                current = current.next;
            }
        }
        return head;
    }
 }




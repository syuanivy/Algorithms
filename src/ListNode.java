import javax.management.ListenerNotFoundException;
import java.util.*;

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
    // smaller values before pivot x, greater or equal values after
    //time limit exceeded
    public static ListNode partition(ListNode head, int x) {
        ListNode start = new ListNode(0);
        ListNode smallerEnd = start;
        ListNode pivot = new ListNode(x);
        ListNode greaterEnd = pivot;
        ListNode n = head;
        while(n != null){
            if(n.val < pivot.val){
                smallerEnd.next = n;
                smallerEnd = smallerEnd.next;
            }else{
                greaterEnd.next = n;
                greaterEnd = greaterEnd.next;
            }
            n=n.next;
        }
        greaterEnd.next = null; //!!!!!!!!!! otherwise it will form a loop!!!!
        smallerEnd.next = pivot.next;
        pivot.next = null;
        return start.next;
    }



    //Linkedlist cycle
    //using HashSet.
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode n = head;
        while(n != null) {
            if(set.contains(n)) return true;
            set.add(n);
            n = n.next;
        }
        return false;
    }
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode n = head;
        while(n != null) {
            if(set.contains(n)) return n;
            set.add(n);
            n = n.next;
        }
        return n;
    }
    //using chasing pointers
    public static boolean hasCycleTwoPointers(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2.next !=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2) return true;
        }
        return false;
    }
    //after meet in the cycle, have one pointer stay
    // where they meet and the other start from the head, move them together,
    //And they will meet again at the start of the cycle.
    public ListNode detectCycleTwoPointers(ListNode head) {
        if (head == null) return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next !=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2 && p2 != null) {
                p1 = head;
                while( p1 != p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
            }return p1;
        }
        return null;
    }
    //reverse linkedlist II, from 1<=m<=n<=list.length
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        Stack stack = new Stack();
        ListNode zero = new ListNode(0);
        zero.next = head;
        ListNode p1 = zero;
        ListNode p = head;
        int count = 1;
        while (count < m){
            p1 = p1.next;
            p = p.next;
            count++;
        }


        while(count <= n){
            stack.add(p);
            p = p.next;
            count++;
        }
        ListNode p2 = p;

        while(!stack.isEmpty()){
            p1.next = (ListNode)stack.pop();
            p1 = p1.next;
        }
        p1.next = p2;
        return zero.next;

    }
    //insertion sort a linked list
    public static ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode start = new ListNode(Integer.MIN_VALUE);
        start.next = head;
        ListNode sorted = head;
        ListNode current = head.next;

        while(current != null){

            while(current!=null && sorted.val <= current.val){
                sorted = sorted.next;
                current = current.next;
            }

            if(current == null) return start.next;
            //now current is the first one that violates the order
            ListNode insertAfter = start;
            ListNode insertBefore = start.next;
            while(insertBefore.val <= current.val){
                insertAfter = insertAfter.next;
                insertBefore = insertBefore.next;
            }
            insertAfter.next = current;
            sorted.next = current.next;
            current.next = insertBefore;

            current = sorted.next;


        }
        return start.next;
    }
    //L0-Ln-L1-Ln-1-L2-Ln-2....
    //Recursive solution, O(n^2), in efficient;
    //Use a stack
    public static void reorderList(ListNode head){
        if(head == null || head.next == null || head.next.next == null) return;
        int len = 0;
        ListNode n = head;
        while(n != null){
            n = n.next;
            len++;
        }
        int firstHalf;
        if(len % 2 == 0) firstHalf = len/2;
        else firstHalf = len/2+1;

        ListNode p1 = head;
        ListNode p2 = head.next;
        while(len>firstHalf){
            p1 = p1.next;
            p2 = p2.next;
            len--;
        }
        p1.next = null;

        Stack stack = new Stack();
        while(p2 != null){
            stack.add(p2);
            p2 = p2.next;
        }

        p1 = head;
        while(stack.isEmpty()){
            ListNode temp = (ListNode)stack.pop();
            temp.next = p1.next;
            p1.next = temp;
            p1 = temp.next;
        }
    }
    public static void reorderListRecur(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;

        ListNode p1 = head;
        ListNode p2 = head.next;
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = null;
        p2.next = head.next;
        head.next = p2;
        reorderListRecur(p2.next);
    }
    //Sort linkedlist in nlgn and constant space
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        int len = 0;
        ListNode n = head;
        while(n != null){
            len++;
            n = n.next;
        }
        int half = len/2;
        n = head;
        while(len>half+1){
            n = n.next;
            len--;
        }
        ListNode head2 = n.next;
        n.next = null;

        ListNode sorted1 = sortList(head);
        ListNode sorted2 = sortList(head2);
        return mergeSortedList(sorted1, sorted2);



    }
    //helper, merge two sorted list in linear time
    public static ListNode mergeSortedList(ListNode l1, ListNode l2){
        if(l1 == null && l2 == null) return null;
        if(l1 == null && l2 != null) return l2;
        if(l1 != null && l2 == null) return l1;

        ListNode head;
        if(l1.val <= l2.val) head = l1;
        else head = l2;

        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode splice = head;
        while(n1 != null && n2 != null){
            if(n1.val <= n2.val) {
                while(n1 != null && n1.val <= n2.val){
                    splice = n1;
                    n1 = n1.next;
                }
                splice.next = n2;
            }else{
                while(n2 != null && n2.val < n1.val){
                    splice = n2;
                    n2 = n2.next;
                }
                splice.next = n1;
            }
        }
        return head;
    }
    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        ListNode n = newHead;
        while(n.next != null){
            n = n.next;
        }
        n.next = head;
        head.next = null;
        return newHead;
    }
    //Add two numbers:
    // Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 0 -> 8
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if(l1 == null && l2 != null) return l2;
        if(l1 != null && l2 == null) return l1;

        ListNode n1 = l1;
        ListNode n2 = l2;
        int s1 = 0, s2 = 0;
        while(n1 != null){
            s1++;
            n1 = n1.next;
        }
        while(n2 != null){
            s2++;
            n2 = n2.next;
        }

        ListNode sumHead;
        int temp = 0;
        ListNode m1 = l1, m2 = l2;
        if(m1.val + m2.val >= 10){
            sumHead = new ListNode(m1.val + m2.val-10);
            temp = 1;
        }else{
            sumHead = new ListNode(m1.val + m2.val);
        }

        ListNode m = sumHead;

        if(s1 <= s2){
            while(s1 > 1){
                s1--;
                s2--;
                m1 = m1.next;
                m2 = m2.next;
                int sum = m1.val +m2.val +temp;
                temp = 0;
                if(sum >= 10){
                    m.next = new ListNode(sum-10);
                    temp = 1;
                }else{
                    m.next = new ListNode(sum);
                }
                m = m.next;
            }
            while(s2 >1){
                s2--;
                m2 = m2.next;
                int sum = m2.val+temp;
                temp =0;
                if(sum >= 10){
                    m.next = new ListNode(sum-10);
                    temp = 1;
                }else{
                    m.next = new ListNode(sum);
                }
                m = m.next;
            }

        }else{
            while(s2 > 1){
                s1--;
                s2--;
                m1 = m1.next;
                m2 = m2.next;
                int sum = m1.val +m2.val +temp;
                temp = 0;
                if(sum >= 10){
                    m.next = new ListNode(sum-10);
                    temp = 1;
                }else{
                    m.next = new ListNode(sum);
                }
                m = m.next;
            }
            while(s1 >1){
                s1--;
                m1 = m1.next;
                int sum = m1.val+temp;
                temp =0;
                if(sum >= 10){
                    m.next = new ListNode(sum-10);
                    temp = 1;
                }else{
                    m.next = new ListNode(sum);
                }
                m = m.next;
            }
        }

        if(temp != 0) m.next = new ListNode(temp);

        return sumHead;
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


    // delete duplicated elements, 112344 to 1234
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
    // 112344 to 23
    public static ListNode deleteDuplicatesAll(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode start = new ListNode(0);
        start.next = head;
        ListNode p1 = start;
        ListNode p2 = head;
        boolean remove = false;

        while(p2 != null && p2.next != null){
            ListNode n1= p2;
            ListNode n2 = p2.next;
            while(n2 != null && n2.val == p2.val){
                n1 = n1.next;
                n2 = n2.next;
                remove = true;
            }
            if(remove){
                p1.next = n2;
                n1.next = null;
            }else{
                p1 = p1.next;
            }

            p2 = p1.next;
            remove = false;
        }
        return start.next;

    }
    public static void printList(ListNode head){

        ListNode l = head;
        while(l != null){
            System.out.println(l.val);
            l = l.next;
        }
    }
 }




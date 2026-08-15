/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {

        while(head==null || head.next == null || head.next.next == null){
            return;
        }

        // Middle of LL
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next !=null){
            slow=slow.next;
            fast = fast.next.next;
        }

        ListNode secondhead = slow.next;
        // Detach LL
        slow.next = null;

        // Reverse LinkedList 
       
            ListNode curr = secondhead;
            ListNode prev = null;
        while(curr!=null){
            ListNode nextnode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextnode;
        }
        
        // Join t2 with t1
        ListNode t1 = head;
        ListNode t2 = prev;
        while(t2 != null){
            ListNode m1 = t1.next;
            ListNode m2 = t2.next;
            t1.next = t2;
            t2.next = m1;
            t1=m1;
            t2=m2;
        }
    }
}
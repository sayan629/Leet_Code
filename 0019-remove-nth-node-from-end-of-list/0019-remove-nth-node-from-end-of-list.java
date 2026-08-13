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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // initializa two pointers...
        ListNode firstptr = dummy;
        ListNode secondptr = dummy;
        
        // move secondptr to n spaces
        for(int i=0;i<=n;i++){
            secondptr=secondptr.next;
        }

        // start moving both pointer together till next of ptr2 is null.
        while(secondptr!=null){
            firstptr = firstptr.next;
            secondptr = secondptr.next;

        }

        //remove that node
        firstptr.next = firstptr.next.next;
        return dummy.next;
    }
}
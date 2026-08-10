// Hare and Tortoise Algorithm
package DSA.LinkedList;

public class MiddleOfLinkedList {

    public ListNode MiddleLinkedList(ListNode head) {

        ListNode fastptr = head;
        ListNode slowptr = head;

        while (fastptr != null && fastptr.next != null) {
            fastptr = fastptr.next.next;
            slowptr = slowptr.next;
        }
        return slowptr;
    }
}

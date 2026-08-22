package DSA.LinkedList;

class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

public class RemoveDuplicatesFromSortedList {

    static Node removeDuplicates(Node head) {

        Node curr = head;
        while (curr != null && curr.next != null) {

            if (curr.data == curr.next.data) {
                Node nextNext = curr.next.next;
                curr.next = nextNext;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

}

package Java.Collections;

class Node3 {
    int data;
    Node3 next;
    Node3 prev;

    Node3(int new_data) {
        this.data = new_data;
        this.next = null;
        this.prev = null;
    }
}

public class CircularLinkedList {
    // https://www.geeksforgeeks.org/dsa/circular-linked-list/

    public static void main(String[] args) {

        // Singly Circular -

        // Node3 head = new Node3(10);

        // head.next = new Node3(20);

        // head.next.next = new Node3(30);

        // head.next.next.next = new Node3(40);

        // head.next.next.next.next = head;

        // Node3 temp = head;
        // while (temp != null) {
        // System.out.println(temp.data);
        // temp = temp.next;
        // }

        // Doubly Circular -

        Node3 head = new Node3(10);

        head.next = new Node3(20);
        head.next.prev = head;

        head.next.next = new Node3(30);
        head.next.next.prev = head.next;

        head.next.next.next = new Node3(40);
        head.next.next.next.prev = head.next.next;

        Node3 tail = head.next.next.next;
        tail.next = head;
        head.prev = tail;

        Node3 temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

}

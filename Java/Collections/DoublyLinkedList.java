package Java.Collections;

class Node2 {
    int data;
    Node2 next;
    Node2 prev;

    Node2(int new_data) {
        this.data = new_data;
        this.next = null;
        this.prev = null;
    }
}

public class DoublyLinkedList {

    public static void main(String[] args) {
        // https://www.geeksforgeeks.org/dsa/doubly-linked-list/

        Node2 head = new Node2(10);

        head.next = new Node2(20);
        head.next.prev = head;

        head.next.next = new Node2(30);
        head.next.next.prev = head.next;

        head.next.next.next = new Node2(40);
        head.next.next.next.prev = head.next.next;

        Node2 temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

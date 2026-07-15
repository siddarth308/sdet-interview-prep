package Java.Collections;

class Node {

    int data;
    Node next;

    Node(int new_data) {
        this.data = new_data;
        this.next = null;
    }
}

public class SinglyLinkedList {
    // Singly Linked List

    // public class Node {

    // // Data part of the node
    // int data;

    // // Pointer to the next node in the list
    // Node next;

    // // Constructor to initialize the node with data
    // public Node(int data){
    // this.data = data;
    // this.next = null;
    // }
    // }

    public static void main(String[] args) {
        // // Create the first node (head of the list)
        Node head = new Node(10);

        // Link the second node
        head.next = new Node(20);

        head.next.next = new Node(30);

        head.next.next.next = new Node(40);

        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

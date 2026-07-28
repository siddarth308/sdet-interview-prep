package Java.Collections;

public class QueueDemo {

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    class Queue {
        Node front, rear;

        void enque(int data){
            Node newNode = new Node(data){
                if (front == null){ // empty queue
                    front = rear = newNode; return;
                }
                rear.next = newNode;
                rear = newNode;
            }
        }

        int deque(int data) throws Exception {
            if (front == null) {
                throw new Exception();
            }
            int result = front.data;
            front = front.next;
            return result;
        }

    }
}

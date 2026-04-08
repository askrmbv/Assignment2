public class CustomerQueue {
    private class Node {
        String customerName;
        Node next;
        Node(String name) { this.customerName = name; }
    }

    private Node front, rear;

    public void enqueue(String name) {
        Node newNode = new Node(name);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public String dequeue() {
        if (front == null) return null;
        String name = front.customerName;
        front = front.next;
        if (front == null) rear = null;
        return name;
    }

    public void displayQueue() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }
        Node current = front;
        System.out.print("Current Queue: ");
        while (current != null) {
            System.out.print("[" + current.customerName + "] -> ");
            current = current.next;
        }
        System.out.println("END");
    }
}
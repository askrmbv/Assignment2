public class MyQueue {
    private Node front;
    private Node rear;

    public void enqueue(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Object dequeue() {
        if (isEmpty()) return null;
        Object data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public Object removeAt(int index) {
        if (isEmpty() || index < 1) return null;
        if (index == 1) return dequeue();

        Node current = front;
        Node prev = null;
        int count = 1;

        while (current != null && count < index) {
            prev = current;
            current = current.next;
            count++;
        }

        if (current == null) return null;

        Object data = current.data;
        prev.next = current.next;
        if (current == rear) rear = prev;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Node temp = front;
        int i = 1;
        while (temp != null) {
            System.out.println(i + ". " + temp.data);
            temp = temp.next;
            i++;
        }
    }
}
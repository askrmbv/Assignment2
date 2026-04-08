public class TransactionHistory {
    private class Node {
        String transaction;
        Node next;
        Node(String data) { this.transaction = data; }
    }

    private Node top;

    public void push(String action) {
        Node newNode = new Node(action);
        newNode.next = top;
        top = newNode;
    }

    public void displayHistory() {
        if (top == null) {
            System.out.println("No recent transactions.");
            return;
        }
        Node current = top;
        System.out.println("--- Recent Transactions (Last to First) ---");
        while (current != null) {
            System.out.println("- " + current.transaction);
            current = current.next;
        }
    }
}
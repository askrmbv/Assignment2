public class AccountList {
    private Node head;

    public void add(BankAccount acc) {
        Node newNode = new Node(acc);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public BankAccount find(String name) {
        Node temp = head;
        while (temp != null) {
            BankAccount acc = (BankAccount) temp.data;
            if (acc.username.equalsIgnoreCase(name)) {
                return acc;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
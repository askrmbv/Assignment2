public class TransactionStack {
    private String[] arr = new String[100];
    private int top = -1;

    public void push(String action) {
        if (top < arr.length - 1) {
            arr[++top] = action;
        }
    }

    public String pop() {
        if (top == -1) return null;
        return arr[top--];
    }

    public String peek() {
        if (top == -1) return "No history";
        return arr[top];
    }
}
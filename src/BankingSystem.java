import java.util.Scanner;

public class BankingSystem {
    private static AccountList accounts = new AccountList();
    private static TransactionStack history = new TransactionStack();
    private static MyQueue accountRequests = new MyQueue();
    private static MyQueue billQueue = new MyQueue();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        accounts.add(new BankAccount("KZ001", "Asanali", 5000.0));

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("9 - Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) enterBank();
            else if (choice == 2) enterATM();
            else if (choice == 3) enterAdmin();
            else if (choice == 9) System.exit(0);
        }
    }

    private static void enterBank() {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1 - Submit account request\n2 - Deposit\n3 - Withdraw\n4 - Pay bill\n5 - Back");
        int choice = sc.nextInt(); sc.nextLine();

        if (choice == 1) {
            System.out.print("Name: ");
            accountRequests.enqueue(sc.nextLine());
        } else if (choice == 2 || choice == 3) {
            System.out.print("Username: ");
            BankAccount acc = accounts.find(sc.nextLine());
            if (acc != null) {
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                if (choice == 2) acc.balance += amt;
                else if (acc.balance >= amt) acc.balance -= amt;
                history.push((choice == 2 ? "Deposit " : "Withdraw ") + amt + " for " + acc.username);
            }
        } else if (choice == 4) {
            System.out.print("Bill name: ");
            billQueue.enqueue(sc.nextLine());
        }
    }

    private static void enterATM() {
        System.out.println("\n--- ATM ---");
        System.out.print("Username: ");
        BankAccount acc = accounts.find(sc.nextLine());
        if (acc == null) return;
        System.out.println("1 - Balance\n2 - Withdraw\n3 - Back");
        int choice = sc.nextInt();
        if (choice == 1) System.out.println("Balance: " + acc.balance);
        else if (choice == 2) {
            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            if (acc.balance >= amt) {
                acc.balance -= amt;
                history.push("ATM Withdraw " + amt);
            }
        }
    }

    private static void enterAdmin() {
        while (true) {
            System.out.println("\n--- ADMIN ---");
            System.out.println("1 - Process Request\n2 - Process Bill\n3 - View Accounts\n4 - Undo\n5 - Back");
            int choice = sc.nextInt(); sc.nextLine();

            if (choice == 1 || choice == 2) {
                MyQueue q = (choice == 1) ? accountRequests : billQueue;
                if (q.isEmpty()) { System.out.println("Empty."); continue; }
                q.display();
                System.out.print("Enter number to process: ");
                int n = sc.nextInt(); sc.nextLine();
                Object data = q.removeAt(n);
                if (data != null && choice == 1) {
                    accounts.add(new BankAccount("ID" + (int)(Math.random()*100), (String)data, 0));
                    System.out.println("Created!");
                }
            } else if (choice == 3) accounts.displayAll();
            else if (choice == 4) history.pop();
            else if (choice == 5) break;
        }
    }
}
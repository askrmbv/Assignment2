import java.util.Scanner;

public class BankingSystem {
    private static AccountList accounts = new AccountList();
    private static TransactionStack history = new TransactionStack();
    private static MyQueue accountRequests = new MyQueue();
    private static MyQueue billQueue = new MyQueue();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        accounts.add(new BankAccount("KZ001", "Asanali", 5000));

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: enterBank(); break;
                case 2: enterATM(); break;
                case 3: enterAdmin(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void enterBank() {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1 - Submit account opening request");
        System.out.println("2 - Deposit money");
        System.out.println("3 - Withdraw money");
        System.out.println("4 - Pay a bill");
        System.out.println("5 - Back");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter your name: ");
            accountRequests.enqueue(sc.nextLine());
            System.out.println("Request submitted to queue.");
        } else if (choice == 2 || choice == 3) {
            System.out.print("Username: ");
            BankAccount acc = accounts.find(sc.nextLine());
            if (acc != null) {
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                if (choice == 2) {
                    acc.balance += amt;
                    history.push("Deposit: " + amt + " to " + acc.username);
                } else if (acc.balance >= amt) {
                    acc.balance -= amt;
                    history.push("Withdraw: " + amt + " from " + acc.username);
                } else System.out.println("Insufficient funds.");
            } else System.out.println("Account not found.");
        } else if (choice == 4) {
            System.out.print("Bill name: ");
            billQueue.enqueue(sc.nextLine());
            System.out.println("Bill added to queue.");
        }
    }

    private static void enterATM() {
        System.out.println("\n--- ATM MENU ---");
        System.out.print("Username: ");
        BankAccount acc = accounts.find(sc.nextLine());
        if (acc == null) {
            System.out.println("Access denied.");
            return;
        }
        System.out.println("1 - Balance enquiry\n2 - Withdraw\n3 - Back");
        int choice = sc.nextInt();
        if (choice == 1) System.out.println("Balance: " + acc.balance);
        else if (choice == 2) {
            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            if (acc.balance >= amt) {
                acc.balance -= amt;
                history.push("ATM Withdraw: " + amt);
            }
        }
    }

    private static void enterAdmin() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1 - Process account queue\n2 - Process bill queue\n3 - View accounts\n4 - Undo\n5 - Back");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            String name = (String) accountRequests.dequeue();
            if (name != null) {
                accounts.add(new BankAccount("ID" + (int)(Math.random()*100), name, 0));
                System.out.println("Created account for " + name);
            }
        } else if (choice == 2) {
            Object bill = billQueue.dequeue();
            if (bill != null) System.out.println("Paid: " + bill);
        } else if (choice == 3) {
            accounts.displayAll();
        } else if (choice == 4) {
            System.out.println("Undone: " + history.pop());
        }
    }
}
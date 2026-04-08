import java.util.LinkedList;
import java.util.Scanner;

public class BankingSystem {
    private static LinkedList<BankAccount> accounts = new LinkedList<>();
    private static TransactionHistory history = new TransactionHistory();
    private static CustomerQueue queue = new CustomerQueue();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Инициализация данных (Aya и Sara)
        accounts.add(new BankAccount("1001", "Aya", 5000.0));
        accounts.add(new BankAccount("1002", "Sara", 3500.0));

        while (true) {
            System.out.println("\n=== ASTANA BANKING SYSTEM ===");
            System.out.println("1. Add Customer to Queue");
            System.out.println("2. Serve Next Customer");
            System.out.println("3. View All Accounts");
            System.out.println("4. View Transaction History");
            System.out.println("5. Deposit/Withdraw");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name to add to queue: ");
                    queue.enqueue(scanner.nextLine());
                    break;
                case 2:
                    String served = queue.dequeue();
                    if (served != null) {
                        System.out.println("Serving customer: " + served);
                        history.push("Served customer: " + served);
                    } else {
                        System.out.println("No customers in queue.");
                    }
                    break;
                case 3:
                    accounts.forEach(System.out::println);
                    break;
                case 4:
                    history.displayHistory();
                    break;
                case 5:
                    handleTransaction();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void handleTransaction() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNum)) {
                System.out.print("1. Deposit  2. Withdraw: ");
                int op = scanner.nextInt();
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();

                if (op == 1) {
                    acc.deposit(amount);
                    history.push("Deposited $" + amount + " to " + accNum);
                } else {
                    acc.withdraw(amount);
                    history.push("Withdrew $" + amount + " from " + accNum);
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }
}
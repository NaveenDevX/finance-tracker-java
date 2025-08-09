import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserManage userManage = new UserManage();
    private static FinanceManager financeManager = new FinanceManager();
    private static String currentUser;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Finance Tracker =====");

        // Login/Register loop
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                if (login(sc)) break;
            } else if (choice.equals("2")) {
                register(sc);
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // Main menu loop
        while (true) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Balance");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addTransaction(sc);
                    break;
                case "2":
                    viewTransactions();
                    break;
                case "3":
                    viewBalance();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Login method
    private static boolean login(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (userManage.validateUser(username, password)) {
            currentUser = username.toLowerCase();
            System.out.println("Login successful! Welcome " + currentUser);
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    // Register method
    private static void register(Scanner sc) {
        System.out.print("Choose username: ");
        String username = sc.nextLine();
        System.out.print("Choose password: ");
        String password = sc.nextLine();

        if (userManage.createUser(username, password)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    // Add Transaction method
    private static void addTransaction(Scanner sc) {
        System.out.print("Type (Income/Expense): ");
        String type = sc.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.print("Category: ");
        String category = sc.nextLine();

        LocalDate date = LocalDate.now();

        Transaction t = new Transaction(type, amount, date, category);
        financeManager.addTransaction(currentUser, t);
        System.out.println("Transaction added!");
    }

    // View Transactions method
    private static void viewTransactions() {
        List<Transaction> list = financeManager.getTransactions(currentUser);
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        System.out.println("Transactions for " + currentUser + ":");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    // View Balance method
    private static void viewBalance() {
        double balance = financeManager.getBalance(currentUser);
        System.out.println("Current Balance: $" + balance);
    }
}

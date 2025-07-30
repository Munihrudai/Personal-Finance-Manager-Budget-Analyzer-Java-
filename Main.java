import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = null, password = null; // Initialize to null

        System.out.println("🔐 Welcome to Personal Finance Manager");
        System.out.print("Do you have an account? (yes/no): ");
        String hasAccount = scanner.nextLine();

        if (hasAccount.equalsIgnoreCase("no")) {
            System.out.print("Create a username: ");
            username = scanner.nextLine();
            System.out.print("Create a password: ");
            password = scanner.nextLine();
            AuthManager.register(username, password);
            System.out.println("✅ User registered successfully.");
        }

        int attempts = 0;
        boolean loggedIn = false;
        while (attempts < 5) {
            System.out.print("Enter username: ");
            username = scanner.nextLine(); // Always assigned here
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            if (AuthManager.login(username, password)) {
                System.out.println("✅ Login successful!");
                loggedIn = true;
                break;
            } else {
                System.out.println("❌ Incorrect username or password. Attempts left: " + (4 - attempts));
                attempts++;
            }
        }

        if (!loggedIn) {
            System.out.println("🚫 Too many failed attempts. Exiting...");
            return;
        }

        FinanceManager manager = new FinanceManager();
        FileManager.loadData(username, manager);

        while (true) {
            System.out.println("\n===== Personal Finance Menu =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Show Summary");
            System.out.println("4. Save & Exit");
            System.out.println("5. Export to CSV");
            System.out.println("6. Set Category Limit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter income category: ");
                    String incomeCat = scanner.nextLine();
                    System.out.print("Enter amount: ₹");
                    double incomeAmt = Double.parseDouble(scanner.nextLine());
                    manager.addIncome(new Income(incomeCat, incomeAmt));
                    System.out.println("✅ Income added.");
                    break;

                case 2:
                    System.out.print("Enter expense category: ");
                    String expenseCat = scanner.nextLine();
                    System.out.print("Enter amount: ₹");
                    double expenseAmt = Double.parseDouble(scanner.nextLine());
                    manager.addExpense(new Expense(expenseCat, expenseAmt));
                    System.out.println("✅ Expense added.");
                    if (manager.exceedsLimit(expenseCat, expenseAmt)) {
                        System.out.println("⚠️ Warning: Your expense ₹" + expenseAmt + " exceeds the limit ₹" + manager.getLimit(expenseCat) + " for category [" + expenseCat + "].");
                    }
                    break;

                case 3:
                    manager.showSummary();
                    break;

                case 4:
                    FileManager.saveToFile(username, manager.getIncomes(), manager.getExpenses());
                    System.out.println("💾 Data saved. Exiting.");
                    return;

                case 5:
                    FileManager.exportCSV(username, manager.getIncomes(), manager.getExpenses());
                    break;

                case 6:
                    System.out.print("Enter category to set limit for: ");
                    String cat = scanner.nextLine();
                    System.out.print("Enter budget limit for this category: ₹");
                    double limit = Double.parseDouble(scanner.nextLine());
                    manager.setLimit(cat, limit);
                    System.out.println("✅ Limit set.");
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static void saveToFile(String username, List<Income> incomes, List<Expense> expenses) {
        try {
            new File("data").mkdirs();
            FileWriter writer = new FileWriter("data/" + username + "_data.txt");
            for (Income i : incomes) {
                writer.write("INCOME," + i.getCategory() + "," + i.getAmount() + "\n");
            }
            for (Expense e : expenses) {
                writer.write("EXPENSE," + e.getCategory() + "," + e.getAmount() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportCSV(String username, List<Income> incomes, List<Expense> expenses) {
        try {
            String home = System.getProperty("user.home");
            FileWriter writer = new FileWriter(home + "/Downloads/" + username + "_finance_report.csv");
            writer.write("Type,Category,Amount\n");
            for (Income i : incomes) {
                writer.write("Income," + i.getCategory() + "," + i.getAmount() + "\n");
            }
            for (Expense e : expenses) {
                writer.write("Expense," + e.getCategory() + "," + e.getAmount() + "\n");
            }
            writer.close();
            System.out.println("✅ Data exported to Downloads/" + username + "_finance_report.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String username, FinanceManager manager) {
        try {
            File file = new File("data/" + username + "_data.txt");
            if (!file.exists()) return;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String type = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                if (type.equals("INCOME")) {
                    manager.addIncome(new Income(category, amount));
                } else if (type.equals("EXPENSE")) {
                    manager.addExpense(new Expense(category, amount));
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

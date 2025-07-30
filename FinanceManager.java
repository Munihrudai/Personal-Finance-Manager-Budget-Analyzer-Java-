import java.util.*;

public class FinanceManager {
    private List<Income> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private Map<String, Double> categoryLimits = new HashMap<>();

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    // Set budget limit for a category
    public void setLimit(String category, double limit) {
        categoryLimits.put(category, limit);
    }

    // Get budget limit for a category
    public double getLimit(String category) {
        return categoryLimits.getOrDefault(category, 0.0);
    }

    // Check if a new expense exceeds the set limit
    public boolean exceedsLimit(String category, double amount) {
        double limit = categoryLimits.getOrDefault(category, Double.MAX_VALUE);
        double totalSpent = expenses.stream()
            .filter(e -> e.getCategory().equalsIgnoreCase(category))
            .mapToDouble(Expense::getAmount)
            .sum();
        return (totalSpent + amount) > limit;
    }

    // Show income, expense, balance summary
    public void showSummary() {
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double balance = totalIncome - totalExpense;

        System.out.println("\n📊 Summary:");
        System.out.println("Total Income: ₹" + totalIncome);
        System.out.println("Total Expenses: ₹" + totalExpense);
        System.out.println("Balance: ₹" + balance);

        if (!categoryLimits.isEmpty()) {
            System.out.println("\n💡 Category Limits:");
            for (String cat : categoryLimits.keySet()) {
                System.out.println("  - " + cat + ": ₹" + categoryLimits.get(cat));
            }
        }
    }

    // ✅ Getter for category limits map
    public Map<String, Double> getCategoryLimits() {
        return categoryLimits;
    }
}

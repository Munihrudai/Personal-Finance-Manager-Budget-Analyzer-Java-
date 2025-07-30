import java.util.*;

public class BudgetManager {
    private List<Expense> expenses = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();
    private Map<String, Double> categoryLimits = new HashMap<>();

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void addExpense(Expense expense) {
        checkLimit(expense.getCategory(), expense.getAmount());
        expenses.add(expense);
    }

    public void setCategoryLimit(String category, double limit) {
        categoryLimits.put(category, limit);
        System.out.println("✅ Limit set: " + category + " → ₹" + limit);
    }

    private void checkLimit(String category, double amount) {
        if (categoryLimits.containsKey(category)) {
            double totalSpent = expenses.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount)
                .sum();

            if (totalSpent + amount > categoryLimits.get(category)) {
                System.out.println("⚠️ Warning: You're exceeding the limit for " + category);
            }
        }
    }

    public List<Expense> getExpenses() { return expenses; }
    public List<Income> getIncomes() { return incomes; }

    public void showSummary() {
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

        System.out.println("📊 Income: ₹" + totalIncome);
        System.out.println("📉 Expenses: ₹" + totalExpense);
        System.out.println("💰 Balance: ₹" + (totalIncome - totalExpense));
    }
}

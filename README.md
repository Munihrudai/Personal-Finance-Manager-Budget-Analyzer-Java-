🔧 Project Title:
Personal Finance Manager & Budget Analyzer (Java Console App)
📌 Project Objective:
To help individuals track their income, expenses, set category-wise budget limits, and generate a detailed CSV report with the final balance—all through a Java console application with user login support and per-user data persistence.

🧱 Tech Stack & Tools Used:
Component	Used Tool or Library
Programming Language	Java (Core Java)
IDE	IntelliJ IDEA or VS Code
Data Persistence	File I/O (Text files per user)
Report Generation	CSV using FileWriter
Folder Location	System Downloads folder (via System.getProperty("user.home"))

📂 Project Structure:
graphql
Copy
Edit
📁 PersonalFinanceManager/
├── Main.java                # Main app logic & user interaction
├── AuthManager.java         # Handles login and registration
├── FinanceManager.java      # Core logic for tracking income, expenses, limits
├── FileManager.java         # Save/load data and CSV export
├── Income.java              # Income model class
├── Expense.java             # Expense model class
└── README.md                # Project explanation
🧠 How the Code Works (Explanation by File)
✅ 1. Main.java
Handles the user interface (menu-driven CLI).

Prompts user to register or login (up to 5 attempts).

Once logged in:

Allows adding income (source + amount)

Allows adding expense (category + amount)

Allows setting budget limit per category

Warns if you exceed set limit

Allows viewing summary

Allows exporting a report to .csv in Downloads/

Saves user data before exit

🔄 User data is persisted and can be loaded in the next login.

✅ 2. AuthManager.java
Provides two static methods:

register(username, password) → stores login in users.txt

login(username, password) → verifies credentials from file

Simple plain text auth (for demo purposes).

✅ You can improve this later with hashed passwords.

✅ 3. FinanceManager.java
Manages:

List<Income> incomes

List<Expense> expenses

Map<String, Double> categoryLimits

Methods:

addIncome(source, amount)

addExpense(category, amount)

setLimit(category, amount)

exceedsLimit(category, amount) → returns true if you cross the budget

🎯 This is your business logic brain.

✅ 4. Income.java & Expense.java
Simple data models:

java
Copy
Edit
public class Income {
    private String source;
    private double amount;
    ...
}
Same for Expense.

🧱 These are your data building blocks.

✅ 5. FileManager.java
Responsible for:

saveData(username, manager) → saves incomes, expenses, and limits to a .txt file

loadData(username, manager) → loads data on next login

exportToCSV(username, manager) → generates .csv report with:

Incomes

Expenses

Limits

Summary

Final Balance

The file is named like:

Copy
Edit
Munna_finance_report_20250730_101532.csv
📝 All data is stored per user and export goes to Downloads.

📊 Example CSV Report Output:
mathematica
Copy
Edit
Personal Finance Report for User: Munna

Incomes
Source,Amount
Freelancing,20000

Expenses
Category,Amount
Groceries,8000
Transport,3000

Category Limits
Category,Limit
Groceries,10000
Transport,5000

Summary
Total Income,Total Expenses,Balance
20000.0,11000.0,9000.0

Final Balance,9000.0
🔐 Security Features:
Basic username & password authentication

Up to 5 login retries

Separate data saved per user

✨ Advanced Features (Added):
Category-wise budget limit checking

CSV reports that are Excel-compatible

Auto-saved user data across sessions

File name includes timestamp to avoid overwrite

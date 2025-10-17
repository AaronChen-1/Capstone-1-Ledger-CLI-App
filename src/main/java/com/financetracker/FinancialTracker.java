package com.financetracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class FinancialTracker {

    public static void main(String[] args) {
        homeScreen();
    }

    public static void simulateLoading(String message) {
        System.out.println("\n" + message + "...");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("                      HOME");
            System.out.println("=".repeat(50));
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("=".repeat(50));
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    simulateLoading("Opening deposit screen");
                    addDeposit(scanner);
                    break;
                case "P":
                    simulateLoading("Opening payment screen");
                    makePayment(scanner);
                    break;
                case "L":
                    simulateLoading("Opening Ledger");
                    ledgerScreen(scanner);
                    break;
                case "X":
                    System.out.println("Goodbye");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Add Deposit for home screen
    public static void addDeposit(Scanner scanner) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      ADD DEPOSIT");
        System.out.println("=".repeat(50));

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Get current date and time
        String date = LocalDate.now().toString();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Create transaction (positive amount for deposit)
        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        saveTransaction(transaction);
        System.out.println("Deposit saved");
    }

    // Make Payment for home screen
    public static void makePayment(Scanner scanner) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      MAKE PAYMENT");
        System.out.println("=".repeat(50));

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 0 ) {
            System.out.println("amount must be greater than 0");
            return;
        }

        // Get current date and time
        String date = LocalDate.now().toString();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Create transaction (negative amount for payment)
        Transaction transaction = new Transaction(date, time, description, vendor, -amount);

        saveTransaction(transaction);
        System.out.println("Payment added");
    }

    // go to ledger screen from home screen
    public static void ledgerScreen(Scanner scanner) {
    while(true){
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      LEDGER");
        System.out.println("=".repeat(50));
        System.out.println("A) All Entries");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().toUpperCase();

        switch(choice) {
            case "A":
                simulateLoading("Loading All transactions");
                displayAll(scanner); break;
            case "D":
                simulateLoading("Loading all deposits");
                displayDeposits(scanner); break;
            case "P":
                simulateLoading("Loading all payments");
                displayPayments(scanner);break;
            case "R":
                simulateLoading("Loading reports page...");
                reportsScreen(scanner);break;
            case "H": return;
            default:
                System.out.println("Invalid option. Try again.");

        }
    }
}

    public static void displayAll(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      ALL TRANSACTIONS");
        System.out.println("=".repeat(50));
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (int i = transactions.size() - 1; i >= 0; i--) { // newest first
                Transaction t = transactions.get(i);
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
            }
        }
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void displayDeposits(Scanner scanner){
        ArrayList<Transaction> transactions = loadTransactions();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      DEPOSITS");
        System.out.println("=".repeat(50));
        boolean found = false;

        for (int i = transactions.size() - 1; i>=0; i--) {
            Transaction t = transactions.get(i);
            if (t.amount > 0 ) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No deposits found.");
        }
        System.out.print("\nPress Enter to continue");
        scanner.nextLine();
    }

    public static void displayPayments(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      PAYMENTS");
        System.out.println("=".repeat(50));
        boolean found = false;
        for (int i = transactions.size() - 1; i >= 0; i--) { // newest first
            Transaction t = transactions.get(i);
            if (t.amount < 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }
    if (!found) {
        System.out.println("No payments found.");
    }
    System.out.print("\nPress Enter to continue...");
    scanner.nextLine();
}

    public static void reportsScreen(Scanner scanner) {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("                      REPORTS");
            System.out.println("=".repeat(50));
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option (0-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    simulateLoading("Showing transactions from current month");
                    monthToDate(scanner);
                    break;
                case "2":
                    simulateLoading("Showing transactions from last month");
                    previousMonth(scanner);
                    break;
                case "3":
                    simulateLoading("Showing transactions from current year");
                    yearToDate(scanner);
                    break;
                case "4":
                    simulateLoading("Showing transactions from last year");
                    previousYear(scanner);
                    break;
                case"5":
                    simulateLoading("loading");
                    searchByVendor(scanner);
                    break;
                case"0": return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void monthToDate(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        LocalDate now = LocalDate.now();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      MONTH TO DATE");
        System.out.println("=".repeat(50));
        boolean found = false;
        for (int i = transactions.size() -1; i > 0; i--) {
            Transaction t = transactions.get(i);
            LocalDate transDate = LocalDate.parse(t.date);
            if (transDate.getYear() == now.getYear() && transDate.getMonth() == now.getMonth()) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for this month");
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public static void previousMonth(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      PREVIOUS MONTH");
        System.out.println("=".repeat(50));
        boolean found = false;

        for (int i = transactions.size() -1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            LocalDate transDate = LocalDate.parse(t.date);
            YearMonth transMonth = YearMonth.from(transDate);
            if (transMonth.equals(lastMonth)) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transasctions found for previous month.");
        }
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void yearToDate(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        int currentYear = LocalDate.now().getYear();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      YEAR TO DATE");
        System.out.println("=".repeat(50));
        boolean found = false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            LocalDate transDate = LocalDate.parse(t.date);
            if (transDate.getYear() == currentYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n",
                        t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for this year.");
        }
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();

    }

    public static void previousYear(Scanner scanner) {
        ArrayList<Transaction> transactions = loadTransactions();
        int lastYear = LocalDate.now().getYear() - 1;
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      PREVIOUS YEAR");
        System.out.println("=".repeat(50));
        boolean found = false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            LocalDate transDate = LocalDate.parse(t.date);
            if (transDate.getYear() == lastYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n",
                        t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for previous year.");
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void searchByVendor(Scanner scanner) {
        System.out.print("\nEnter vendor name: ");
        String searchVendor = scanner.nextLine();

        ArrayList<Transaction> transactions = loadTransactions();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                      SEARCH RESULTS FOR: " + searchVendor);
        System.out.println("=".repeat(50));
        boolean found = false;

        for (int i = transactions.size() -1; i>= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.vendor.equalsIgnoreCase(searchVendor)) {
                System.out.printf("%s | %s | %s | %s | $%.2f%n", t.date, t.time, t.description, t.vendor, t.amount);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for vendor: " + searchVendor);
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    //save transactions to csv file
    public static void saveTransaction(Transaction transaction) {
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction.toCSV() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Transaction t = new Transaction(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), Double.parseDouble(parts[4].trim()));
                    transactions.add(t);
                }
            }
            reader.close();
        } catch (IOException e) {
            //file doesn't exist yet, return empty list
        }
        return transactions;
    }
}
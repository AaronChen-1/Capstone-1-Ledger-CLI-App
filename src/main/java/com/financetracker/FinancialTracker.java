package com.financetracker;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class FinancialTracker {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();
    }

    // Home Screen
    public static void homeScreen() {
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
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Exit");
                    //System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Add Deposit
    public static void addDeposit() {
        System.out.println("\n=== ADD DEPOSIT ===");
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
        System.out.println("Deposit added! (Not saved yet)");
    }

    // Make Payment
    public static void makePayment() {
        System.out.println("\n=== MAKE PAYMENT ===");
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        // Get current date and time
        String date = LocalDate.now().toString();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Create transaction (negative amount for payment)
        Transaction transaction = new Transaction(date, time, description, vendor, -amount);

        saveTransaction(transaction);
        System.out.println("Payment added! (Not saved yet)");
    }

    // Ledger Screen (placeholder for now)
    public static void ledgerScreen() {
    while(true){
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          LEDGER");
        System.out.println("=".repeat(50));
        System.out.println("A) All Entries");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
        scanner.nextLine();


    }

    }

    }

    //Save transaction to CSV file
    public static void saveTransaction(Transaction transaction) {
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction.toCSV() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static ArrayList<Transaction> loadTransactions(){
    ArrayList<Transaction> transactions = new ArrayList<>();
    try{
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        String line;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split("\\|");
            if (parts.length ==5){
                Transaction t = new Transaction(parts[0], parts[1], parts[2],parts[3], Double.parseDouble(parts[4]));
                transactions.add(t);
            }
        }
        reader.close();
    } catch(IOException e){
        //file doesnt exist yet, return empty list
    }
    return transactions;

}
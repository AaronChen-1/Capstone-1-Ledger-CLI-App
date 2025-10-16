package com.financetracker;


import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FinancialTracker {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //homeScreen();
    }

    public static void homeScreen() {
        while(true){
            System.out.println("\n" + "=".repeat(50));
            System.out.println("          ACCOUNTING LEDGER - HOME");
            System.out.println("=".repeat(50));
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("=".repeat(50));
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toUpperCase();

            }
        }
    }

}

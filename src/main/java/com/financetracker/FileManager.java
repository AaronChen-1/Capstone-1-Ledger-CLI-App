package com.financetracker;

public class FileManager{

}

/*

import com.financetracker.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {
    private static final String FILE_NAME = "transactions.csv";

    public static void writeTransaction(Transaction transaction) {
        try {
            // FileWriter with 'true' parameter means append mode (don't overwrite)
            FileWriter writer = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Write the transaction in CSV format
            bufferedWriter.write(transaction.toCSV());
            bufferedWriter.newLine();

            bufferedWriter.close();
            System.out.println("Transaction saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try{
            FileReader reader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while((line = bufferedReader.readLine()) != null);{
                Transaction transaction = parseTransaction(line);
                if (transaction != null) {
                    transactions.add(transaction);
                }
            }
            bufferedReader.close();
        }catch (FileNotFoundException e){
            System.out.println("cannot find file, will make new one");
        } catch(IOException e){
            System.out.println("Error reading the file:" + e.getMessage());
        }
        return transactions;
    }
}

private static Transaction parseTransaction(String line) {
    try {
        String[] parts = line.split("\\|");

        if (parts.length !=5){
            System.out.println("Invalid line format: " + line);
            return null;
        }
        LocalDate date = LocalDate.parse(parts[0]);
        LocalTime time = LocalTime.parse(parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);

        return new Transaction(date, time, description, vendor, amount);

    } catch (Exception e) {
        System.out.println("Error parsing transaction: " + line);
        System.out.println("Error details: " + e.getMessage());
        return null;
    }
    }
}
*/
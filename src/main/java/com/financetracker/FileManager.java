package com.financetracker;

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
}

package com.financetracker;


public class Transaction {
     String date;
     String time;
     String description;
     String vendor;
     double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toCSV() {
        return date + " | " + time + "|" + description +"|" + vendor + "|" + amount;
    }

    public String toString() {
        return String.format("%s | %s | %-20s | %-20s | %8.2f",date,time,description,vendor,amount);
    }
}

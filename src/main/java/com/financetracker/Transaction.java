package com.financetracker;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction(String date, String time, String description, String vendor, double amount) {
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String toCSV() {
        return date + " | " + time + "|" + description +"|" + vendor + "|" + amount;
    }

    public String toString() {
        return String.format("%s | %s | %-20s | %-20s | %8.2f",date,time,description,vendor,amount);
    }
}

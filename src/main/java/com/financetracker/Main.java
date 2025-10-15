package com.financetracker;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Transaction test = new Transaction(
                LocalDate.now(), LocalTime.now(), "Test", "Test", 100.00
        );

        System.out.println(test);
        System.out.println(test.toCSV());
    }
}

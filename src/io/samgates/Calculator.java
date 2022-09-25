package io.samgates;

import java.text.NumberFormat;
import java.util.Formatter;

public class Calculator {
    private int principal;
    private float apr;
    private int months;
    private NumberFormat fc;

    public Calculator(int principal, float apr, int years) {
        final byte PERCENT = 100;
        final byte MONTHS = 12;

        this.principal = principal;
        this.apr = apr  / PERCENT / MONTHS;
        this.months = years * 12;

        this.fc = NumberFormat.getCurrencyInstance();
    }

    public String getMonthlyPayment() {
        return this.fc.format(calculatePayment());
    }

    public void printMonthlyPayment() {
        System.out.println(getMonthlyPayment());
    }

    public void printBalanceTable() {
        System.out.println("\nAmortization schedule\n================");

        Formatter paymentTable = new Formatter();
        String template = "%14s %14s %14s %14s\n";
        paymentTable.format(template, "Month", "Principal", "Interest", "Balance");

        double payment = calculatePayment();

        for(int i = months - 1; i >= 0; i--) {
            double balance = (payment / apr) * (1 - (1 / Math.pow(1 + apr, i)));
            double interest = balance * apr;
            double principal = payment - interest;

            paymentTable.format(
                    template,
                    (months - i),
                    this.fc.format(principal),
                    this.fc.format(interest),
                    this.fc.format(balance)
            );
        }

        System.out.println(paymentTable);
    }

    private double calculatePayment() {
        return principal * ((apr * Math.pow(1 + apr, months)) / (Math.pow(1 + apr, months) - 1));
    }
}
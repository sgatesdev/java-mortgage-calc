package io.samgates;

public class Main {
    static int principal;
    static float apr;
    static int years;

    public static void main(String[] args) {
        System.out.println("Welcome to Sam's Mortgage Calculator\n");

        principal = (int) Console.readNumber("Principal: ", 1000, 250_000);
        apr = (float) Console.readNumber("APR (yearly): ", 0, 10);
        years = (int) Console.readNumber("Term (years): ", 1, 30);

        var mortgageCalc = new Calculator(principal, apr, years);
        mortgageCalc.printMonthlyPayment();
        mortgageCalc.printBalanceTable();
    }

}
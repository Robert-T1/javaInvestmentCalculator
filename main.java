import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Get validated input from the user
        int t = getValidInt(scanner, "Enter simulated investment years: ");
        double annualContributions = getValidDouble(scanner, "Enter annual contributions: ");
        double r = getValidDouble(scanner, "Enter annual return rate (%): ");
        double p = getValidDouble(scanner, "Enter starting amount: ");

        // Pass user input into the calculateInvestment method
        InvestmentData result = calculateInvestment(p, r, t, annualContributions);

        // Display calculated investment results
        System.out.println("\n--- Investment Summary ---");
        System.out.println("Ending Balance: " + result.EndingBalance);
        System.out.println("Total Contributions: " + result.TotalContributions);
        System.out.println("Total Interest: " + result.TotalInterest);
        System.out.println("Years: " + result.Years);

        // Close scanner to prevent resource leak
        scanner.close();
    }

    /**
     * Helper method to safely get a valid integer input from the user.
     * Continues prompting until a valid non-negative integer is entered.
     */
    public static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Helper method to safely get a valid double input from the user.
     * Continues prompting until a valid non-negative number is entered.
     */
    public static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Calculates investment growth over time using annual contributions
     * and a fixed annual interest rate.
     */
    public static InvestmentData calculateInvestment(double p, double r, int t, double contributions) {

        double balance = p;
        double totalContributions = p;
        double totalInterest = 0;

        double rate = r / 100.0;

        // Loop through each year and apply contributions and interest
        for (int i = 0; i < t; i++) {
            balance += contributions;
            totalContributions += contributions;

            double interest = balance * rate;
            balance += interest;

            totalInterest += interest;
        }

        return new InvestmentData(balance, totalContributions, totalInterest, t);
    }
}

/**
 * Data class used to store results of the investment calculation.
 */
class InvestmentData {
    public double EndingBalance;
    public double TotalContributions;
    public double TotalInterest;
    public int Years;

    // Constructor to initialize all fields
    InvestmentData(double endingBalance, double totalContributions, double totalInterest, int years) {
        this.EndingBalance = endingBalance;
        this.TotalContributions = totalContributions;
        this.TotalInterest = totalInterest;
        this.Years = years;
    }
}
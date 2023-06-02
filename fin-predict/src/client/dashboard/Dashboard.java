package client.dashboard;
import client.console_interface.ViewDashboard;

public class Dashboard implements ViewDashboard {

    public static void setup() {
        displayDashboard();
    }

    public static void displayDashboard() {
        // Display Price class findings
        Price.displayPastPrices();
        Price.displayCurrentDayForecast();

        // Display FinancialOpportunities class findings
        FinancialOpportunities.discoverGrowthRate();
        FinancialOpportunities.hasPricesStabilized();
        FinancialOpportunities.isCurrentPriceHigher();

        // Display FinancialRisks class findings
        FinancialRisks.havePricesDecreased();
        FinancialRisks.doPricesPropagate();
        FinancialRisks.isCurrentPriceLower();
    }

    // Displays dashboard using user input
    public void displayUserInputDashboard() {

    }
}

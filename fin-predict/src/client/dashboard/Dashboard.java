package client.dashboard;
import client.console_interface.ViewDashboard;
import client.middleware.RouterClient;

public class Dashboard  implements ViewDashboard {
    protected static Price price = new Price();
    protected static FinancialOpportunities financialOpportunities = new FinancialOpportunities();
    protected static FinancialRisks financialRisks = new FinancialRisks();

    public static void setup() {
        displayDashboard();
    }

    public static void displayDashboard() {
        // Display Price class findings
        price.displayPastPrices();
        price.displayCurrentDayForecast();

        // Display FinancialOpportunities class findings
        financialOpportunities.discoverGrowthRate();
        financialOpportunities.hasPricesStabilized();
        financialOpportunities.isCurrentPriceHigher();

        // Display FinancialRisks class findings
        financialRisks.havePricesDecreased();
        financialRisks.doPricesPropagate();
        financialRisks.isCurrentPriceLower();
    }

    // Displays dashboard using user input
    public void displayUserInputDashboard() {

    }
}

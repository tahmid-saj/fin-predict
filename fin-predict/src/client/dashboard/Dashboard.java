package client.dashboard;
import client.console_interface.Prompt;
import client.console_interface.ViewDashboard;

public class Dashboard implements ViewDashboard {
    protected static Price price = new Price();
    protected static FinancialOpportunities financialOpportunities = new FinancialOpportunities();
    protected static FinancialRisks financialRisks = new FinancialRisks();

    public static void setup() {
        displayDashboard();

        Prompt prompt = new Prompt();
        prompt.loopInput();
    }

    protected static void displayDashboard() {
        // Display Price class findings
        price.displayPastPrices();
        price.displayCurrentDayPrediction();

        // Display FinancialOpportunities class findings
        financialOpportunities.discoverGrowthRate();
        financialOpportunities.havePricesStabilized();
        financialOpportunities.isCurrentPriceHigher();

        // Display FinancialRisks class findings
        financialRisks.havePricesDecreased();
        financialRisks.havePricesPropagated();
        financialRisks.isCurrentPriceLower();
    }

    // Displays dashboard using user input
    public void displayUserInputDashboard(int inputPreviousDaysDisplay,
                                          int inputIncreaseDaysObserved,
                                          double inputGrowthRateThreshold,
                                          int inputDaysObservePriceStabilization,
                                          double inputStabilizationBoundary,
                                          int inputAvgPreviousHigherDaysObserved,
                                          int inputDecreaseDaysObserved,
                                          int inputPricePropagationDaysObserved,
                                          double inputPricePropagationChange,
                                          int inputAvgPreviousLowerDaysObserved) {

        // Display Price class findings
        price.displayPastPrices(inputPreviousDaysDisplay);
        price.displayCurrentDayPrediction();

        // Display FinancialOpportunities class findings
        financialOpportunities.discoverGrowthRate(inputIncreaseDaysObserved, inputGrowthRateThreshold);
        financialOpportunities.havePricesStabilized(inputDaysObservePriceStabilization, inputStabilizationBoundary);
        financialOpportunities.isCurrentPriceHigher(inputAvgPreviousHigherDaysObserved);

        // Display FinancialRisks class findings
        financialRisks.havePricesDecreased(inputDecreaseDaysObserved);
        financialRisks.havePricesPropagated(inputPricePropagationDaysObserved, inputPricePropagationChange);
        financialRisks.isCurrentPriceLower(inputAvgPreviousLowerDaysObserved);
    }
}

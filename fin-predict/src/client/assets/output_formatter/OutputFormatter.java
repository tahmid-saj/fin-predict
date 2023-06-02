package client.assets.output_formatter;

import java.util.Date;

public class OutputFormatter {
    // Use cases:

    // Price class:
    // - Printing the returned previous prices in a table format
    // - Printing the returned current day price in a dot jot

    // FinancialOpportunities class:
    // - Printing if the price growth rate is over a threshold in a dot jot
    // - Printing if the prices have stabilized to a low point in time frame requested in a dot jot
    // - Printing if prediction is higher than the current average over time frame requested in a dot jot

    // FinancialRisks class:
    // - Printing if the prices have decreased over time frame
    // - Printing if prices have propagated over time frame
    // - Printing if prediction is lower than the current average over time frame requested

    // Request class:
    //

    // Price class calls:
    public static void printPreviousPrices(int daysRequested) {

    }

    public static void printCurrentDayPrediction(Date currentDate) {

    }

    // FinancialOpportunities class calls:
    public static void printGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold, boolean hasIncreased) {

    }

    public static void printHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary, boolean hasStabilized) {

    }

    public static void printIsCurrentPriceHigher(int avgPreviousPriceDaysObserved, boolean isPriceHigher) {

    }

    // FinancialRisks class calls:
    public static void reqHavePricesDecreased(int financialRiskDaysObserved, boolean havePricesDecreased) {

    }

    public static void reqHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved, boolean havePricesPropagated) {

    }

    public static void reqIsCurrentPriceLower(int avgPreviousPriceDaysObserved, boolean isPriceLower) {
        
    }
}

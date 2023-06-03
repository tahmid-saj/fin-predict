package client.assets.output_formatter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public static void printPreviousPrices(List<Double> previousPrices, int daysRequested, List<LocalDateTime> previousDaysToDisplay, Map<LocalDateTime, Double> datePrices) {
        System.out.println("Previous Days                     | Previous Prices");
        for (LocalDateTime date : datePrices.keySet()) {
            System.out.println(date + "     | " + datePrices.get(date));
        }
    }

    public static void printCurrentDayPrediction(LocalDateTime currentDate, double currentDayPrediction) {
        System.out.println(" - The prediction for " + currentDate + " is: " + currentDayPrediction);
    }

    // FinancialOpportunities class calls:
    public static void printGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold, boolean hasIncreased) {
        if (hasIncreased) {
            System.out.println(" - The growth rate increased more than " + growthRateThreshold + " in the past " + daysObserved + " days");
        } else {
            System.out.println(" - The growth rate has not increased past " + growthRateThreshold + " in the past " + daysObserved + " days");
        }
    }

    public static void printHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary, boolean hasStabilized) {
        if (hasStabilized) {
            System.out.println(" - The prices have stabilized and have been between +/- " + stabilizationPlusMinusBoundary + " % the past " + stabilizedPriceDaysObserved + " days");
        } else {
            System.out.println(" - The prices have not stabilized propagated over +/- " + stabilizationPlusMinusBoundary + " % the past " + stabilizedPriceDaysObserved + " days");
        }
    }

    public static void printIsCurrentPriceHigher(int avgPreviousPriceDaysObserved, boolean isPriceHigher) {
        if (isPriceHigher) {
            System.out.println(" - The current day's price is higher than the previous " + avgPreviousPriceDaysObserved + " days average");
        }
    }

    // FinancialRisks class calls:
    public static void printHavePricesDecreased(int financialRiskDaysObserved, boolean havePricesDecreased) {
        if (havePricesDecreased) {
            System.out.println(" - The prices have decreased in the past " + financialRiskDaysObserved + " days");
        }
    }

    public static void printHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved, boolean havePricesPropagated) {
        if (havePricesPropagated) {
            System.out.println(" - The prices have propagated past " + priceRateOfIncreaseObserved + " in the past " + priceRateOfIncreaseDaysObserved + " days");
        } else {
            System.out.println(" - The prices have not propagated past " + priceRateOfIncreaseObserved + " in the past " + priceRateOfIncreaseDaysObserved + " days");
        }
    }

    public static void printIsCurrentPriceLower(int avgPreviousPriceDaysObserved, boolean isPriceLower) {
        if (isPriceLower) {
            System.out.println(" - The current day's price is lower than the previous " + avgPreviousPriceDaysObserved + " days average");
        }
    }
}

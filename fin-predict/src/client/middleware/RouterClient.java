package client.middleware;

import java.util.ArrayList;
import java.util.List;

public class RouterClient {

    // Use cases:

    // Price class:
    // - Retrieving previous prices of interval requested in days -> Calls RouterServer to call CSVDatabase
    // - Retrieving current day's prediction -> calls RouterServer to call ModelConnection

    // FinancialOpportunities class:
    // - Checking if price growth rate is over a threshold -> Calls RouterServer to call DataAnomalyFinder
    // - Checking if prices have stabilized to a low point in time frame requested -> Calls RouterServer to call DataAnomalyFinder
    // - Checking if prediction is higher than the current average over time frame requested -> Calls RouterServer to call DataAnomalyFinder

    // FinancialRisks class:
    // - Checking if prices have decreased over time frame -> Calls RouterServer to call DataAnomalyFinder
    // - Checking if prices have propagated over time frame -> Calls RouterServer to call DataAnomalyFinder
    // - Checking if prediction is lower than the current average over time frame requested -> Calls RouterServer to call DataAnomalyFinder

    // Request class:
    //

    // Price class calls:
    protected List<Double> reqPreviousPrices(int daysRequested) {
        List<Double> resPreviousPrices = new ArrayList<Double>();
        resPreviousPrices.add(1.0);
        resPreviousPrices.add(2.0);
        return resPreviousPrices;
    }

    protected double reqCurrentDayPrediction() {
        return 1.0;
    }

    // FinancialOpportunities class calls:
    protected boolean reqGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold) {
        return true;
    }

    protected boolean reqHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        return true;
    }

    protected boolean reqIsCurrentPriceHigher(int avgPreviousPriceDaysObserved) {
        return true;
    }

    // FinancialRisks class calls:
    protected boolean reqHavePricesDecreased(int financialRiskDaysObserved) {
        return true;
    }

    protected boolean reqHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        return true;
    }

    protected boolean reqIsCurrentPriceLower(int avgPreviousPriceDaysObserved) {
        return true;
    }
}

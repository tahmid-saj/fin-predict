package client.dashboard;

import client.middleware.RouterClient;

public class FinancialOpportunities extends RouterClient {
    private static int growthRateDaysObserved = 14;
    private static double growthRateThreshold = 20.0;

    private static int stabilizedPriceDaysObserved = 90;
    private static double stabilizationPlusMinusBoundary = 8.0;

    private static int avgPreviousPriceDaysObserved = 14;

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(int daysObserved, double growthRateThreshold) {

    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(int daysObserved) {

    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(double growthRateThreshold) {

    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate() {

    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {

    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(int stabilizedPriceDaysObserved) {

    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(double stabilizationPlusMinusBoundary) {

    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized() {

    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceHigher(int avgPreviousPriceDaysObserved) {

    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceHigher() {

    }
}

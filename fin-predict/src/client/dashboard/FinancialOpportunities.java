package client.dashboard;

import client.assets.output_formatter.OutputFormatter;
import client.middleware.ClientRouter;

public class FinancialOpportunities extends ClientRouter {
    private static int growthRateDaysObserved = 14;
    private static double growthRateThreshold = 20.0;

    private static int stabilizedPriceDaysObserved = 90;
    private static double stabilizationPlusMinusBoundary = 8.0;

    private static int avgPreviousPriceDaysObserved = 14;

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(int daysObserved, double growthRateThreshold) {
        boolean resGrowthRateOverThreshold = ClientRouter.reqGrowthRateIncreasedPastThreshold(daysObserved, growthRateThreshold);
        OutputFormatter.printGrowthRateIncreasedPastThreshold(daysObserved, growthRateThreshold, resGrowthRateOverThreshold);
    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(int daysObserved) {
        boolean resGrowthRateOverThreshold = ClientRouter.reqGrowthRateIncreasedPastThreshold(daysObserved, growthRateThreshold);
        OutputFormatter.printGrowthRateIncreasedPastThreshold(daysObserved, growthRateThreshold, resGrowthRateOverThreshold);
    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate(double growthRateThreshold) {
        boolean resGrowthRateOverThreshold = ClientRouter.reqGrowthRateIncreasedPastThreshold(growthRateDaysObserved, growthRateThreshold);
        OutputFormatter.printGrowthRateIncreasedPastThreshold(growthRateDaysObserved, growthRateThreshold, resGrowthRateOverThreshold);
    }

    // Checks if growth rate is over the threshold
    protected void discoverGrowthRate() {
        boolean resGrowthRateOverThreshold = ClientRouter.reqGrowthRateIncreasedPastThreshold(growthRateDaysObserved, growthRateThreshold);
        OutputFormatter.printGrowthRateIncreasedPastThreshold(growthRateDaysObserved, growthRateThreshold, resGrowthRateOverThreshold);
    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        boolean resPricesStabilized = ClientRouter.reqHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary);
        OutputFormatter.printHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary, resPricesStabilized);
    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(int stabilizedPriceDaysObserved) {
        boolean resPricesStabilized = ClientRouter.reqHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary);
        OutputFormatter.printHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary, resPricesStabilized);
    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized(double stabilizationPlusMinusBoundary) {
        boolean resPricesStabilized = ClientRouter.reqHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary);
        OutputFormatter.printHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary, resPricesStabilized);
    }

    // Checks if prices have stabilized
    protected void hasPricesStabilized() {
        boolean resPricesStabilized = ClientRouter.reqHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary);
        OutputFormatter.printHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary, resPricesStabilized);
    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceHigher(int avgPreviousPriceDaysObserved) {
        boolean resCurrentPriceHigher = ClientRouter.reqIsCurrentPriceHigher(avgPreviousPriceDaysObserved);
        OutputFormatter.printIsCurrentPriceHigher(avgPreviousPriceDaysObserved, resCurrentPriceHigher);
    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceHigher() {
        boolean resCurrentPriceHigher = ClientRouter.reqIsCurrentPriceHigher(avgPreviousPriceDaysObserved);
        OutputFormatter.printIsCurrentPriceHigher(avgPreviousPriceDaysObserved, resCurrentPriceHigher);
    }
}

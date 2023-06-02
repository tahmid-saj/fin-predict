package server.data_anomaly_finder;

import server.middleware.ServerRouter;

public class DataAnomalyFinder extends ServerRouter {

    // FinancialOpportunities class calls:
    public static boolean getGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold) {
        return true;
    }

    public static boolean getHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        return true;
    }

    public static boolean getIsCurrentPriceHigher(int avgPreviousPriceDaysObserved) {
        return true;
    }

    // FinancialRisks class calls:
    public static boolean getHavePricesDecreased(int financialRiskDaysObserved) {
        return true;
    }

    public static boolean getHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        return true;
    }

    public static boolean getIsCurrentPriceLower(int avgPreviousPriceDaysObserved) {
        return true;
    }
}

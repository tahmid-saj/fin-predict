package client.dashboard;

import client.middleware.RouterClient;

public class FinancialRisks extends RouterClient {
    private static int financialRiskDaysObserved = 90;

    private static int priceRateOfIncreaseDaysObserved = 14;
    private static double priceRateOfIncreaseObserved = 35.0;

    private static int avgPreviousPriceDaysObserved = 14;

    // Checks if price's rate of increase has been negative over time frame
    protected void havePricesDecreased(int financialRiskDaysObserved) {

    }

    // Checks if price's rate of increase has been negative over time frame
    protected void havePricesDecreased() {

    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void doPricesPropagate(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {

    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void doPricesPropagate(int priceRateOfIncreaseDaysObserved) {

    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void doPricesPropagate(double priceRateOfIncreaseObserved) {

    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void doPricesPropagate() {

    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceLower(int avgPreviousPriceDaysObserved) {

    }

    // Checks if the prediction is higher than the average previous price over the time frame
    protected void isCurrentPriceLower() {

    }
}

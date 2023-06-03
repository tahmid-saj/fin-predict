package client.dashboard;

import client.assets.output_formatter.OutputFormatter;
import client.middleware.ClientRouter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FinancialRisks extends ClientRouter {
    private static int financialRiskDaysObserved = 90;

    private static int priceRateOfIncreaseDaysObserved = 14;
    private static double priceRateOfIncreaseObserved = 35.0;

    private static int avgPreviousPriceDaysObserved = 14;

    // Checks if price's rate of increase has been negative over time frame
    protected void havePricesDecreased(int financialRiskDaysObserved) {
        boolean resPricesDecreased = ClientRouter.reqHavePricesDecreased(financialRiskDaysObserved);
        OutputFormatter.printHavePricesDecreased(financialRiskDaysObserved, resPricesDecreased);
    }

    // Checks if price's rate of increase has been negative over time frame
    protected void havePricesDecreased() {
        boolean resPricesDecreased = ClientRouter.reqHavePricesDecreased(financialRiskDaysObserved);
        OutputFormatter.printHavePricesDecreased(financialRiskDaysObserved, resPricesDecreased);
    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void havePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        boolean resPricedPropagated = ClientRouter.reqHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved);
        OutputFormatter.printHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved, resPricedPropagated);
    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void havePricesPropagated(int priceRateOfIncreaseDaysObserved) {
        boolean resPricedPropagated = ClientRouter.reqHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved);
        OutputFormatter.printHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved, resPricedPropagated);
    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void havePricesPropagated(double priceRateOfIncreaseObserved) {
        boolean resPricedPropagated = ClientRouter.reqHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved);
        OutputFormatter.printHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved, resPricedPropagated);
    }

    // Checks if price's rate of increase propagates greatly over a time frame
    protected void havePricesPropagated() {
        boolean resPricedPropagated = ClientRouter.reqHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved);
        OutputFormatter.printHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved, resPricedPropagated);
    }

    // Checks if the prediction is lower than the average previous price over the time frame
    protected void isCurrentPriceLower(int avgPreviousPriceDaysObserved) throws IOException, ParseException {
        boolean resCurrentPriceLower = ClientRouter.reqIsCurrentPriceLower(avgPreviousPriceDaysObserved);
        OutputFormatter.printIsCurrentPriceLower(avgPreviousPriceDaysObserved, resCurrentPriceLower);
    }

    // Checks if the prediction is lower than the average previous price over the time frame
    protected void isCurrentPriceLower() throws IOException, ParseException {
        boolean resCurrentPriceLower = ClientRouter.reqIsCurrentPriceLower(avgPreviousPriceDaysObserved);
        OutputFormatter.printIsCurrentPriceLower(avgPreviousPriceDaysObserved, resCurrentPriceLower);
    }
}

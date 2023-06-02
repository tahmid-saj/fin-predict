package client.dashboard;
import client.assets.output_formatter.OutputFormatter;
import client.middleware.ClientRouter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Price extends ClientRouter {
    static Date today = new Date();

    // Default value of past days of the prices to display
    protected static final int previousDaysDisplay = 14;

    // Displays past prices if daysToDisplay is given
    protected void displayPastPrices(int daysToDisplay) {
        List<Double> resPreviousPrices = ClientRouter.reqPreviousPrices(daysToDisplay);
        List<Date> previousDaysToDisplay = new ArrayList<Date>();
        OutputFormatter.printPreviousPrices(resPreviousPrices, daysToDisplay, previousDaysToDisplay);
    }

    // Displays past prices if daysToDisplay is not given
    protected void displayPastPrices() {
        // Uses previousDaysDisplay by default
        List<Double> resPreviousPrices = ClientRouter.reqPreviousPrices(previousDaysDisplay);
        List<Date> previousDaysToDisplay = new ArrayList<Date>();
        OutputFormatter.printPreviousPrices(resPreviousPrices, previousDaysDisplay, previousDaysToDisplay);
    }

    // Displays the forecasting prediction of current day
    protected void displayCurrentDayForecast() {
        double resCurrentDayForecast = ClientRouter.reqCurrentDayPrediction();
        OutputFormatter.printCurrentDayPrediction(today);
    }
}

package client.dashboard;
import client.console_interface.ViewDashboard;
import client.middleware.RouterClient;

import java.util.List;

public class Price extends RouterClient {
    // Default value of past days of the prices to display
    protected static final int previousDaysDisplay = 14;

    // Displays past prices if daysToDisplay is given
    protected void displayPastPrices(int daysToDisplay) {

    }

    // Displays past prices if daysToDisplay is not given
    protected void displayPastPrices() {
        // Uses previousDaysDisplay by default
        List<Double> resPreviousPrices = RouterClient.reqPreviousPrices(previousDaysDisplay);

    }

    // Displays the forecasting prediction of current day
    protected void displayCurrentDayForecast() {
        System.out.println("Displaying forecast for: " + ViewDashboard.today);
    }
}

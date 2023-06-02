package client.dashboard;
import client.console_interface.ViewDashboard;

public class Price extends Dashboard {
    // Default value of past days of the prices to display
    protected static final int previousDaysDisplay = 14;

    // Displays past prices if daysToDisplay is given
    protected void displayPastPrices(int daysToDisplay) {

    }

    // Displays past prices if daysToDisplay is not given
    protected void displayPastPrices() {
        // Uses previousDaysDisplay by default

    }

    // Displays the forecasting prediction of current day
    protected void displayCurrentDayForecast() {
        System.out.println("Displaying forecast for: " + ViewDashboard.today);
    }
}

package client.dashboard;
import client.console_interface.ViewDashboard;

public class Price extends Dashboard {
    // Default value of past days of the prices to display
    private static final int previousDaysDisplay = 14;

    // Displays past prices if daysToDisplay is given
    private static void displayPastPrices(int daysToDisplay) {

    }

    // Displays past prices if daysToDisplay
    private static void displayPastPrices() {

    }

    // Displays the forecasting prediction of current day
    private static void displayCurrentDayForecast() {
        System.out.println("Displaying forecast for: " + ViewDashboard.today);
    }
}

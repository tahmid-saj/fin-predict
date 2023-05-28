package client.console_interface;

// Console interface:

// View interface:
// Displays initial dashboard upon opening app
// Prompts users for displaying forecasting predictions

// Console class:
// Prompts users for performing Refresh and Read operations on database

// View interface:
// Contains display methods to implement for the Command and Dashboard classes
public interface View {
        public void setup();
        public void displayDashboard();
        public void displayForecast();
}

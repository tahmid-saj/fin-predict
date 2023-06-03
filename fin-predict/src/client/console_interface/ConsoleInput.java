package client.console_interface;

// Console interface:

// View interface:
// Displays initial dashboard upon opening app
// Prompts users for displaying forecasting predictions

// Console class:
// Prompts users for performing Refresh and Read operations on database

// Console class:
// Abstract class which is used in parallel to the view interface
// Contains abstract methods for the Input and Dashboard classes
public abstract class ConsoleInput {
    abstract void loopInput();

    protected static void predictOperation() {
    }

    protected static void refreshOperation(String begDate, String endDate) {

    }
}

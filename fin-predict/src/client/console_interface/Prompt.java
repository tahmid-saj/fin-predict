package client.console_interface;

import client.dashboard.Dashboard;

import java.util.Scanner;

public class Prompt extends ConsoleInput implements ViewConsole, ViewDashboard {
    private static Request request = new Request();
    private static Dashboard dashboard = new Dashboard();

    // Use cases:
    // - lookInput : Prompts user until they choose to exit the app. Asks if user would like to perform the below operations:
    // - Read: Will display a prediction for the current day
    // - Dashboard: display the dashboard using user's input
    // - Refresh: Will refresh the data in the database
    // loopInput will use the Input class to parse through the user's input

    // - refreshOperation: Performs a refresh on the database
    // - predictOperation: Displays current day's prediction or the dashboard using user input
    // - displayDashboard: Displays dashboard using user input


    @Override
    public void loopInput(boolean close) {
        close = true;

        while (!close) {
            System.out.println("Would you like to generate a prediction or perform a refresh on the data?");
            close = Input.parseInput();
        }
    }

    protected static void predictOperation() {
        request.performPredictOperation();
    }

    protected static void refreshOperation(String begDate, String endDate) {
        request.performRefreshOperation(begDate, endDate);
    }

    public static void displayDashboard(int inputPreviousDaysDisplay,
                                        int inputIncreaseDaysObserved,
                                        double inputGrowthRateThreshold,
                                        int inputDaysObservePriceStabilization,
                                        double inputStabilizationBoundary,
                                        int inputAvgPreviousHigherDaysObserved,
                                        int inputDecreaseDaysObserved,
                                        int inputPricePropagationDaysObserved,
                                        double inputPricePropagationChange,
                                        int inputAvgPreviousLowerDaysObserved) {

    }

}

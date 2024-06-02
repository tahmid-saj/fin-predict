package client.console_interface;

import client.assets.output_formatter.OutputFormatter;
import client.dashboard.Dashboard;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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
    public void loopInput() throws Exception {
        boolean close = false;

        while (!close) {
            System.out.print("Would you like to generate a prediction / dashboard or perform a refresh on the data? ");
            close = Input.parseInput();
        }
    }

    protected static void predictOperation() throws Exception {
        double currentDayPrediction = request.performPredictOperation();
        LocalDateTime today = LocalDateTime.now();
        OutputFormatter.printCurrentDayPrediction(today, currentDayPrediction);
    }

    protected static void refreshOperation(String begDate, String endDate) {
        boolean refreshOperationSuccess = request.performRefreshOperation(begDate, endDate);

        if (!refreshOperationSuccess) {
            System.out.println("The refresh operation failed");
        } else {
            System.out.println("The refresh operation succeeded");
        }
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
                                        int inputAvgPreviousLowerDaysObserved) throws IOException, ParseException {

        dashboard.displayUserInputDashboard(inputPreviousDaysDisplay,
                                            inputIncreaseDaysObserved,
                                            inputGrowthRateThreshold,
                                            inputDaysObservePriceStabilization,
                                            inputStabilizationBoundary,
                                            inputAvgPreviousHigherDaysObserved,
                                            inputDecreaseDaysObserved,
                                            inputPricePropagationDaysObserved,
                                            inputPricePropagationChange,
                                            inputAvgPreviousLowerDaysObserved);
    }

}

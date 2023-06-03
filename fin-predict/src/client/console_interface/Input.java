package client.console_interface;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Input extends Prompt {

    protected static boolean parseInput() throws Exception {
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine().toLowerCase();

        switch (inputString) {
            case "predict":
            case "p":
                parseInputPredictOperation();
                break;
            case "refresh":
            case "r":
                parseInputRefreshOperation();
                break;
            case "dashboard":
            case "d":
                parseInputDisplayDashboard();
                break;
            case "close":
            case "cls":
            case "c":
                return true;
            default:
                System.out.println("Please either enter predict (p), refresh (r), dashboard (d), close / cls (c) ");
                break;
        }

        return false;
    }

    private static void parseInputPredictOperation() throws Exception {
        Prompt.predictOperation();
    }

    private static void parseInputRefreshOperation() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter beginning date (YYYMMDD format): ");
        String inputStringBegDate = input.nextLine();
        System.out.print("Enter end date (YYYMMDD format): ");
        String inputStringEndDate = input.nextLine();

        Prompt.refreshOperation(inputStringBegDate, inputStringEndDate);
    }

    private static void parseInputDisplayDashboard() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        // Prices class
        System.out.print("Enter previous number of days to display in dashboard: ");
        int inputIntPreviousDaysDisplay = input.nextInt();

        // FinancialOpportunities class
        System.out.print("Enter previous number of days to observe growth rate increase: ");
        int inputIntIncreaseDaysObserved = input.nextInt();
        System.out.print("Enter threshold to observe growth rate increase: ");
        double inputFloatGrowthRateThreshold = input.nextFloat();
        System.out.print("Enter previous number of days to observe price stabilization: ");
        int inputIntDaysObservePriceStabilization = input.nextInt();
        System.out.print("Enter stabilization +/- percentage boundary: ");
        double inputFloatStabilizationBoundary = input.nextFloat();
        System.out.print("Enter previous number of days to observe if price is higher than average: ");
        int inputIntAvgPreviousHigherDaysObserved = input.nextInt();

        // FinancialRisks class
        System.out.print("Enter previous number of days to observe decrease in price: ");
        int inputIntDecreaseDaysObserved = input.nextInt();
        System.out.print("Enter previous number of days to observe price propagation: ");
        int inputIntPricePropagationDaysObserved = input.nextInt();
        System.out.print("Enter propagation rate of change percentage: ");
        double inputFloatPricePropagationChange = input.nextFloat();
        System.out.print("Enter previous number of days to observe if price is lower than average: ");
        int inputIntAvgPreviousLowerDaysObserved = input.nextInt();

        Prompt.displayDashboard(inputIntPreviousDaysDisplay,
                            inputIntIncreaseDaysObserved,
                            inputFloatGrowthRateThreshold,
                            inputIntDaysObservePriceStabilization,
                            inputFloatStabilizationBoundary,
                            inputIntAvgPreviousHigherDaysObserved,
                            inputIntDecreaseDaysObserved,
                            inputIntPricePropagationDaysObserved,
                            inputFloatPricePropagationChange,
                            inputIntAvgPreviousLowerDaysObserved);
    }
}

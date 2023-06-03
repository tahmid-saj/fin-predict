package server.data_anomaly_finder;

import org.json.simple.parser.ParseException;
import server.middleware.ServerRouter;
import server.model_connection.ModelConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataAnomalyFinder extends ServerRouter {

    // FinancialOpportunities class calls:
    public static boolean getGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold) {
        return true;
    }

    public static boolean getHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        return true;
    }

    public static boolean getIsCurrentPriceHigher(int avgPreviousPriceDaysObserved) throws IOException, ParseException {
        double currentDayPrediction = ModelConnection.getCurrentDayPrediction();

        return currentDayPrediction > calculateAvgPreviousPrice(avgPreviousPriceDaysObserved);
    }

    // FinancialRisks class calls:
    public static boolean getHavePricesDecreased(int financialRiskDaysObserved) {
        return true;
    }

    public static boolean getHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        return true;
    }

    public static boolean getIsCurrentPriceLower(int avgPreviousPriceDaysObserved) throws IOException, ParseException {
        double currentDayPrediction = ModelConnection.getCurrentDayPrediction();

        return currentDayPrediction < calculateAvgPreviousPrice(avgPreviousPriceDaysObserved);
    }

    // Helper methods
    private static double calculateAvgPreviousPrice(int daysObserved) {
        double sumPrices = 0.0;

        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scanner = new Scanner(file);

            int lineNum = 0;
            while (scanner.hasNextLine() && lineNum <= daysObserved) {
                String row = scanner.nextLine();

                if (lineNum > 0) {
                    String[] rowList = row.split(",");
                    double rowPrice = Double.valueOf(rowList[1]);
                    sumPrices += rowPrice;
                }
                lineNum++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return sumPrices / daysObserved;
    }
}

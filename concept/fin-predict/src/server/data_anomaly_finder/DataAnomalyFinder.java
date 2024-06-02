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
        double avgGrowthRate = 0.0;

        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scannerFirstLine = new Scanner(file);
            Scanner scannerSecondLine = new Scanner(file);

            String tmp = scannerSecondLine.nextLine();

            int lineNum = 0;
            while (scannerSecondLine.hasNextLine() && lineNum <= daysObserved - 1) {
                String rowFirst = scannerFirstLine.nextLine();
                String rowSecond = scannerSecondLine.nextLine();

                if (lineNum > 0) {
                    String[] rowListFirst = rowFirst.split(",");
                    String[] rowListSecond = rowSecond.split(",");

                    double rowFirstPrice = Double.valueOf(rowListFirst[1]);
                    double rowSecondPrice = Double.valueOf(rowListSecond[1]);
                    double rowGrowthRate = (rowSecondPrice / rowFirstPrice) * 100.0;

                    avgGrowthRate += rowGrowthRate;
                }

                lineNum++;
            }

            avgGrowthRate /= daysObserved - 1;

            if (avgGrowthRate > growthRateThreshold) {
                return true;
            }

            scannerFirstLine.close();
            scannerSecondLine.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scannerFirstLine = new Scanner(file);
            Scanner scannerSecondLine = new Scanner(file);

            String tmp = scannerSecondLine.nextLine();

            int lineNum = 0;
            while (scannerSecondLine.hasNextLine() && lineNum <= stabilizedPriceDaysObserved - 1) {
                String rowFirst = scannerFirstLine.nextLine();
                String rowSecond = scannerSecondLine.nextLine();

                if (lineNum > 0) {
                    String[] rowListFirst = rowFirst.split(",");
                    String[] rowListSecond = rowSecond.split(",");

                    double rowFirstPrice = Double.valueOf(rowListFirst[1]);
                    double rowSecondPrice = Double.valueOf(rowListSecond[1]);
                    double rowPriceDiff = rowSecondPrice - rowFirstPrice;
                    double rowPriceOriginal = (1 + (stabilizationPlusMinusBoundary / 100.0)) * rowFirstPrice;

                    if (Math.abs(rowPriceDiff) > rowPriceOriginal) {
                        return true;
                    }
                }
            }

            scannerFirstLine.close();
            scannerSecondLine.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getIsCurrentPriceHigher(int avgPreviousPriceDaysObserved) throws IOException, ParseException {
        double currentDayPrediction = ModelConnection.getCurrentDayPrediction();

        return currentDayPrediction > calculateAvgPreviousPrice(avgPreviousPriceDaysObserved);
    }

    // FinancialRisks class calls:
    public static boolean getHavePricesDecreased(int financialRiskDaysObserved) {
        double totalPriceDecrease = 0.0;
        double totalPriceIncrease = 0.0;

        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scannerFirstLine = new Scanner(file);
            Scanner scannerSecondLine = new Scanner(file);

            String tmp = scannerSecondLine.nextLine();

            int lineNum = 0;
            while (scannerSecondLine.hasNextLine() && lineNum <= financialRiskDaysObserved - 1) {
                String rowFirst = scannerFirstLine.nextLine();
                String rowSecond = scannerSecondLine.nextLine();

                if (lineNum > 0) {
                    String[] rowListFirst = rowFirst.split(",");
                    String[] rowListSecond = rowSecond.split(",");

                    double rowFirstPrice = Double.valueOf(rowListFirst[1]);
                    double rowSecondPrice = Double.valueOf(rowListSecond[1]);

                    if (rowSecondPrice - rowFirstPrice < 0) {
                        totalPriceDecrease += rowSecondPrice - rowFirstPrice;
                    } else {
                        totalPriceIncrease += rowSecondPrice - rowFirstPrice;
                    }
                }
            }

            if (Math.abs(totalPriceDecrease) > Math.abs(totalPriceIncrease)) {
                return true;
            }

            scannerFirstLine.close();
            scannerSecondLine.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scannerFirstLine = new Scanner(file);
            Scanner scannerSecondLine = new Scanner(file);

            String tmp = scannerSecondLine.nextLine();

            int lineNum = 0;
            while (scannerSecondLine.hasNextLine() && lineNum <= priceRateOfIncreaseDaysObserved - 1) {
                String rowFirst = scannerFirstLine.nextLine();
                String rowSecond = scannerSecondLine.nextLine();

                if (lineNum > 0) {
                    String[] rowListFirst = rowFirst.split(",");
                    String[] rowListSecond = rowSecond.split(",");

                    double rowFirstPrice = Double.valueOf(rowListFirst[1]);
                    double rowSecondPrice = Double.valueOf(rowListSecond[1]);

                    if (rowSecondPrice - rowFirstPrice > priceRateOfIncreaseObserved) {
                        return true;
                    }
                }
            }

            scannerFirstLine.close();
            scannerSecondLine.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return false;
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

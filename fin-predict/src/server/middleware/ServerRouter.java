package server.middleware;

import org.json.simple.parser.ParseException;
import server.data_anomaly_finder.DataAnomalyFinder;
import server.database.CSVDatabase;
import server.database.ImageDatabase;
import server.model_connection.ModelConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerRouter extends Server {
    protected static ModelConnection modelConnection = new ModelConnection();
    protected static CSVDatabase csvDatabase = new CSVDatabase();
    protected static ImageDatabase imageDatabase = new ImageDatabase();
    protected static DataAnomalyFinder dataAnomalyFinder = new DataAnomalyFinder();

    @Override
    protected String getServerType() {
        return this.getClass().getName();
    }

    // Price / Request class calls:
    protected static List<Double> reqPreviousPrices(int daysRequested) {
        return csvDatabase.getPreviousPrices(daysRequested);
    }

    protected static double reqCurrentDayPrediction() throws IOException, ParseException {
        return modelConnection.getCurrentDayPrediction();
    }

    // FinancialOpportunities class calls:
    protected static boolean reqGrowthRateIncreasedPastThreshold(int daysObserved, double growthRateThreshold) {
        return dataAnomalyFinder.getGrowthRateIncreasedPastThreshold(daysObserved, growthRateThreshold);
    }

    protected static boolean reqHavePricesStabilized(int stabilizedPriceDaysObserved, double stabilizationPlusMinusBoundary) {
        return dataAnomalyFinder.getHavePricesStabilized(stabilizedPriceDaysObserved, stabilizationPlusMinusBoundary);
    }

    protected static boolean reqIsCurrentPriceHigher(int avgPreviousPriceDaysObserved) {
        return dataAnomalyFinder.getIsCurrentPriceHigher(avgPreviousPriceDaysObserved);
    }

    // FinancialRisks class calls:
    protected static boolean reqHavePricesDecreased(int financialRiskDaysObserved) {
        return dataAnomalyFinder.getHavePricesDecreased(financialRiskDaysObserved);
    }

    protected static boolean reqHavePricesPropagated(int priceRateOfIncreaseDaysObserved, double priceRateOfIncreaseObserved) {
        return dataAnomalyFinder.getHavePricesPropagated(priceRateOfIncreaseDaysObserved, priceRateOfIncreaseObserved);
    }

    protected static boolean reqIsCurrentPriceLower(int avgPreviousPriceDaysObserved) {
        return dataAnomalyFinder.getIsCurrentPriceLower(avgPreviousPriceDaysObserved);
    }

    // Request class calls:
    protected static boolean reqRefreshOperation(String begDate, String endDate) {
        return csvDatabase.doRefreshOperation(begDate, endDate);
    }
}

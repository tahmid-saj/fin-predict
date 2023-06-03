package client.dashboard;
import client.assets.output_formatter.OutputFormatter;
import client.middleware.ClientRouter;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Price extends ClientRouter {
    private static LocalDateTime today = LocalDateTime.now();
    private static final double MILLISECS_IN_A_DAY = 1000 * 60 * 60 * 24;

    // Default value of past days of the prices to display
    protected static final int previousDaysDisplay = 3;

    // Displays past prices if daysToDisplay is given
    protected void displayPastPrices(int daysToDisplay) {
        List<Double> resPreviousPrices = ClientRouter.reqPreviousPrices(daysToDisplay);
        List<LocalDateTime> previousDaysToDisplay = new ArrayList<LocalDateTime>();
        Map<LocalDateTime, Double> datePrices = new HashMap<LocalDateTime, Double>();

        for (int prevDay = 0; prevDay < daysToDisplay; prevDay++) {
            previousDaysToDisplay.add(today.minus(prevDay, ChronoUnit.DAYS));
            datePrices.put(today.minus(prevDay, ChronoUnit.DAYS), resPreviousPrices.get(prevDay));
        }

        OutputFormatter.printPreviousPrices(resPreviousPrices, daysToDisplay, previousDaysToDisplay, datePrices);
    }

    // Displays past prices if daysToDisplay is not given
    protected void displayPastPrices() {
        // Uses previousDaysDisplay by default
        List<Double> resPreviousPrices = ClientRouter.reqPreviousPrices(previousDaysDisplay);
        List<LocalDateTime> previousDaysToDisplay = new ArrayList<LocalDateTime>();
        Map<LocalDateTime, Double> datePrices = new HashMap<LocalDateTime, Double>();

        for (int prevDay = 0; prevDay < previousDaysDisplay; prevDay++) {
            previousDaysToDisplay.add(today.minus(prevDay, ChronoUnit.DAYS));
            datePrices.put(today.minus(prevDay, ChronoUnit.DAYS), resPreviousPrices.get(prevDay));
        }

        OutputFormatter.printPreviousPrices(resPreviousPrices, previousDaysDisplay, previousDaysToDisplay, datePrices);
    }

    // Displays the forecasting prediction of current day
    protected void displayCurrentDayPrediction() throws IOException, ParseException {
        double resCurrentDayPrediction = ClientRouter.reqCurrentDayPrediction();
        OutputFormatter.printCurrentDayPrediction(today, resCurrentDayPrediction);
    }
}

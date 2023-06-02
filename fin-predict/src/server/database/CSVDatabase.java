package server.database;

import server.middleware.ServerRouter;

import java.util.ArrayList;
import java.util.List;

public class CSVDatabase extends ServerRouter implements Database {

    public static List<Double> getPreviousPrices(int daysRequested) {
        List<Double> resPreviousPrices = new ArrayList<Double>();
        resPreviousPrices.add(1.0);
        resPreviousPrices.add(2.0);
        resPreviousPrices.add(5.0);

        return resPreviousPrices;
    }

}

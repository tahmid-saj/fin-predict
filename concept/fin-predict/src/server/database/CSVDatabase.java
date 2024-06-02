package server.database;

import server.middleware.ServerRouter;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVDatabase extends ServerRouter implements Database {

    public static List<Double> getPreviousPrices(int daysRequested) {
        List<Double> resPreviousPrices = new ArrayList<Double>();

        try {
            File file = new File("data/database/btc_prices.csv");
            Scanner scanner = new Scanner(file);

            int lineNum = 0;
            while (scanner.hasNextLine() && lineNum <= daysRequested) {
                String row = scanner.nextLine();

                if (lineNum > 0) {
                    String[] rowList = row.split(",");
                    double rowPrice = Double.valueOf(rowList[1]);
                    resPreviousPrices.add(rowPrice);
                }
                lineNum++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return resPreviousPrices;
    }

    public static boolean doRefreshOperation(String bedDate, String endDate) {
        return true;
    }
}

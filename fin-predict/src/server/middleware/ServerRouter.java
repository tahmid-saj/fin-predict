package server.middleware;

import server.data_anomaly_finder.DataAnomalyFinder;
import server.database.CSVDatabase;
import server.database.ImageDatabase;
import server.model_connection.ModelConnection;

public class ServerRouter extends Server {
    protected static ModelConnection modelConnection = new ModelConnection();
    protected static CSVDatabase csvDatabase = new CSVDatabase();
    protected static ImageDatabase imageDatabase = new ImageDatabase();
    protected static DataAnomalyFinder dataAnomalyFinder = new DataAnomalyFinder();

    @Override
    protected void routeModelConnection() {

    }

    @Override
    protected void routeDataAnomalyFinder() {

    }

    @Override
    protected void routeDatabase() {

    }
}

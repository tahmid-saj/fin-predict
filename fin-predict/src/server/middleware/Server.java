package server.middleware;

public abstract class Server {

    protected abstract void routeModelConnection();

    protected abstract void routeDataAnomalyFinder();

    protected abstract void routeDatabase();
}

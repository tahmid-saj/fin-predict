package client.console_interface;

import client.middleware.ClientRouter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Request extends ClientRouter {

    // Returns current day prediction
    protected double performPredictOperation() throws IOException, ParseException {
        return ClientRouter.reqCurrentDayPrediction();
    }

    // Returns if the refresh was performed
    protected boolean performRefreshOperation(String begDate, String endDate) {
        return ClientRouter.reqRefreshOperation(begDate, endDate);
    }
}

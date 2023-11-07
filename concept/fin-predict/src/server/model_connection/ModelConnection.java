package server.model_connection;

import server.middleware.ServerRouter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class ModelConnection {

    public static double getCurrentDayPrediction() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("data/predictions_output/prediction_output.json"));
        JSONObject jo = (JSONObject) obj;

        boolean success = (Boolean)jo.get("success");
        String predictionType = (String)jo.get("predictionType");
        String predictionDate = (String)jo.get("predictionDate");
        double predictionValue = (Double)jo.get("predictionValue");

        return predictionValue;
    }
}

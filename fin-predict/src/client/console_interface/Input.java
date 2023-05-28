package client.console_interface;

public class Input extends Console implements ViewConsole {

    @Override
    public void displayForecast() {
        System.out.println("Forecast for: " + ViewConsole.today);
    }

    void refreshOperation() {

    }

    void readOperation() {

    }
}

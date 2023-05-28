package client.console_interface;

public class Input extends Console implements ViewConsole {

    @Override
    public void displayForecast() {
        System.out.println(ViewConsole.today);
    }
    void refreshOperation() {

    }

    void readOperation() {

    }
}

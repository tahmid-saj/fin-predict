package client.console_interface;

import java.util.Date;

public interface ViewDashboard {
    static Date today = new Date();

    void setup();
    void displayDashboard();
}

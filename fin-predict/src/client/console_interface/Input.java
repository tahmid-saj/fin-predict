package client.console_interface;

import java.util.Scanner;

public class Input extends Prompt {
    Request request = new Request();

    protected static boolean parseInput() {
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine().toLowerCase();

        switch (inputString) {
            case "predict":
            case "p":
                parsePredictOperation();
            case "refresh":
            case "r":
                parseRefreshOperation();
            case "dashboard":
            case "d":
                parseDisplayDashboard();
            case "close":
            case "cls":
            case "c":
                return true;
            default:
                System.out.println("Please either enter predict (p), refresh (r), dashboard (d), close / cls (c)");
                break;
        }

        return false;
    }

    private static void parsePredictOperation() {

    }

    private static void parseRefreshOperation() {

    }

    private static void parseDisplayDashboard() {

    }
}

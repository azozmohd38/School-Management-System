package utils;

import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (HelperUtils.isValidText(value)) {
                return value.trim();
            }
            System.out.println("Invalid text");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number");
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (HelperUtils.isInRange(value, min, max)) {
                return value;
            }
            System.out.println("Number must be between " + min + " and " + max);
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a decimal number");
            }
        }
    }

    public boolean readConfirmation(String prompt) {
        String value = readOneOf(prompt, new String[]{"YES", "NO", "Y", "N"});
        return value.equalsIgnoreCase("YES") || value.equalsIgnoreCase("Y");
    }

    public String readOneOf(String prompt, String[] allowed) {
        while (true) {
            String value = readText(prompt);
            if (HelperUtils.isOneOf(value, allowed)) {
                return value;
            }
            System.out.println("Value is not allowed");
        }
    }
}

package util;
import java.util.Scanner;
public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    public static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Try again.");
        }
    }
    public static String readName(String message) {
        while (true) {
            String name = readText(message);
            if (name.matches("[A-Za-z ]+")) {
                return name;
            }
            System.out.println("Name should contain only letters and spaces.");
        }
    }
    public static String readPhone(String message) {
        while (true) {
            String phone = readText(message);
            if (phone.matches("\\d{10}")) {
                return phone;
            }
            System.out.println("Phone number must have exactly 10 digits.");
        }
    }
    public static String readEmail(String message) {
        while (true) {
            String email = readText(message);
            if (email.matches("[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[A-Za-z]+")) {
                return email;
            }
            System.out.println("Enter a valid email (example: name@mail.com).");
        }
    }
    public static int readInt(String message, int min, int max) {
        while (true) {
            String text = readText(message);
            try {
                int number = Integer.parseInt(text);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
    public static int readId(String message) {
        return readInt(message, 1, Integer.MAX_VALUE);
    }
    public static double readAmount(String message) {
        while (true) {
            String text = readText(message);
            try {
                double amount = Double.parseDouble(text);
                if (amount > 0) {
                    return amount;
                }
                System.out.println("Amount must be greater than zero.");
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
